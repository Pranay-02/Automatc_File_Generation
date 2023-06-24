package com.vnit.api.util;

import java.util.ArrayList;
import java.util.List;

public class utility {
    
    public List<String> extractSubstringsByHash(String input) {
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

     public List<String> extractTokensByDollar(String input) {
        List<String> tokens = new ArrayList<>();
        
        String[] splitTokens = input.split("\\$");
        
        for (int i = 0; i < splitTokens.length - 1; i++) {
            String token = splitTokens[i];
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        
        String lastToken = splitTokens[splitTokens.length - 1];
        if (!lastToken.isEmpty()) {
            tokens.add(lastToken);
        }
        
        return tokens;
    }

    public List<String> extractTokensBySemiColon(String input) {
        List<String> tokens = new ArrayList<>();
        
        String[] splitTokens = input.split("\\:");
        
        for (String token : splitTokens) {
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        
        return tokens;
    }
    
}
