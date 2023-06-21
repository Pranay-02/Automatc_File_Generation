package com.vnit.test;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import com.vnit.api.util.*;
import com.vnit.api.entity.Object;

public class test {
    public static void main(String []args) {

        dbUtility db = new dbUtility();
        fileUtility ut = new fileUtility();

        String filePath = "D:\\VNIT 2nd year\\Project\\Task\\src\\com\\vnit\\api\\properties.txt";
        db.fillMap(filePath);

        // test for mapping of dbvariable to java variable
        String javaType = db.getDBVariableMapping("INTEGER");
        System.out.println(javaType);

        // test for mapping of dbvariable to java variable
        String dbDetail = db.getDBDetailMapping("db_password");
        System.out.println(dbDetail);

        // test for successful dbconnection
        Connection con = db.createDBConnection();
        if(con != null) {
            System.out.println("Connection successfully established");
        }
        else {
            System.out.println("Connection not established");
        }

        // test for column details of table customer
        ArrayList<Object> columnDetails = db.getColumns("customer", con);
        for(int i = 0; i < columnDetails.size(); i++) {
            System.out.println(columnDetails.get(i).getColumnType());
        }

        // test for converting file to string
        String content = ut.getFileAsString(filePath);
        System.out.println(content);

        // test for converting string to file
        String filePath1 = "D:\\VNIT 2nd year\\Project\\Task\\src\\com\\vnit\\api\\properties1.txt";
        try {
            ut.createFileFromString(filePath1, content);
        }
        catch (IOException e) {
            System.out.println("File not created");
        }
    }
}
