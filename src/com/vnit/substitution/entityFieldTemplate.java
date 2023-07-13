package com.vnit.substitution;

public class entityFieldTemplate {

    public String getTemplate() {
        String temp = "";
        
        temp += "#00$01$m:constantsMap:entity_package_name$#" + "\n\n";

        temp += "#00$01$m:constantsMap:entity_imports$#" + "\n\n";

        temp += "@Entity\n" + 
                "@Table(name=\"" + "#$00$01$m:constantsMap:table_name$#" +  "\")\n";

        temp += "public class "  + "#$00$01$m:constantsMap:cap_table_name$#" + "Mst {\n";
        
        return temp;
    }

    public String getClosingBracket() {
        return "}";
    }
}
