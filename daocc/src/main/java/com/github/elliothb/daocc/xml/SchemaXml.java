package com.github.elliothb.daocc.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "schema")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SchemaXml {
    @XmlAttribute(name = "name", required = true)
    public String name;

    @XmlAttribute(name = "java.package")
    public String javaPackage;

    @XmlAttribute(name = "cxx.package")
    public String cxxPackage;

    @XmlElement(name = "table")
    public List<TableXml> tables;
}
