package com.github.elliothb.daocc.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "constraint")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ConstraintXml {
    @XmlAttribute(name = "name")
    public String name;

    @XmlAttribute(name = "references")
    public String references;

    @XmlAttribute(name = "unique")
    public boolean unique;
}
