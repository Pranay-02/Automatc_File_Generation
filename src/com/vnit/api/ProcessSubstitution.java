package com.vnit.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import com.vnit.substitution.fragmentTemplate;
import com.vnit.substitution.fieldTemplate;
import com.vnit.api.entity.Object;
import com.vnit.api.util.mapsUtil;
import com.vnit.api.util.utility;

public class ProcessSubstitution {
    fieldTemplate fTemplate = new fieldTemplate();
    fragmentTemplate ffTemplate = new fragmentTemplate();
    utility ut = new utility();

    public String getCompleteTemplate(ArrayList<Object> columns) {
        String template = ""; 
        
        template += fTemplate.getTemplate();

        for (int i = 0; i < columns.size(); i++) {
            String fragments = ffTemplate.getFieldFragments(columns.get(i).getColumnName());
            template += fragments;
        }

        template += '}';
        template = processTemplate(template);

        return template;
    }

    public String processTemplate(String template) {
        ArrayList<String> hashStrings = ut.extractSubstringsByHash(template);
        ArrayList<String> substitutes = processHash(hashStrings);

        for (int i = 0; i < hashStrings.size(); i++) {
            String var = "#" + hashStrings.get(i) + "#";
            template = substitue(template, var, substitutes.get(i));
        }
        
        return template;

    }

    public ArrayList<String> processHash(ArrayList<String> hashStrings) {
        ArrayList<String> substitutes = new ArrayList<>();
        for (int i = 0; i < hashStrings.size(); i++) {
            String substiutedValue = "";
            ArrayList<String> dollarStrings = ut.extractTokensByDollar(hashStrings.get(i));
            substiutedValue = processDollar(dollarStrings);
            substitutes.add(substiutedValue);
        }

        return substitutes;
    }

    public String processDollar(ArrayList<String> dollarStrings) {
        String typeOfSubstitution = dollarStrings.get(1);
        String substiutedValue = "";
        switch (typeOfSubstitution) {
            case "01": // substitution from single map
                String tobeSubstiuted = dollarStrings.get(2);
                ArrayList<String> semiColonStrings = ut.extractTokensBySemiColon(tobeSubstiuted);
                substiutedValue = processSemiColon(semiColonStrings);
                break;
            case "02": // substitution from map of map
                String maptobesubstiuted = dollarStrings.get(2);
                ArrayList<String> semicolonStrings = ut.extractTokensBySemiColon(maptobesubstiuted);
                substiutedValue = processSemiColon(semicolonStrings);
                break;
        }

        return substiutedValue;
    }

    public String processSemiColon(ArrayList<String> semiColonStrings) {
        String literal = semiColonStrings.get(0);
        String substitutedString = "";
        switch(literal) {
            case "m" : // substitute from single map
                String mapToBeUsed = semiColonStrings.get(1);
                String keyOfMap = semiColonStrings.get(2);
                Map<String, ?> map = getMap(mapToBeUsed);
                substitutedString = String.valueOf(map.get(keyOfMap));                    
                break;
            case "c" : // substitute a constant
                substitutedString = semiColonStrings.get(1);
                break;
        }

        return substitutedString;
    }

    public String substitue(String source, String variable, String target) {
        StringBuilder sb = new StringBuilder(source);
        int startIndex = sb.indexOf(variable);
        int endIndex = startIndex + variable.length();
        String sub =  sb.replace(startIndex, endIndex, target).toString();

        return sub;
    }

    public Map<String, ?> getMap(String mapName) {
        try {
            Class<?> declaringClass = mapsUtil.class;  
            Field field = declaringClass.getDeclaredField(mapName);

            @SuppressWarnings("unchecked")
            Map<String, ?> map = (Map<String, ?>) field.get(null);
            return map;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
