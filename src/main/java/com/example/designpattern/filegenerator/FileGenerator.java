package com.example.designpattern.filegenerator;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

import com.example.designpattern.table.Table;

public class FileGenerator {
	String projectDestinationLocation;
	
	public FileGenerator(String projectDestinationLocation) {
		this.projectDestinationLocation = projectDestinationLocation;
	}
	
	public void generateAll(String projectOriginalLocation, List<Table> tables) {
		generatePomXml(projectOriginalLocation);
		generateEntityFiles(tables);
		generateFormFiles(tables, projectOriginalLocation + "/src/main/java/com/example/testbasicform/BaseForm.java");
		copyScreenResources(projectOriginalLocation + "/src/main/resources/screen");
	}
	
	public void generatePomXml(String projectOriginalLocation) {
        try {
        	String originalPomPath = projectOriginalLocation + "/pom.xml";
            Files.copy(Paths.get(originalPomPath), Paths.get(projectDestinationLocation + "/pom.xml"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated: " + projectDestinationLocation + "/pom.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateEntityFiles(List<Table> tables) {
        String entityPath = projectDestinationLocation + "/src/main/java/entity";
        File entityDir = new File(entityPath);
        if (!entityDir.exists()) {
            entityDir.mkdirs();
        }

        for (Table table : tables) {
            String javaClassContent = table.generateEntityClass();
            String fileName = table.getTableName() + ".java";
            String filePath = entityPath + "/" + fileName;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(javaClassContent);
                System.out.println("Generated: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateFormFiles(List<Table> tables, String originalBaseFormPath) {
        String formPath = projectDestinationLocation + "/src/main/java/form";
        File formDir = new File(formPath);
        if (!formDir.exists()) {
            formDir.mkdirs();
        }

        try {
            Files.copy(Paths.get(originalBaseFormPath), Paths.get(formPath + "/BaseForm.java"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated: " + formPath + "/BaseForm.java");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Table table : tables) {
            String formClassContent = table.generateFormClass();
            String fileName = table.getTableName() + "Form.java";
            String filePath = formPath + "/" + fileName;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(formClassContent);
                System.out.println("Generated: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyScreenResources(String originalScreenResourcePath) {
        String screenPath = projectDestinationLocation + "/src/main/resources/screen";
        File screenDir = new File(screenPath);
        if (!screenDir.exists()) {
            screenDir.mkdirs();
        }

        try {
            List<Path> files = Files.walk(Paths.get(originalScreenResourcePath))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path file : files) {
                String fileName = file.getFileName().toString();
                Files.copy(file, Paths.get(screenPath + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + screenPath + "/" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
