package com.vnit.api;

import java.util.ArrayList;

import com.vnit.substitution.fragmentTemplate;
import com.vnit.substitution.fieldTemplate;
import com.vnit.api.entity.Object;
import com.vnit.api.util.utility;

public class ProcessSubstitution {
    fieldTemplate fTemplate = new fieldTemplate();
    fragmentTemplate ffTemplate = new fragmentTemplate();
    utility ut = new utility();
    
    public String getCompleteTemplate(ArrayList<Object> columns) {
        String template = fTemplate.getTemplate();

        for(int i = 0; i < columns.size(); i++) {
            String fragments = ffTemplate.getFieldFragments(columns.get(i).getColumnName());
             template += fragments;
        }
        
        template += '}';
        processTemplate(template);

        return template;
    }

    public String processTemplate(String template) {
        ArrayList<String> hashStrings = ut.extractSubstringsByHash(template);
        ArrayList<String> substitutes = processHash(hashStrings);
        
        for(int i = 0; i < hashStrings.size(); i++) {
            String var = "#" + hashStrings.get(i) + "#";
            template = substitue(template, var, substitutes.get(i));
        }

        return template;

    }

    public ArrayList<String> processHash(ArrayList<String> hashStrings) {
        ArrayList<String> substitutes = new ArrayList<>();
        for(int i = 0; i < hashStrings.size(); i++) {
            String substiutedValue = "";
            ArrayList<String> dollarStrings = ut.extractTokensByDollar(hashStrings.get(i));
            processDollar(dollarStrings);
            substitutes.add(substiutedValue);
        }

        return substitutes;
    }

    

    public void processDollar(ArrayList<String> dollarStrings) {
        String typeOfSubstitution = dollarStrings.get(0);
        switch(typeOfSubstitution) {
            case "00" : // single substitution
        }
    }

    public String substitue(String source, String variable, String target) {
        return source.replaceAll(variable, target);
    } 
}
