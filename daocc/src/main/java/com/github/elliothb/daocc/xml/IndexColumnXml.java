package com.github.elliothb.daocc.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class IndexColumnXml {
    @XmlAttribute(name = "name", required = true)
    public String name;
}
