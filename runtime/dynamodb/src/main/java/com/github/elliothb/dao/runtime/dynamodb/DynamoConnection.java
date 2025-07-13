package com.github.elliothb.dao.runtime.dynamodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.elliothb.db.dao.Column;
import com.github.elliothb.db.dao.Schema;
import com.github.elliothb.db.dao.Table;
import com.github.elliothb.db.spi.DaoConnection;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;

public class DynamoConnection implements DaoConnection {
    private static final Logger LOGGER = LogManager.getLogger(DynamoConnection.class);

    private final DynamoDbClient ddbClient;

    public DynamoConnection(DynamoDbClient client) {
        this.ddbClient = client;
    }

    @Override
    public void create(Schema schema) throws Exception {
        for (Table table : schema.getTables()) {
            CreateTableResponse response = createTable(table);
            LOGGER.trace("Created table: {} with response: {}", table.getName(), response);
        }
    }

    private static KeySchemaElement createKeySchemaElement(Column column, KeyType keyType) {
        return KeySchemaElement.builder()
                .attributeName(column.getName())
                .keyType(keyType)
                .build();
    }

    public CreateTableResponse createTable(Table table) throws Exception {
        CreateTableRequest.Builder request = CreateTableRequest.builder()
                .tableName(table.getName());

        List<KeySchemaElement> keySchema = new ArrayList<>();

        Column primaryKey = table.getPrimaryKey();
        keySchema.add(createKeySchemaElement(primaryKey, KeyType.HASH));

        String sortKeyName = table.getAttribute("ddb.sortKey");
        if (sortKeyName != null) {
            Column partitionKey = table.findColumn(sortKeyName);
            keySchema.add(createKeySchemaElement(partitionKey, KeyType.RANGE));
        }

        LOGGER.trace("Creating table: {}, primary key: {}, sort key: {}", table.getName(), primaryKey.getName(), sortKeyName);

        return ddbClient.createTable(request.build());
    }

    @Override
    public void close() throws Exception {
        ddbClient.close();
    }
}
