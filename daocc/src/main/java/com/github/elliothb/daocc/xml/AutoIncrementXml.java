package com.github.elliothb.daocc.xml;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "autoIncrement")
public enum AutoIncrementXml {
    @XmlEnumValue("always") ALWAYS,
    @XmlEnumValue("never") NEVER,
    @XmlEnumValue("default") DEFAULT;
}
