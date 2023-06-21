package com.vnit.api.entity;

public class Object {
    String columnName;
    String columnType;
    boolean primaryKey;
    int size;
    boolean required;

    public Object(String columnName, String columnType, boolean primaryKey, int size, boolean required) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.primaryKey = primaryKey;
        this.size = size;
        this.required = required;
    }

    public String getColumnName() {
        return this.columnName;
    }
    
    public String getColumnType() {
        return this.columnType;
    }

    public boolean getColumnPrimaryKey() {
        return this.primaryKey;
    }

    public int getColumnSize() {
        return this.size;
    }

    public boolean getColumnRequired() {
        return this.required;
    }
}
