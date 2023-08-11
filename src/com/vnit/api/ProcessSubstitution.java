package com.vnit.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import com.vnit.substitution.entityFragmentTemplate;
import com.vnit.substitution.ControllerTemplate;
import com.vnit.substitution.CssTemplate;
import com.vnit.substitution.HTMLTemplate;
import com.vnit.substitution.RepoTemplate;
import com.vnit.substitution.SpecTSTemplate;
import com.vnit.substitution.TSTemplate;
import com.vnit.substitution.entityFieldTemplate;
import com.vnit.api.entity.Object;
import com.vnit.api.util.mapsUtil;
import com.vnit.api.util.utility;

public class ProcessSubstitution {
    entityFieldTemplate fTemplate = new entityFieldTemplate();
    entityFragmentTemplate ffTemplate = new entityFragmentTemplate();
    RepoTemplate rTemplate = new RepoTemplate();
    ControllerTemplate cTemplate = new ControllerTemplate();
    HTMLTemplate htmlTemplate = new HTMLTemplate();
    CssTemplate cssTemplate = new CssTemplate();
    SpecTSTemplate specTSTemplate = new SpecTSTemplate();
    TSTemplate tsTemplate = new TSTemplate();
    utility ut = new utility();

    public String getEntityTemplate(ArrayList<Object> columns) {
        String template = ""; 
        
        template += fTemplate.getTemplate();

        for (int i = 0; i < columns.size(); i++) {
            String fragments = ffTemplate.getFieldFragments(columns.get(i).getColumnName());
            template += fragments;
        }

        template += fTemplate.getClosingBracket();
        template = processTemplate(template);

        return template;
    }

    public String getRepoTemplate(String columnName) {
        String template = "";
        
        template += rTemplate.getRepoFieldTemplate();
        template += rTemplate.getObjectTemplate();
        template += rTemplate.postObjectTemplate(columnName);
        template += rTemplate.deleteObjectTemplate();
        template += rTemplate.getClosingBracket();

        template = processTemplate(template);

        return template;
    }

    public String getControllerTemplate(ArrayList<Object> columns, String primaryColumnName) {
        String template = "";
        
        template += cTemplate.getControllerFieldTemplate();
        template += cTemplate.getControllerCreateTemplate(columns, primaryColumnName);
        template += cTemplate.getControllerDeleteTemplate(primaryColumnName);
        template += cTemplate.getControllerGetTemplate(primaryColumnName);
        template += cTemplate.getClosingBracket();
        template = processTemplate(template);
        
        return template;
    }

    public String getHtmlTemplate(ArrayList<Object> columns) {
        String template = "";

        template += htmlTemplate.getFormPart1();
        for(int i = 0; i < columns.size(); i++) {
            template += htmlTemplate.getFormFragment1(columns.get(i).getColumnName());
        }
        template += htmlTemplate.getFormPart2();
     
        template += getHtmlTemplateHelper(columns);
        template += htmlTemplate.getFormPart5();

        template += htmlTemplate.getListingTablePart1();
        template += getHtmlTemplateHelper(columns);
        template += htmlTemplate.getListingTablePart2();

        template += htmlTemplate.getSaveCancelButton();
        template += htmlTemplate.getDeleteDialog();

        template = processTemplate(template);
        return template;
    }

    public String getHtmlTemplateHelper(ArrayList<Object> columns) {
        String template = "";

        template += htmlTemplate.getFormPart3();
        for(int i = 0; i < columns.size(); i++) {
            template += htmlTemplate.getFormFragment2(columns.get(i).getColumnName());
        }

        template += htmlTemplate.getFormPart4();
        for(int i = 0; i < columns.size(); i++) {
            template += htmlTemplate.getFormFragment3(columns.get(i).getColumnName());
        }

        return template;

    }
    
    public String getCSSTemplate() {
        String template = "";

        template += cssTemplate.getCssString();
        return template;
    }

    public String getSpecTSTemplate() {
        String template = "";
        template += specTSTemplate.getSpecTSString();
        template = processTemplate(template);
        return template;
    }

    public String getTSTemplate(ArrayList<Object> columns) {
        String template = "";
        template += tsTemplate.getTSString(columns);
        template = processTemplate(template);
        return template;
    }

    public String processTemplate(String template) {
        ArrayList<String> hashStrings = ut.extractSubstringsByHash(template);

        for (int i = 0; i < hashStrings.size(); i++) {
            String hashString = hashStrings.get(i); 
            String substitutedString = "";
            if(mapsUtil.substitutionMap.containsKey(hashString)) {
                substitutedString = mapsUtil.substitutionMap.get(hashString);
            }
            else {
                substitutedString = processHash(hashString);
                mapsUtil.substitutionMap.put(hashString, substitutedString);
            }

            String var = "^" + hashString + "^";
            template = substitue(template, var, substitutedString);
        }
        
        return template;

    }

    public String processHash(String hashStrings) {
        String substiutedValue = "";
        ArrayList<String> dollarStrings = ut.extractTokensByDollar(hashStrings);
        substiutedValue = processTypeOfSubstitution(dollarStrings);
    
        return substiutedValue;
    }

    public String processTypeOfSubstitution(ArrayList<String> dollarStrings) {
        String typeOfSubstitution = dollarStrings.get(0);
        String substituedString = "";
        switch(typeOfSubstitution) {
            case "00": //single substitution
                substituedString = processDollar(dollarStrings);
                break;
        }
        return substituedString;

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
            case "02": // substitution from 2 places
                String sub = dollarStrings.get(2);
                ArrayList<String> semicolonstrings = ut.extractTokensBySemiColon(sub);
                String condition = processSemiColon(semicolonstrings);
                if(condition.equals("true")) {
                    sub = dollarStrings.get(3);
                    ArrayList<String> constData = ut.extractTokensBySemiColon(sub);
                    substiutedValue = processSemiColon(constData);
                }
                
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
            
                case "m2" : // substitute from map of map
                String parentMapString = semiColonStrings.get(1);
                String keyOfParentMap = semiColonStrings.get(2);
                String keyOfChildMap = semiColonStrings.get(3);

                Map<String, ?> parentmap = getMap(parentMapString);
                Map<String, String> childMap = (Map<String, String>) parentmap.get(keyOfParentMap);
                substitutedString = childMap.get(keyOfChildMap);
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
