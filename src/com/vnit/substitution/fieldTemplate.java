package com.vnit.substitution;

public class fieldTemplate {

    fragmentTemplate fragments = new fragmentTemplate();
    
    public String getTemplate() {
        String temp = "";
        
        temp += "#package_name#" + "\n\n";

        temp += "#imports#" + "\n\n";

        temp += "@Entity\n" + 
                "@Table(name=\"#table_name#\")\n";

        temp += "public class #table_name#Mst {\n";
        
        // temp += "}";

        return temp;
    }
}
