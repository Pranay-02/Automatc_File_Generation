package com.vnit.substitution;

public class fragmentTemplate {

    public String getFieldFragments() {
        String temp = "";
        // to be changed
        temp += "@ApiNodelProperty(required = #required#, value = \"(#size#)\")\n";
        temp += "#@Id\n#";
        temp += "#@GeneratedValue(strategy=GenerationType.IDENTITY)\n#";
        temp += "@Column(name = \"#column_name#\")\n";
        temp += "private $2#data_type#2$ #column_name#;\n";

        temp += "public $2#data_type#2$ get#column_name#() {\n" + 
                "\treturn #column_name#\n" + "}\n\n";

        temp += "public void set#column_name#() {\n" +
                "\tthis.#column_name# = #column_name#\n + }\n\n";

        return temp;
    }

}
