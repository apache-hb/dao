package com.github.elliothb.daocc.xml;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "type")
public enum DataTypeXml {
    @XmlEnumValue("text") TEXT,
    @XmlEnumValue("char") CHAR,
    @XmlEnumValue("float") FLOAT,
    @XmlEnumValue("double") DOUBLE,
    @XmlEnumValue("int") INT,
    @XmlEnumValue("uint") UINT,
    @XmlEnumValue("long") LONG,
    @XmlEnumValue("ulong") ULONG,
    @XmlEnumValue("bool") BOOL,
    @XmlEnumValue("datetime") DATETIME,
    @XmlEnumValue("tz_datetime") TZ_DATETIME,
    @XmlEnumValue("blob") BLOB,
}
