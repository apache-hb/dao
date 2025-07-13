module com.github.elliothb.dao.runtime.dynamodb {
    requires com.github.elliothb.db;
    requires software.amazon.awssdk.services.dynamodb;
    requires org.apache.logging.log4j;

    provides com.github.elliothb.db.spi.DaoEnvironment
        with com.github.elliothb.dao.runtime.dynamodb.DynamoEnvironment;
}
