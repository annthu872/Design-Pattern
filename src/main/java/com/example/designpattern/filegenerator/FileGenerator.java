package com.example.designpattern.filegenerator;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.table.Table;
import com.example.designpattern.Default.Authentication;

public class FileGenerator {
	String projectOriginalLocation;
	String projectDestinationLocation;
	
	String mainLocation = "/src/main/java/com/example/designpattern/DesignpatternApplication.java";
	String dbConnectionLocation = "/src/main/java/com/example/designpattern/DatabaseConnection.java";
	String sharedVariableHolderLocation = "/src/main/resources/template/dbVariable.txt";
	
	String controllerLocation = "/src/main/java/com/example/designpattern/Controller";
	List<String> controllerList = new ArrayList<>();
	
	String decoratorLocation = "/src/main/java/com/example/designpattern/decorator";
	List<String> decoratorList = new ArrayList<>();
	
	String tableHandlerLocation = "/src/main/java/com/example/tablehandler/TableController.java";
	
	String formLocation = "/src/main/java/com/example/userform";
	String entityLocation = "/src/main/java/com/example/userclass";
	String baseFormLocation = "/src/main/java/com/example/baseform/BaseForm";
	
	public FileGenerator(String projectOriginalLocation, String projectDestinationLocation) {
		this.projectOriginalLocation = projectOriginalLocation;
		this.projectDestinationLocation = projectDestinationLocation;
		
		controllerList.add("TableListController.java");
		controllerList.add("FormPopupController.java");
		
		decoratorList.add("TableUIUnit.java");
		decoratorList.add("IScreenUnit.java");
		decoratorList.add("HeadingUIUnit.java");
		decoratorList.add("TableListUIUnit.java");
		decoratorList.add("ScreenUnitDecorator.java");
	}
	
	public void generateAll(List<Table> tables) {
		generatePomXml();
		generateMainFile();
		copyFilesFromLocation("/src/main/java/com/example/designpattern/table");
		copyFilesFromLocation("/src/main/java/com/example/designpattern/column");
		generateEntityFiles(tables);
		generateFormFiles(tables);
		copyFilesFromLocation("/src/main/resources/css");
		copyFilesFromLocation("/src/main/resources/images");
		copyFilesFromLocation("/src/main/resources/screen");
		generateControllerFiles();
		copyFilesFromLocation("/src/main/java/com/example/designpattern/notification");
		generateDecoratorFiles();
		generateTableHandlerFile();
		copyFilesFromLocation("/src/main/java/com/example/registry");
		generateAuthentication("/src/main/java/com/example/designpattern/Default");
		copyFilesFromLocation("/src/main/java/IoCContainer");
	}
	
	public void generatePomXml() {
		String destinationPath = projectDestinationLocation + "/pom.xml";
        File destinationDir = new File(destinationPath);
        if (!destinationDir.exists()) {
        	destinationDir.mkdirs();
        }
        try {
        	String originalPomPath = projectOriginalLocation + "/src/main/resources/template/pom.txt";
            Files.copy(Paths.get(originalPomPath), Paths.get(projectDestinationLocation + "/pom.xml"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated: " + projectDestinationLocation + "/pom.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateEntityFiles(List<Table> tables) {
        String entityPath = projectDestinationLocation + entityLocation;
        File entityDir = new File(entityPath);
        if (!entityDir.exists()) {
            entityDir.mkdirs();
        }

        for (Table table : tables) {
            String javaClassContent = table.generateEntityClass(entityLocation);
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

    public void generateFormFiles(List<Table> tables) {
        String formPath = projectDestinationLocation + formLocation;
        File baseFormDir = new File(projectDestinationLocation + "/src/main/java/com/example/baseform");
        if (!baseFormDir.exists()) {
        	baseFormDir.mkdirs();
        }
        File formDir = new File(formPath);
        if (!formDir.exists()) {
            formDir.mkdirs();
        }

        try {
            Files.copy(Paths.get(projectOriginalLocation + baseFormLocation + ".java"), Paths.get(projectDestinationLocation + baseFormLocation + ".java"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated: " + formPath + "/BaseForm.java");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Table table : tables) {
            String formClassContent = table.generateFormClass(entityLocation, formLocation, baseFormLocation);
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
    
    public void generateControllerFiles() {
        String controllerPath = projectDestinationLocation + controllerLocation;
        File controllerDir = new File(controllerPath);
        if (!controllerDir.exists()) {
            controllerDir.mkdirs();
        }

        for (String controller : controllerList) {
            try {
                Files.copy(Paths.get(projectOriginalLocation + controllerLocation + "/" + controller), Paths.get(controllerPath + "/" + controller), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Generated: " + controllerPath + "/" + controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
            Files.copy(Paths.get(projectOriginalLocation + "/src/main/resources/template/PopupWindow.txt"), Paths.get(controllerPath + "/PopupWindow.java"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated: " + controllerPath + "/PopupWindow.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateDecoratorFiles() {
        String decoratorPath = projectDestinationLocation + decoratorLocation;
        File decoratorDir = new File(decoratorPath);
        if (!decoratorDir.exists()) {
            decoratorDir.mkdirs();
        }

        for (String decorator : decoratorList) {
            try {
                Files.copy(Paths.get(projectOriginalLocation + decoratorLocation + "/" + decorator), Paths.get(decoratorPath + "/" + decorator), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Generated: " + decoratorPath + "/" + decorator);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void generateTableHandlerFile() {
        String tableHandlerPath = projectDestinationLocation + tableHandlerLocation;
        File tableHandlerDir = new File(tableHandlerPath);
        if (!tableHandlerDir.exists()) {
        	tableHandlerDir.mkdirs();
        }
        try {
            Files.copy(Paths.get(projectOriginalLocation + tableHandlerLocation), Paths.get(tableHandlerPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied: " + tableHandlerPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void generateMainFile() {
        String mainPath = projectDestinationLocation + mainLocation;
        File mainDir = new File(mainPath);
        if (!mainDir.exists()) {
        	mainDir.mkdirs();
        }
        try {
            Files.copy(Paths.get(projectOriginalLocation + "/src/main/resources/template/main.txt"), Paths.get(mainPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated main file: " + mainPath);
            copyDBConnectionAndSharedVariableHolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyDBConnectionAndSharedVariableHolder() {
        String dbConnectionPath = projectDestinationLocation + dbConnectionLocation;

        try {
            Files.copy(
            		Paths.get(projectOriginalLocation + dbConnectionLocation), 
            		Paths.get(dbConnectionPath), 
            		StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied DB connection file: " + dbConnectionPath);
            System.out.println(SharedVariableHolder.database + " " +
            		SharedVariableHolder.url + " " +
            		SharedVariableHolder.user + " " +
            		SharedVariableHolder.password);
            generateSharedVariableHolder(
            		SharedVariableHolder.database, 
            		SharedVariableHolder.url, 
            		SharedVariableHolder.user, 
            		SharedVariableHolder.password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void generateSharedVariableHolder(String database, String url, String user, String password) {
        String sharedVariableHolderTemplatePath = projectOriginalLocation + sharedVariableHolderLocation;
        String sharedVariableHolderPath = projectDestinationLocation + "/src/main/java/com/example/designpattern/SharedVariableHolder.java";

        try (BufferedReader reader = new BufferedReader(new FileReader(sharedVariableHolderTemplatePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(sharedVariableHolderPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("<DATABASE>", database)
                           .replace("<URL>", url)
                           .replace("<USERNAME>", user)
                           .replace("<PASSWORD>", password);
                writer.write(line + "\n");
            }

            System.out.println("Generated Shared Variable Holder file: " + sharedVariableHolderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFilesFromLocation(String sourceLocation) {
        String destinationPath = projectDestinationLocation + sourceLocation;
        File destinationDir = new File(destinationPath);
        if (!destinationDir.exists()) {
        	destinationDir.mkdirs();
        }
        try {
            List<Path> files = Files.walk(Paths.get(projectOriginalLocation + sourceLocation))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path file : files) {
                String fileName = file.getFileName().toString();
                Files.copy(file, Paths.get(destinationPath + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + destinationPath + "/" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateAuthentication(String authenticationLocation) {
        String destinationPath = projectDestinationLocation + authenticationLocation;
        File destinationDir = new File(destinationPath);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        try {
            List<Path> files = Files.walk(Paths.get(projectOriginalLocation + authenticationLocation))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path file : files) {
                String fileName = file.getFileName().toString();

                if (fileName.equals("Authentication.java")) {
                    // Read the template content
                    String templateContent = Files.readString(Paths.get(projectOriginalLocation + "/src/main/resources/template/Authentication.txt"));

                    // Replace placeholders with actual values
                    templateContent = templateContent.replace("<USERS>", Authentication.userstableName)
                            .replace("<RESETPASSWORD>", Authentication.resetPasswordTableName)
                            .replace("<USERNAME>", Authentication.usernameColumnName)
                            .replace("<PASSWORD>", Authentication.passwordColumnName);

                    // Write the modified content to the destination file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath + "/" + fileName))) {
                        writer.write(templateContent);
                        System.out.println("Generated and templated: " + destinationPath + "/" + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Files.copy(file, Paths.get(destinationPath + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied: " + destinationPath + "/" + fileName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}