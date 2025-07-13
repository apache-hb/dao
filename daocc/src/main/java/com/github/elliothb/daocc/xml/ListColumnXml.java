package com.github.elliothb.daocc.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ListColumnXml extends NamedObjectXml {
    @XmlAttribute(name = "of")
    public String table;

    @XmlAttribute(name = "by")
    public String column;
}
