module com.github.elliothb.daocc {
    requires jakarta.xml.bind;
    requires org.apache.logging.log4j;

    exports com.github.elliothb.daocc.xml;

    opens com.github.elliothb.daocc.xml to jakarta.xml.bind;
}
