package com.github.elliothb.daocc.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "index")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class IndexXml extends NamedObjectXml {
    @XmlElement(name = "column")
    public List<IndexColumnXml> columns;
}
