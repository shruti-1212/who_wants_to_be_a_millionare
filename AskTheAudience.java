package project;

import java.util.*;

public class AskTheAudience {

	public void askTheAudience(Question[] questions, int questionIndex) {
		
		System.out.println();
		System.out.println("--- Ask the Audience Lifeline ---");
		System.out.println();

		System.out.println("Audience is getting ready to vote...");
		System.out.println("Audience is thinking...");
		System.out.println("Audience is submitting their votes...");

		// Simulate audience votes
		Random random = new Random();
		
		//create a boolean which when true, ensures the correct option has the highest vote and when false, ensures a wrong option has the highest votes
		boolean isCorrect = random.nextBoolean();
		
		int correctAnswerPercentage;
		int remainingPercentage;
		
		if(isCorrect) {
			correctAnswerPercentage = random.nextInt(31) + 50; // Correct answer gets 50% to 81%
			remainingPercentage = 100 - correctAnswerPercentage;
		}else {
			remainingPercentage = random.nextInt(31) + 60; //This is distributed amongst the wrong options
			correctAnswerPercentage = 100 - remainingPercentage;
		}

		// Generate percentages for incorrect options...
		int option1 = random.nextInt(remainingPercentage - 2); 
		remainingPercentage -=option1;
		int option2 = random.nextInt(remainingPercentage - 1);
		remainingPercentage -=option2;
		int option3 = remainingPercentage;

		// Store options in a list
		List<String> options = new ArrayList<>(List.of("A", "B", "C", "D"));

		// Get the correct answer letter
		String correctAnswerLetter = questions[questionIndex].getAnswerLetter().toUpperCase();
		// Variables to store the percentages for each option
		int percentA = 0, percentB = 0, percentC = 0, percentD = 0;

		// Assign the correct answer percentage to the correct option
		if (correctAnswerLetter.equals("A")) {
			percentA = correctAnswerPercentage;
		} else if (correctAnswerLetter.equals("B")) {
			percentB = correctAnswerPercentage;
		} else if (correctAnswerLetter.equals("C")) {
			percentC = correctAnswerPercentage;
		} else if (correctAnswerLetter.equals("D")) {
			percentD = correctAnswerPercentage;
		}
		// Remove the correct answer letter from the options list
		options.remove(correctAnswerLetter);

		// Assign remaining percentages to the other options
		int[] remainingPercentages = {option1, option2, option3};
		int index = 0;

		// Use while loop to assign remaining percentages to the options
		while (!options.isEmpty()) {
			String letter = options.remove(0); // Get the next option from the list
			if (letter.equals("A") && percentA == 0) {
				percentA = remainingPercentages[index];
			} else if (letter.equals("B") && percentB == 0) {
				percentB = remainingPercentages[index];
			} else if (letter.equals("C") && percentC == 0) {
				percentC = remainingPercentages[index];
			} else if (letter.equals("D") && percentD == 0) {
				percentD = remainingPercentages[index];
			}
			index++;
		}

		System.out.println();
		System.out.println("<<------Audience Votes are as follows------>>");
		System.out.println("A -->> " + percentA + "%");
		System.out.println("B -->> " + percentB + "%");
		System.out.println("C -->> " + percentC + "%");
		System.out.println("D -->> " + percentD + "%");
	}
}

