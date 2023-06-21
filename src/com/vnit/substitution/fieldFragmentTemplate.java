package com.vnit.substitution;

import java.util.ArrayList;
import com.vnit.api.entity.Object;
import com.vnit.api.util.dbUtility;

public class fieldFragmentTemplate {

    substitution sub;

    public String getFieldFragments(ArrayList<Object> list, dbUtility db) {
        sub = new substitution(db);
        String fragments = "";

        for(int i = 0; i < list.size(); i++) {
            String columnName =  list.get(i).getColumnName();
            String columnType = list.get(i).getColumnType();
            boolean primaryKey = list.get(i).getColumnPrimaryKey();
            int size = list.get(i).getColumnSize();
            boolean required = list.get(i).getColumnRequired();

            fragments += sub.getColumnAnnotations(columnName, primaryKey, size, required);
            fragments += sub.getColumnDeclaration(columnName, columnType);
            fragments += sub.getFieldGetSet(columnName, columnType);
        }
 
        return fragments;

    }

}
