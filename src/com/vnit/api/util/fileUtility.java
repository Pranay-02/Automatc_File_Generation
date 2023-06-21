package com.vnit.api.util;

import java.io.*;

public class fileUtility {
    public String getFileAsString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
        return contentBuilder.toString();

    }

    public void createFileFromString(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
        catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    public String substitue(String source, String variable, String target) {
        return source.replaceAll(variable, target);
    }

    public String getFilePath() {
        return "D:\\VNIT 2nd year\\Project\\Task\\src\\com\\vnit\\api\\";
    }
}
