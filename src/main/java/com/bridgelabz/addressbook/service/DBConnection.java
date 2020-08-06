package com.bridgelabz.addressbook.service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection
{
    public Connection getConnection()
    {
        Connection connection = null;
        FileReader fileReader;
        Properties properties = new Properties();
        try
        {
            fileReader = new FileReader("src/main/resources/personDB.properties");
            properties.load(fileReader);
            Class.forName(properties.getProperty("DB_Driver"));
            connection = DriverManager.getConnection(properties.getProperty("DB_Url"), properties.getProperty("DB_User"),
                    properties.getProperty("DB_Password"));
        } catch (ClassNotFoundException | SQLException | IOException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
