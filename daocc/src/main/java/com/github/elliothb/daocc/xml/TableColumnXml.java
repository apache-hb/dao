package com.github.elliothb.daocc.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TableColumnXml extends NamedObjectXml {
    @XmlAttribute(name = "type", required = true)
    public DataTypeXml type;

    @XmlAttribute(name = "required")
    public boolean required = true;

    @XmlAttribute(name = "length")
    public long length = 0;

    @XmlAttribute(name = "autoIncrement")
    public AutoIncrementXml autoIncrement = null;

    @XmlElement(name = "constraint")
    public List<ConstraintXml> constraints;
}
