package com.vnit.substitution;

import java.util.*;
import com.vnit.api.entity.Object;
import com.vnit.api.util.dbUtility;

public class template {

    substitution sub;
    fieldFragmentTemplate fftemplate = new fieldFragmentTemplate();
    
    public String getFile(String tableName, ArrayList<Object> list, dbUtility db) {

        sub = new substitution(db);

        String fileContent = "";

        fileContent += sub.getPackageName();
        fileContent += sub.getHeaderFiles();
        fileContent += sub.getTableAnnotations(tableName);
        fileContent += sub.getClassDeclaration(tableName);

        fileContent += fftemplate.getFieldFragments(list, db);

        fileContent += sub.getClosingBracket();

        return fileContent;
    }
}
