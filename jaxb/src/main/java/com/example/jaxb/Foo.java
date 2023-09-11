package com.example.jaxb;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"fooObject"})
@XmlRootElement(name = "Foo")
public class Foo {
    protected List<Foo> fooObject;

    public List<Foo> getFooObject() {
        if (fooObject == null) {
            fooObject = new ArrayList<Foo>();
        }
        return this.fooObject;
    }
}