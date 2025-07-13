package com.github.elliothb.daocc.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public abstract class NamedObjectXml {
    @XmlAttribute(name = "name", required = true)
    public String name;

    @XmlAttribute(name = "comment")
    public String comment;

    @XmlElement(name = "annotation")
    public List<AnnotateXml> annotations;
}
