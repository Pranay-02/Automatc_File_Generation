package com.vnit.substitution;

public class fieldTemplate {

    public String getTemplate() {
        String temp = "";
        
        temp += "#00$01$m:constantsMap:package_name$#" + "\n\n";

        temp += "#00$01$m:constantsMap:imports$#" + "\n\n";

        temp += "@Entity\n" + 
                "@Table(name=\"" + "#$00$01$m:constantsMap:table_name$#" +  "\")\n\n";

        temp += "public class"  + "#$00$01$m:constantsMap:table_name$#" + "Mst {\n";
        
        return temp;
    }
}
