package com.github.elliothb.daocc.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TableXml extends NamedObjectXml {
    @XmlAttribute(name = "primaryKey")
    public String primaryKey;

    @XmlAttribute(name = "syntheticPrimaryKey")
    public DataTypeXml syntheticPrimaryKey;

    @XmlAttribute(name = "autoIncrement")
    public AutoIncrementXml autoIncrement;

    @XmlElement(name = "column")
    public List<TableColumnXml> columns;

    @XmlElement(name = "list")
    public List<ListColumnXml> lists;

    @XmlElement(name = "constraint")
    public List<ConstraintXml> constraints;
}
