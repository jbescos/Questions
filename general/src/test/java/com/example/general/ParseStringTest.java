package com.example.general;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParseStringTest {

    @Test
    public void test1() {
        String example = "M<K>";
        Parser parser = new Parser(example);
        String result = parser.make().toString();
        assertEquals(example, result);
    }

    @Test
    public void test2() {
        String example = "M<K<Q>>";
        Parser parser = new Parser(example);
        String result = parser.make().toString();
        assertEquals(example, result);
    }

    @Test
    public void test3() {
        String example = "M<K,V>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
        assertEquals(2, tree.childs.size());
    }

    @Test
    public void test4() {
        String example = "M<K,V<Q>>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
        assertEquals(2, tree.childs.size());
    }

    @Test
    public void test5() {
        String example = "java.util.Map <K extends ?, V super O <Q>>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
        assertEquals(2, tree.childs.size());
    }

    @Test
    public void test6() {
        String example = "Map <Key, Value<Que>>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
        assertEquals(2, tree.childs.size());
    }

    @Test
    public void test7() {
        String example = "M<K<Q> >";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals("M<K<Q>>", result);
    }

    @Test
    public void test8() {
        String example = "M<K<T>,V>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
        assertEquals(2, tree.childs.size());
    }

    @Test
    public void test9() {
        String example = "M<T,Q,R>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
        assertEquals(3, tree.childs.size());
    }

    @Test
    public void test10() {
        String example = "M<A,B<C,D<E>,F<G>>>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
    }

    @Test
    public void test11() {
        String example = "M<A,B[]<C[],D[]<E>,F<G[]>>>";
        Parser parser = new Parser(example);
        Node<String> tree = parser.make();
        String result = tree.toString();
        assertEquals(example, result);
    }

    private static class Parser {
        private final String str;

        public Parser(String str) {
            this.str = str;
        }

        Node<String> make() {
            Node<String> root = new Node<>(null);
            StringBuilder content = new StringBuilder();
            Node<String> current = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '<') {
                    Node<String> child = new Node<>(current);
                    current.value = content.toString();
                    current.childs.add(child);
                    current = child;
                    content = new StringBuilder();
                } else if (c == '>') {
                    if (current.value == null) {
                        current.value = content.toString();
                    }
                    current = current.parent;
                    content = new StringBuilder();
                } else if (c == ',') {
                    if (current.value == null) {
                        current.value = content.toString();
                    }
                    content = new StringBuilder();
                    Node<String> brother = new Node<>(current.parent);
                    brother.parent.childs.add(brother);
                    current = brother;
                } else {
                    content.append(c);
                }
            }
            return root;
        }
    }

    private static class Node<T> {
        private T value;
        private final Node<T> parent;
        private final List<Node<T>> childs = new LinkedList<>();

        public Node(Node<T> parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(value.toString());
            boolean hasChilds = !childs.isEmpty();
            if (hasChilds) {
                builder.append("<");
            }
            for (Node<T> child : childs) {
                builder.append(child.toString()).append(",");
            }
            if (hasChilds) {
                // Remove last comma
                builder.deleteCharAt(builder.length() - 1);
                builder.append(">");
            }
            return builder.toString();
        }
    }
}
