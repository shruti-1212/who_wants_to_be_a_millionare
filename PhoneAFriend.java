package project;

import java.util.Random;
import java.util.Scanner;

public class PhoneAFriend {

	private final String[] friendNames = {"Mansi", "Shruti", "Ndametem", "Richard"};
	private Random random = new Random();


	// Simulate the "Phone a Friend" lifeline
	public String useLifeline(Question[] questions, int questionIndex) {

		System.out.println();
		System.out.println("--- Phone a Friend Lifeline ---");
		System.out.println();

		System.out.println("Which friend would you like to call? \nHere is the list of your friends provided by you: ");

		String friendName = friendcalling();

		String question = questions[questionIndex].getQuestion();
		String options =  questions[questionIndex].getOptions();
		String correctAnswer = questions[questionIndex].getAnswer();

		System.out.println("Calling your friend " + friendName + " for help...");

		System.out.println("Hi " +  friendName + " , I'm currently on the Who Wants to be a Millionare show and i need your help in answering the following question: ");
		System.out.println(question);
		System.out.println(options);

		// Call the friend for advice
		return simulateFriendAnswer(question, options, correctAnswer, friendName);

		//	        return "Sorry, your friend couldn't help.";
	}

	//friend selection and calling
	private String friendcalling() {
		// Display the list of friends
		for (int i = 0; i < friendNames.length; i++) {
			System.out.println(friendNames[i]);
		}

		Scanner sc = new Scanner(System.in);
		Boolean checkingName = true;
		while(checkingName) {
			String name = sc.nextLine().trim(); 

			// Check if the input matches any friend's name (case-insensitive)
			for (int i = 0; i < friendNames.length; i++) {
				if (friendNames[i].equalsIgnoreCase(name)) {
					return friendNames[i]; // Return the correctly matched friend's name
				}
			}
			System.out.println("Invalid friend name. Please choose a name from the list provided.");
		}
		return null;
	}



	// Simulate the friend's answer with some randomness
	private String simulateFriendAnswer(String question, String options, String correctAnswer, String friendName) {
		System.out.println("\nFriend: Hmm, let me think...");
		//System.out.println("Friend: I've seen this question before!");

		// Introduce randomness: 60% chance the friend gives the correct answer, 40% chance they guess.
		boolean isCorrect = random.nextInt(100) < 60;

		if (isCorrect) {
			System.out.println("Friend: I'm pretty sure the answer is " + correctAnswer.split("\\.")[0] + ".");
			return correctAnswer.split("\\.")[0];
		} else {
			// Randomly pick an incorrect option
			String[] allOptions = options.split("  ");
			String randomOption;

			do {
				randomOption = allOptions[random.nextInt(allOptions.length)].split("\\.")[0];
			} while (randomOption.equals(correctAnswer.split("\\.")[0]));

			System.out.println("Friend: I think the answer might be " + randomOption + ".");
			return randomOption;
		}
	}
}
