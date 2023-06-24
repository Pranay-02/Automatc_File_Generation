package com.vnit.api.util;

import java.util.*;
import java.sql.*;
import java.io.*;
import com.vnit.api.entity.Object;

public class dbUtility {
    static public Map<String, String> variableMap = new HashMap<>();
    static public Map<String, String> dbDetailMap = new HashMap<>();
    static public Map<String, String> constantsMap = new HashMap<>();
    
    static public Map<String, Map<String, String>> fld = new HashMap<>();
    
    public void fillMap(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;    
            String currentMap = "";

            while ((line = reader.readLine()) != null) {
                if(line.startsWith("#")) {
                    currentMap = line.substring(1).trim();
                }
                else {
                    String []str = line.split("=");
                    if(currentMap.equals("Mysql server details")) {
                        dbDetailMap.put(str[0], str[1]);
                    }
                    else 
                    if(currentMap.equals("Java data type details")) {
                        variableMap.put(str[0], str[1]);
                    }
                    else 
                    if(currentMap.equals("Constants details")) {
                        constantsMap.put(str[0], str[1]);
                    }
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public Connection createDBConnection() {
        String url = getDBDetailMapping("db_url");
        String username = getDBDetailMapping("db_username");
        String password = getDBDetailMapping("db_password");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    
    public ArrayList<Object> getColumns(String tableName, Connection connection) {        
        ArrayList<Object> columns =  new ArrayList<>();

        try {

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columnDetails = metaData.getColumns(null, null, tableName, null);
            ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName);

            //get primary ket details
            Set<String> primaryKeyColumns = new HashSet<>();
            while (primaryKeysResultSet.next()) {
                String primaryKeyColumnName = primaryKeysResultSet.getString("COLUMN_NAME");
                primaryKeyColumns.add(primaryKeyColumnName);
            }

            // Process and print the column details
            while (columnDetails.next()) {
                String columnName = columnDetails.getString("COLUMN_NAME");
                String columnType = columnDetails.getString("TYPE_NAME");
                boolean isPrimaryKey = primaryKeyColumns.contains(columnName);
                int columnSize = columnDetails.getInt("COLUMN_SIZE");
                boolean required = (columnDetails.getString("IS_NULLABLE").equals("YES")) ? true : false; 
                
                columnType = getDBVariableMapping(columnType);

                Object o = new Object(columnName, columnType, isPrimaryKey, columnSize, required);
                columns.add(o);

                Map<String, String> fld_map = new HashMap<>();
                fld_map.put("column_name", columnName);
                fld_map.put("column_type", columnType);
                fld_map.put("primary_key", isPrimaryKey ? "true" : "false");
                fld_map.put("size", String.valueOf(columnSize));
                fld_map.put("required", required ? "true" : "false");

                fld.put(columnName, fld_map);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return columns;
    }

    public String getDBVariableMapping(String dbVariable) {
        return variableMap.get(dbVariable);
    }

    public String getDBDetailMapping(String dbDetail) {
        return dbDetailMap.get(dbDetail);
    }

    public String getConstantDetailMapping(String constant) {
        return constantsMap.get(constant);
    }
}
