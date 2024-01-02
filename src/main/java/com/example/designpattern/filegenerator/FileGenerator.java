package com.example.designpattern.filegenerator;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.designpattern.table.Table;

public class FileGenerator {
	String projectOriginalLocation;
	String projectDestinationLocation;
	
	String mainLocation = "/src/main/java/com/example/designpattern/DesignpatternApplication.java";
	String dbConnectionLocation = "/src/main/java/com/example/designpattern/DatabaseConnection.java";
	String sharedVariableHolderLocation = "/src/main/java/com/example/designpattern/SharedVariableHolder.java";
	
	String tableLocation = "/src/main/java/com/example/designpattern/table";
	String columnLocation = "/src/main/java/com/example/designpattern/column";
	
	String controllerLocation = "/src/main/java/com/example/designpattern/Controller";
	List<String> controllerList = new ArrayList<>();
	
	String notificationLocation = "/src/main/java/com/example/designpattern/notification";
	
	String decoratorLocation = "/src/main/java/com/example/designpattern/decorator";
	List<String> decoratorList = new ArrayList<>();
	
	String tableHandlerLocation = "/src/main/java/com/example/tablehandler/TableController.java";
	
	String formLocation = "/src/main/java/com/example/testbasicform";
	String entityLocation = "/src/main/java/com/example/testbasicform";
	String baseFormLocation = "/src/main/java/com/example/testbasicform/BaseForm.java";
	
	String styleLocation = "/src/main/resources/css";
	String imagesLocation = "/src/main/resources/images";
	String fxmlLocation = "/src/main/resources/screen";
	
	public FileGenerator(String projectOriginalLocation, String projectDestinationLocation) {
		this.projectOriginalLocation = projectOriginalLocation;
		this.projectDestinationLocation = projectDestinationLocation;
		
		controllerList.add("TableListController.java");
		controllerList.add("FormPopupController.java");
		controllerList.add("PopupWindow.java");
		controllerList.add("ChangeAuthenticationTableController.java");
		
		decoratorList.add("TableUIUnit.java");
		decoratorList.add("IScreenUnit.java");
		decoratorList.add("HeadingUIUnit.java");
		decoratorList.add("TableListUIUnit.java");
		decoratorList.add("ScreenUnitDecorator.java");
	}
	
	public void generateAll(List<Table> tables) {
		generatePomXml();
		generateMainFile();
		copyTableAndColumnFiles();
		generateEntityFiles(tables);
		generateFormFiles(tables);
		copyScreenResources();
		copyFilesFromLocation(styleLocation);
		copyFilesFromLocation(imagesLocation);
		generateControllerFiles();
		generateNotificationFiles();
		generateDecoratorFiles();
		generateTableHandlerFile();
		copyFilesFromLocation("/src/main/java/com/example/designpattern/Default");
	}
	
	public void generatePomXml() {
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
            String javaClassContent = table.generateEntityClass(formLocation);
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
        File formDir = new File(formPath);
        if (!formDir.exists()) {
            formDir.mkdirs();
        }

        try {
            Files.copy(Paths.get(projectOriginalLocation + baseFormLocation), Paths.get(formPath + "/BaseForm.java"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Generated: " + formPath + "/BaseForm.java");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Table table : tables) {
            String formClassContent = table.generateFormClass(entityLocation, formLocation);
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

    public void copyScreenResources() {
        String screenPath = projectDestinationLocation + fxmlLocation;
        File screenDir = new File(screenPath);
        if (!screenDir.exists()) {
            screenDir.mkdirs();
        }

        try {
            List<Path> files = Files.walk(Paths.get(projectOriginalLocation + fxmlLocation))
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
    }

    public void generateNotificationFiles() {
        String notificationPath = projectDestinationLocation + notificationLocation;
        File notificationDir = new File(notificationPath);
        if (!notificationDir.exists()) {
            notificationDir.mkdirs();
        }

        try {
            List<Path> files = Files.walk(Paths.get(projectOriginalLocation + notificationLocation))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path file : files) {
                String fileName = file.getFileName().toString();
                Files.copy(file, Paths.get(notificationPath + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + notificationPath + "/" + fileName);
            }
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
        String sharedVariableHolderPath = projectDestinationLocation + sharedVariableHolderLocation;

        try {
            Files.copy(Paths.get(projectOriginalLocation + dbConnectionLocation), Paths.get(dbConnectionPath), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Paths.get(projectOriginalLocation + sharedVariableHolderLocation), Paths.get(sharedVariableHolderPath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Copied DB connection file: " + dbConnectionPath);
            System.out.println("Copied Shared Variable Holder file: " + sharedVariableHolderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyTableAndColumnFiles() {
        copyFilesFromLocation(tableLocation);
        copyFilesFromLocation(columnLocation);
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

}
