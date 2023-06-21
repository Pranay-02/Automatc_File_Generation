package com.vnit.api.util;

import java.util.ArrayList;
import java.util.List;

public class utility {
    
    public List<String> extractSubstrings(String input) {
        List<String> substrings = new ArrayList<>();
        int startIndex = input.indexOf('#');
        
        while (startIndex != -1) {
            int endIndex = input.indexOf('#', startIndex + 1);
            
            if (endIndex != -1) {
                String substring = input.substring(startIndex + 1, endIndex);
                substrings.add(substring);
            }
            
            startIndex = input.indexOf('#', endIndex + 1);
        }
        
        return substrings;
    }
    
}
