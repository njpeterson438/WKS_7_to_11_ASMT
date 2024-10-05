package projects;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.entity.Project;  // Adjust the package name if necessary
import projects.exception.DbException;
import projects.service.ProjectService;  // Adjust the package name accordingly
import java.math.BigDecimal;

public class ProjectsApp {
	private ProjectService projectService = new ProjectService();  // Initialize ProjectService

	//@formatter:off
	private List<String> operations = List.of(
	 "1) Add a project"
	);
	//@formatter:on

	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
	    new ProjectsApp().processUserSelections();
	}

	private void processUserSelections() {
	    boolean done = false;

	    while (!done) {
	        try {
	            int selection = getUserSelection();

	            switch (selection) {
	            case -1:
	                done = exitMenu();
	                break;
	            case 1:
	                createProject();
	                break;
	            default:
	                System.out.println("\n" + selection + " is not a valid selection. Try again.");
	        }
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	    }
	}

	// Method to create a project
	private void createProject() {
	   String projectName = getStringInput("Enter the project name");
	   BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
	   BigDecimal actualHours = getDecimalInput("Enter the actual hours");
	   Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
	   String notes = getStringInput("Enter the project notes");
	    
	   // Create a new Project object
	   Project project = new Project();
	   
	   project.setProjectName(projectName);
	   project.setEstimatedHours(estimatedHours);
	   project.setActualHours(actualHours);
	   project.setDifficulty(difficulty);
	   project.setNotes(notes);

	   // Save the project to the database via ProjectService
	   Project dbProject = projectService.addProject(project);
	   System.out.println("You have successfully created project: " + dbProject);
	}

	private int getUserSelection() {
	    printOperations();
	    Integer input = getIntInput("Enter a menu selection");

	    return Objects.isNull(input) ? -1 : input;
	}

	private void printOperations() {
	    System.out.println("\nMenu Options:");
	    operations.forEach(operation -> System.out.println("  " + operation));
	}

	private Integer getIntInput(String prompt) {
	    String input = getStringInput(prompt);

	    if (Objects.isNull(input)) {
	        return null;
	    }

	    try {
	        return Integer.valueOf(input);
	    } catch (NumberFormatException e) {
	        throw new DbException(input + " is not a valid number. Try again.");
	   }
	}

	private String getStringInput(String prompt) {
	    System.out.print(prompt + ": ");
	    String input = scanner.nextLine();

	    return input.isBlank() ? null : input.trim();
	}

	private BigDecimal getDecimalInput(String prompt) {
	    String input = getStringInput(prompt);

	    if (Objects.isNull(input)) {
	        return null;
	    }

	    try {
	        return new BigDecimal(input);
	    } catch (NumberFormatException e) {
	        throw new DbException(input + " is not a valid decimal number. Try again.");
	    }
	}

	private boolean exitMenu() {
	    System.out.println("Exiting the menu.");
	    return true;
	}
}