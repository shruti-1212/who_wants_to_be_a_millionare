package project;

import java.util.*;

public class LifeLines {
	
	ArrayList<String> lifelinesList;
	private Scanner sc;
	
	public LifeLines(Scanner sc) {
		this.sc = sc;
		lifelinesList = new ArrayList<>();
		lifelinesList.add("Fifty-Fifty");
		lifelinesList.add("Phone a Friend");
		lifelinesList.add("Ask the Audience");
	}
	
	//List the lifelines available
	public void availableLifelines() {
		System.out.println();
		System.out.println("Available lifelines:");
		char letter ='A';
		for(String lifeline: lifelinesList) {
			System.out.println(letter + ". " + lifeline);
			letter++;
		}
	}
	
	
	//Prompt to ask player if they would like to use a lifeline
	public String promptLifeline() {
	    if (lifelinesList.isEmpty()) {
	        System.out.println("You have no more lifelines.");
	        return null;
	    }

	    // Prompt user to decide whether to use a lifeline
	    System.out.println("Would you like to use a lifeline? (Y/N)");
	    System.out.print("Enter response => ");
	    String response = sc.nextLine().trim().toUpperCase();

	    // Loop until a valid "Y" or "N" is entered
	    while (!response.equals("Y") && !response.equals("N")) {
	        System.out.println("Invalid response. Please enter 'Y' or 'N'.");
	        System.out.print("Enter response => ");
	        response = sc.nextLine().trim().toUpperCase();
	    }

	    // If the user doesn't want to use a lifeline, exit early
	    if (response.equals("N")) {
	        System.out.println();
	        System.out.println("No lifeline has been chosen.");
	        System.out.println();
	        return null;
	    }

	    // Keep prompting until a valid input is entered
	    while (true) {
		    // Display available lifelines
		    availableLifelines();
		    
		    // Prompt user to choose a lifeline
		    System.out.println("Enter the letter of the lifeline (or press Enter to skip):");
		    System.out.print("Enter response => ");
		    String choice = sc.nextLine().trim().toUpperCase();
		    
		    //Check if user choice is empty
	        if (choice.isEmpty()) {
	            System.out.println();
	            System.out.println("No lifeline has been chosen.");
	            System.out.println();
	            return null;
	        }

	        // Validate the choice against available lifelines
	        String lifelineChoice = null;
	        switch (choice) {
	            case "A":
	                if (lifelinesList.size() > 0) {
	                    lifelineChoice = lifelinesList.get(0);
	                }
	                break;
	            case "B":
	                if (lifelinesList.size() > 1) {
	                    lifelineChoice = lifelinesList.get(1);
	                }
	                break;
	            case "C":
	                if (lifelinesList.size() > 2) {
	                    lifelineChoice = lifelinesList.get(2);
	                }
	                break;
	            default:
	                System.out.println("Invalid input. Please enter a valid letter");
	                continue; //This re-prompts the user if the input is invalid
	        }
	        
	        // Re-prompt if the chosen lifeline is unavailable 
	        if (lifelineChoice == null) {
	            System.out.println("Invalid choice. Please choose a valid option.");
	            continue; 
	        }
	        
	        // Confirm the lifeline choice
	        System.out.println("You chose: " + lifelineChoice + ". Do you want to proceed? (Y/N)");
	        System.out.print("Enter response => ");
	        String confirm = sc.nextLine().trim().toUpperCase();
	        
	        //Validate user response (Y/N)
	        while (!confirm.equals("Y") && !confirm.equals("N")) {
	            System.out.println("Invalid response. Please enter 'Y' or 'N'.");
	            System.out.print("Enter response => ");
	            confirm = sc.nextLine().trim().toUpperCase();
	        }
	        
	        // Return the confirmed lifeline
	        if (confirm.equals("Y")) {
	            lifelinesList.remove(lifelineChoice);
	            return lifelineChoice; 
	        }
	        
	        System.out.println("No lifeline chosen. You can choose again.");
	    }
	}
	
	public void handleLifeline(String lifelineChoice, Question[] questions, int questionIndex){
		if(lifelineChoice == null) {
			return;
			}
		
		switch(lifelineChoice) {
		case "Fifty-Fifty":
			FiftyFifty fiftyfifty = new FiftyFifty();
			fiftyfifty.useFiftyFifty(questions, questionIndex);
			break;
		case "Phone a Friend":
			PhoneAFriend phoneAfriend = new PhoneAFriend();
			phoneAfriend.useLifeline(questions, questionIndex);
			break;
		case "Ask the Audience":
				AskTheAudience askTheAudience = new AskTheAudience();
				askTheAudience.askTheAudience(questions, questionIndex);
			break;
		}
	}
}

