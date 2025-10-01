package com.sol.zmfhzll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PostgresConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection(){
        assertNotNull(dataSource, "DataSource is null");

        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "connection is null");
            assertTrue(connection.isValid(1), "Connection is null");

            System.out.println("Data connection success");
            System.out.println("Database: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("Version: " + connection.getMetaData().getDatabaseProductVersion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
