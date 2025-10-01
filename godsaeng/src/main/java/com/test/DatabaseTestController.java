package com.test;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {  // 여기가 핵심!
            return "Database connection successful! " +
                   "Database: " + connection.getMetaData().getDatabaseProductName() +
                   ", Version: " + connection.getMetaData().getDatabaseProductVersion();
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}