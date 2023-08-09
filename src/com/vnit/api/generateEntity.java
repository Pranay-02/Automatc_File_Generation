package com.vnit.api;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.vnit.api.util.*;
import com.vnit.api.entity.Object;

public class generateEntity {

    ProcessSubstitution processSubstitution;
    private static String tableName = "customer";
    
    generateEntity() {
        processSubstitution = new ProcessSubstitution();
    }

    
    public String generateEntityFile(ArrayList<Object> columns) {
        String fileContent = processSubstitution.getEntityTemplate(columns);
        return fileContent;
    }

    public String generateRepoFile(String columnName) {
        String fileContent = processSubstitution.getRepoTemplate(columnName);
        return fileContent;
    }

    public String generateControllerFile(ArrayList<Object> columns, String columnName) {
        String fileContent = processSubstitution.getControllerTemplate(columns, columnName);
        return fileContent;
    }

    public String generateCssFile() {
        String fileContent = processSubstitution.getCSSTemplate();
        return fileContent;
    }

    public String generateHtmlFile(ArrayList<Object> columns) {
        String fileContent = processSubstitution.getHtmlTemplate(columns);
        return fileContent;
    }

    public static void main(String[] args) {

        dbUtility db = new dbUtility();
        fileUtility ut = new fileUtility();
        generateEntity entity = new generateEntity();
       
        String propertiesFillPath = ut.getFilePath();
        db.fillMap(propertiesFillPath + "properties.txt");

        Connection con = db.createDBConnection();   
        
        mapsUtil.constantsMap.put("table_name", tableName);
        char ch = tableName.charAt(0);
        String capTableName = "" + (char)(ch - 'a' + 'A') + tableName.substring(1);

        mapsUtil.constantsMap.put("cap_table_name", capTableName);

        ArrayList<Object> columns = db.getColumns(tableName, con);
        String primaryKeyColumn = db.getPrimaryKeyColumn();

        String entityFile = entity.generateEntityFile(columns);
        String repoFile = entity.generateRepoFile(primaryKeyColumn);
        String controllerFile = entity.generateControllerFile(columns, primaryKeyColumn);
        String cssFile = entity.generateCssFile();
        String htmlFile = entity.generateHtmlFile(columns);
        
        try {
            ut.createFileFromString(propertiesFillPath + "entity\\" + capTableName +"Mst.java", entityFile);
            ut.createFileFromString(propertiesFillPath + "repo\\" + capTableName + "Repo.java", repoFile);
            ut.createFileFromString(propertiesFillPath + "controller\\" + capTableName + "Controller.java", controllerFile);
            ut.createFileFromString(propertiesFillPath + "frontend\\" + capTableName + ".css", cssFile);
            ut.createFileFromString(propertiesFillPath + "frontend\\" + capTableName + ".html", htmlFile);
        }
        catch(IOException exception) {
            System.out.println("Not able to create file");
        }
    }

}
