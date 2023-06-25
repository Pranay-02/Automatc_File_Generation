package com.vnit.substitution;

public class fragmentTemplate {

    public String getFieldFragments(String columnName) {
        String temp = "";
      
        temp += "@ApiNodelProperty(required = #00$02$m:fld:" + columnName + "$m:fld_map:required$#,"; 
        temp += " value = \"(#00$02$m:fld:" + columnName +"$m:fld_map:size$#)\")\n";

    //  for primary key only
        // temp += "#@Id#\n";
        // temp += "#@GeneratedValue(strategy=GenerationType.IDENTITY)#\n";
        
        temp += "@Column(name = \"#00$02$m:fld:" + columnName + "$m:fld_map:column_name$#\")\n";
        temp += "private #00$02$m:fld:" + columnName + "$m:fld_map:column_type$# #00$02$m:fld:" + columnName + "$m:fld_map:column_name$#;\n";

        temp += "public #00$02$m:fld:" + columnName + "$m:fld_map:column_name$# get#00$02$m:fld:" + columnName + "$m:fld_map:column_name$#() {\n" + 
                "\treturn #00$02$m:fld:" + columnName + "$m:fld_map:column_name$#\n" + "}\n\n";

        temp += "public void set#00$02$m:fld:" + columnName + "$m:fld_map:column_name$#() {\n" +
                "\tthis.#00$02$m:fld:" + columnName + "$m:fld_map:column_name$# = #00$02$m:fld:" + columnName + "$m:fld_map:column_name$#\n";
        
        temp += "}\n\n";

        return temp;
    }
}
