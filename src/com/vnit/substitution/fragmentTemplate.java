package com.vnit.substitution;

public class fragmentTemplate {

    public String getFieldFragments(String columnName) {
        String temp = "\n";
      
        temp += "@ApiModelProperty(required = #00$01$m2:fld:" + columnName + ":required$#,"; 
        temp += " value = \"(#00$01$m2:fld:" + columnName +":size$#)\")\n";

        // for primary key only
        temp += "#00$02$m2:fld:" + columnName + ":primary_key$c:@Id\n$#";
        temp += "#00$02$m2:fld:" + columnName + ":primary_key$c:@GeneratedValue(strategy=GenerationType.IDENTITY)\n$#";
        
        temp += "@Column(name = \"#00$01$m2:fld:" + columnName + ":column_name$#\")\n";
        temp += "private #00$01$m2:fld:" + columnName + ":column_type$# #00$01$m2:fld:" + columnName + ":column_name$#;\n\n";

        temp += "public #00$01$m2:fld:" + columnName + ":column_type$# get#00$01$m2:fld:" + columnName + ":column_name$#() {\n" + 
                "\treturn #00$01$m2:fld:" + columnName + ":column_name$#;\n" + "}\n\n";

        temp += "public void set#00$01$m2:fld:" + columnName + ":column_name$#() {\n" +
                "\tthis.#00$01$m2:fld:" + columnName + ":column_name$# = #00$01$m2:fld:" + columnName + ":column_name$#;\n";
        
        temp += "}\n\n";

        return temp;
    }
}
