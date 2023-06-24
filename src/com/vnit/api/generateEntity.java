package com.vnit.api;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.vnit.api.util.*;
import com.vnit.api.entity.Object;
import com.vnit.substitution.*;


public class generateEntity {

    // String tableName;

    // public generateEntity(String tableName) {
    //     this.tableName = tableName;
    // }
    
    public String generateFile(String tableName, Connection con, dbUtility db, template temp) {
        ArrayList<Object> columns = db.getColumns(tableName, con);
        String fileContent = temp.getFile(tableName, columns, db);
        return fileContent;
    }

    public static void main(String[] args) {

        dbUtility db = new dbUtility();
        fileUtility ut = new fileUtility();
        generateEntity entity = new generateEntity();
        template temp = new template();

        String propertiesFillPath = ut.getFilePath();
        db.fillMap(propertiesFillPath + "properties.txt");

        Connection con = db.createDBConnection();   
        
        String file = entity.generateFile("customer", con, db, temp);
        try {
            ut.createFileFromString(propertiesFillPath + "entity\\itemMst.java", file);
        }
        catch(IOException exception) {
            System.out.println("Not able to create file");
        }       
    }

}
