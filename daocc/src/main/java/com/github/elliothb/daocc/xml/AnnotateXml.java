package com.github.elliothb.daocc.xml;

import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "annotate")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AnnotateXml {
    @XmlAnyElement
    public List<Object> elements;

    @XmlAnyAttribute
    public Map<String, String> attributes;
}
