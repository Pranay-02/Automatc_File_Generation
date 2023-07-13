package com.vnit.api;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.vnit.api.util.*;
import com.vnit.api.entity.Object;

public class generateEntity {

    private static String tableName = "customer";
    
    public String generateEntityFile(ArrayList<Object> columns) {
        ProcessSubstitution processSubstitution = new ProcessSubstitution();
        String fileContent = processSubstitution.getEntityTemplate(columns);
        return fileContent;
    }

    public String generateRepoFile(String columnName) {
        ProcessSubstitution processSubstitution = new ProcessSubstitution();
        String fileContent = processSubstitution.getRepoTemplate(columnName);
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
        
        try {
            ut.createFileFromString(propertiesFillPath + "entity\\" + capTableName +"Mst.java", entityFile);
            ut.createFileFromString(propertiesFillPath + "repo\\" + capTableName +"Repo.java", repoFile);
        }
        catch(IOException exception) {
            System.out.println("Not able to create file");
        }
        
        
    }

}
