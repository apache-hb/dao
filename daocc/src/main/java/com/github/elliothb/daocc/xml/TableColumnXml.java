package com.github.elliothb.daocc.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TableColumnXml {
    @XmlAttribute(name = "name", required = true)
    public String name;

    @XmlAttribute(name = "type", required = true)
    public DataTypeXml type;

    @XmlAttribute(name = "nullable")
    public boolean nullable = false;

    @XmlAttribute(name = "length")
    public long length = 0;

    @XmlAttribute(name = "autoIncrement")
    public AutoIncrementXml autoIncrement = null;
}
