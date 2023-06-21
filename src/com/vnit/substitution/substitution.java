package com.vnit.substitution;

import com.vnit.api.util.dbUtility;

public class substitution {

    dbUtility db;

    substitution(dbUtility db) {
        this.db = db;
    }

    public String getPackageName() {
        return "package " + db.getConstantDetailMapping("package") + "\n\n";
    }
    
    public String getHeaderFiles() {
        
        String []imports = db.getConstantDetailMapping("imports").split(";");
        
        String headerFiles = "";
        for(int i = 0; i < imports.length; i++) {
            headerFiles += "import " + imports[i] + ";\n";
        }

        headerFiles += "\n";

        return headerFiles;
    }

    public String getTableAnnotations(String tableName) {
        return db.getConstantDetailMapping("entity") + "\n" +
                db.getConstantDetailMapping("table") + "(name = \"" + tableName + "\")\n";
    }

    public String getClassDeclaration(String tableName) {
        return "public class "  + tableName + "Mst {\n\n";
    }

    public String getColumnAnnotations(String columnName, boolean primaryKey, int size, boolean required) {
        return db.getConstantDetailMapping("apiProperty") + 
        "(required = "+ required + ", value = \"(size = " + size + ")" + 
        ifRequired(required) + "\")\n" + ifPrimaryKey(primaryKey) + 
        db.getConstantDetailMapping("column") + "(name = \"" + columnName + "\")\n";
    }

    public String ifPrimaryKey(boolean primaryKey) {
        if(primaryKey) {
            return db.getConstantDetailMapping("id") + "\n" 
            + db.getConstantDetailMapping("generatedValue") + 
            "(strategy=" + db.getConstantDetailMapping("strategy") + ")\n"; 
        }

        return "";
    }

    public String ifRequired(boolean required) {
        if(required)
            return " (required)";

        return "";
    }

    public String getColumnDeclaration(String columnName, String columnType) {
        return "private " + columnType + " " + columnName + ";\n\n";
    }

    public String getFieldGetSet(String columnName, String columnType) {
        return getGetter(columnName, columnType) + "\n" + getSetter(columnName, columnType);
    }

    public String getGetter(String columnName, String columnType) {
        return "public " + columnType + " get" + getUpperCaseColumnName(columnName) + "()" +
        " {\n" + "\treturn " + columnName + ";\n}\n";
    }

    public String getSetter(String columnName, String columnType) {
        return "public void " + "set" + getUpperCaseColumnName(columnName) + "()" +
        " {\n" + "\tthis." + columnName + " = " + columnName + ";\n}\n\n";
    }

    public String getUpperCaseColumnName(String columnName) {
        char ch = columnName.charAt(0);
        return Character.toUpperCase(ch) + columnName.substring(1);
    }

    public String getClosingBracket() {
        return "}";
    }

}
