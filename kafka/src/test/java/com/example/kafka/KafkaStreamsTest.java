package com.example.kafka;


import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.junit.Test;

/**
 * Start it first: 
 * bin/zookeeper-server-start.sh config/zookeeper.properties
 * bin/kafka-server-start.sh config/server.properties
 */
public class KafkaStreamsTest {

    private static final String TOPIC_IN = "KafkaStreamsTest-Topic-In";
    private static final String TOPIC_OUT = "KafkaStreamsTest-Topic-Out";
    private static final String HOST = "localhost:9092";
    private static final String APP_ID = KafkaStreamsTest.class.getCanonicalName();
    private static final int END_SIGNAL = -1;

    private void produce() {
        System.out.println("produce begin");
        Properties props = new Properties();
        props.put("bootstrap.servers", HOST);
        props.put("acks", "all");
        props.put("key.serializer", IntegerSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        try (Producer<Integer, String> producer = new KafkaProducer<>(props);) {
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<Integer, String>(TOPIC_IN, i, "test n" + i));
            }
            producer.send(new ProducerRecord<Integer, String>(TOPIC_IN, END_SIGNAL, "End"));
            System.out.println("produce end");
        }  
    }
    
    private void consume() {
        System.out.println("consume begin");
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", HOST);
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", IntegerDeserializer.class.getName());
        props.setProperty("value.deserializer", StringDeserializer.class.getName());
        try(KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);){
            consumer.subscribe(Arrays.asList(TOPIC_OUT));
            boolean consuming = true;
            while (consuming) {
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<Integer, String> record : records) {
                    System.out.printf("key = %s, value = %s%n", record.key(), record.value());
                    if (record.key() == END_SIGNAL) {
                        consuming = false;
                    }
                }
            }
            System.out.println("consume end");
        }
    }
    
    private void stream() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APP_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        builder.<Integer, String>stream(TOPIC_IN).mapValues(value -> value + " was modified by stream").to(TOPIC_OUT);
        Topology topology = builder.build();
        System.out.println("Topology: " + topology.describe());
        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
        
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                System.out.println("stream end");
                streams.close();
            }
        });
        System.out.println("stream begin");
        streams.start();
    }
    
    @Test
    public void produceModifyAndConsume() {
        Executors.newFixedThreadPool(1).execute(() -> stream());
        produce();
        consume();
    }

}
