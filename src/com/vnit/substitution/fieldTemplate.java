package com.vnit.substitution;

public class fieldTemplate {

    fragmentTemplate fragments = new fragmentTemplate();
    
    public String getTemplate() {
        String temp = "";
        
        temp += "#00$01$m:constantsMap:package_name$#" + "\n\n";

        temp += "#00$01$m:constantsMap:imports$#" + "\n\n";

        // table name from user
        temp += "@Entity\n" + 
                "@Table(name=\"" + "#$00$01$m:constantsMap:table_name$#" +  "\")\n";

        temp += "public class"  + "#$00$01$m:constantsMap:table_name$#" + "Mst {\n";
        
        // temp += "}";

        return temp;
    }
}
