package project;

import java.util.ArrayList;
import java.util.*;

public class FiftyFifty {

	public void useFiftyFifty(Question[] questions, int questionIndex) {
		
		System.out.println();
		System.out.println("--- Fifty-Fifty Lifeline ---");
		System.out.println();

		//Get the options for the current question
		String[] options = questions[questionIndex].getOptions().split("  ");

		//Get correct answer option
		String correctAnswerOption = questions[questionIndex].getAnswerLetter();

		// Create temporary lists to hold wrong options and new options in the right order.
		ArrayList<String> wrongOptions = new ArrayList<>();
		ArrayList<String> newOptions = new ArrayList<>();

		for(String option : options) {
			if(option.startsWith(correctAnswerOption)) {
				newOptions.add(option);
			}else
				wrongOptions.add(option);
		}

		//Randomly select a wrong option to display alongside the correct option
		Random random = new Random();
		int indexToKeep = random.nextInt(wrongOptions.size());
		String wrongOptionKept = wrongOptions.get(indexToKeep);

		//Add wrong option to the newOptions to display
		newOptions.add(wrongOptionKept);

		//Reorder the new options to display them in an alphabetical order
		Collections.sort(newOptions);

		//Display question again with the new options
		System.out.println(questions[questionIndex].getQuestion());
		System.out.println(String.join("  ", newOptions));
	}
}
