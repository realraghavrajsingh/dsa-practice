package com.leetcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JsonReaderUtil {

    private static final String NOT_FOUND = "[Not Found]";
    private static final String OUTPUT_DIR = "output";

    public static void extractFieldsFromJson(String jsonString, List<String> fieldNames) {
        try {
            Path outputPath = Paths.get(OUTPUT_DIR);
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }

            for (String fieldName : fieldNames) {
                String value = findFieldValue(jsonString, fieldName);
                String outputValue = (value != null ? value : NOT_FOUND);

                String fileName = fieldName + ".txt";
                Path filePath = outputPath.resolve(fileName);
                System.out.println(fieldName + ": " + outputValue);
                Files.writeString(filePath, outputValue, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                System.out.println("Written field '" + fieldName + "' to file: " + filePath);
            }

            System.out.println("\nAll fields extracted successfully to: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Failed to write field values to files", e);
        }
    }

    public static void extractFieldsFromResource(String resourcePath, List<String> fieldNames) {
        try (InputStream inputStream = JsonReaderUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            String jsonString = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            extractFieldsFromJson(jsonString, fieldNames);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON resource: " + resourcePath, e);
        }
    }

    public static void extractFieldsFromFile(String filePath, List<String> fieldNames) {
        try {
            String jsonString = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
            extractFieldsFromJson(jsonString, fieldNames);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    private static String findFieldValue(String json, String fieldName) {
        Pattern stringPattern = Pattern.compile(
                "\\\"" + Pattern.quote(fieldName) + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"",
                Pattern.DOTALL
        );
        Matcher m = stringPattern.matcher(json);
        if (m.find()) {
            return m.group(1);
        }

        Pattern primitivePattern = Pattern.compile(
                "\\\"" + Pattern.quote(fieldName) + "\\\"\\s*:\\s*([-+]?[0-9]*\\.?[0-9]+|true|false|null)",
                Pattern.CASE_INSENSITIVE
        );
        m = primitivePattern.matcher(json);
        if (m.find()) {
            return m.group(1);
        }

        return null;
    }

    public static void main(String[] args) {
        List<String> fields = List.of("frontBase64Image", "backBase64Image");

        if (args.length > 0) {
            String filePath = args[0];
            System.out.println("Reading JSON from file: " + filePath);
            extractFieldsFromFile(filePath, fields);
        } else {
            String resource = "json.txt"; // located under src/main/resources
            System.out.println("No file path provided. Reading JSON from classpath resource: " + resource);
            extractFieldsFromResource(resource, fields);
        }
    }
}
