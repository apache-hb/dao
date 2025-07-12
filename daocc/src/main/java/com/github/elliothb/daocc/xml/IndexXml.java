package com.github.elliothb.daocc.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "index")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class IndexXml {
    @XmlAttribute(name = "name", required = true)
    public String name;

    public List<IndexColumnXml> columns;
}
