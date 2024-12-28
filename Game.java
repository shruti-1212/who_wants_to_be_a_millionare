package project;

import java.util.Scanner;

public class Game {

	private Scanner sc = new Scanner(System.in);
	private String playerName;
	private QuestionBank questionBank;
	private Question[] questions;
	private LifeLines lifeline;

	public Game() {
		questionBank = new QuestionBank();
		lifeline = new LifeLines(sc);
	}

	public void start() {
		menu();
		// Resource close.
		sc.close();
	}

	private void menu() {
		// Loops the main menu until a valid option is entered.
		boolean loop = true;

		while (loop == true) {
			System.out.println("=============================");
			System.out.println("Who Wants To Be A Millionaire");
			System.out.println("=============================");
			System.out.println("S) Start the game");
			System.out.println("R) View the rules of the game");
			System.out.println("X) Exit");
			System.out.print("Enter an option >> ");
			// We want to consume the next line character so the returnToMenu can work.
			String userMenuInput = sc.nextLine().trim();
			userMenuInput = userMenuInput.toUpperCase();

			// Display the next menu(s).
			switch (userMenuInput) {
			case "S": // Start the game, ask for name!
				loop = false; // Stop looping the main menu.
				System.out.println("===========");
				System.out.println("Player Name");
				System.out.println("===========");
				System.out.print("Please enter your name >> ");
				playerName = sc.nextLine().trim();
				System.out.println();
				System.out.println("Welcome " + playerName + "!");

				// Ask for difficulty.
				System.out.println("====================");
				System.out.println("Difficulty Selection");
				System.out.println("====================");
				diffMenu();

				// Clear the buffer, just in case there's any leftover newline
				String userDiffInput = sc.nextLine().trim();

				// Handles difficulty selection.
				if (userDiffInput.toUpperCase().equals("E")) {
					easyMode();
				} else if (userDiffInput.toUpperCase().equals("H")) {
					hardMode();
				} else {
					System.out.println("Returning to main menu");
					loop = true;
				}
				break;
			case "R": // Game rules.
				System.out.println("==========");
				System.out.println("Game Rules");
				System.out.println("==========");

				rulesDisplay();

				// Display a prompt to return to the main menu.
				returnToMenu();
				break;
			case "X": // Exit.
				System.out.println("Exiting program.");
				loop = false;
				return;
			default: // Everything else.
				System.out.println("Invalid option. Menu reload.");
				break;
			}

		}
	}

	private void diffMenu() {
		System.out.println("E) Easy");
		System.out.println("H) Hard");
		System.out.println("X) Return to main menu");
		System.out.print("Enter an option >> ");
	}

	private void rulesDisplay() {
		System.out.println("1. The game offers two difficulty levels:");
		System.out.println("   - Easy: 9 questions in total, divided into 3 rounds.");
		System.out.println("   - Hard: 15 questions in total, divided into 3 rounds.");
		System.out.println();

		System.out.println("2. Prize money increases with each correct answer:");
		System.out.println("   - Easy Mode:");
		System.out.println("     - Round 1: Questions 1-3 ($100, $500, $1,000).");
		System.out.println("     - Round 2: Questions 4-6 ($8,000, $16,000, $32,000).");
		System.out.println("     - Round 3: Questions 7-9 ($125,000, $500,000, $1,000,000).");
		System.out.println("   - Hard Mode:");
		System.out.println("     - Round 1: Questions 1-5 ($100, $200, $300, $500, $1,000).");
		System.out.println("     - Round 2: Questions 6-10 ($2,000, $4,000, $8,000, $16,000, $32,000).");
		System.out.println("     - Round 3: Questions 11-15 ($64,000, $125,000, $250,000, $500,000, $1,000,000).");
		System.out.println();

		System.out.println("3. Questions are selected randomly from a bank of at least 25 questions.");
		System.out.println("   - No question will be repeated during the game.");
		System.out.println();

		System.out.println("4. Lifelines are available to help you:");
		System.out.println("   - 50/50: Eliminates two incorrect options.");
		System.out.println("   - Ask the Audience: Simulates audience suggestions.");
		System.out.println("   - Phone a Friend: Simulates advice from a friend.");
		System.out.println("   - Lifelines are restricted as follows:");
		System.out.println("     - Easy Mode: Available in all rounds.");
		System.out.println("     - Hard Mode: Available only in Rounds 2 and 3.");
		System.out.println("   - Each lifeline can be used only once per game.");
		System.out.println();

		System.out.println("5. Players must confirm their answer before final submission.");
		System.out.println("   - You can change your answer before confirming.");
		System.out.println();

		System.out.println("6. You may choose to 'walk away' after completing certain rounds:");
		System.out.println("   - Easy Mode: After Rounds 1 and 2.");
		System.out.println("   - Hard Mode: After Rounds 1 and 2.");
		System.out.println();

		System.out.println("7. The game ends if you answer any question incorrectly.");
		System.out.println("   - Incorrect answers result in no prize money.");
		System.out.println();

		System.out.println("8. Completing all questions in Round 3 wins the game and $1,000,000.");
		System.out.println();
	}

	private void returnToMenu() {
		System.out.println("Enter any key to return to main menu >> ");
		sc.nextLine();
	}

	
	
	//method used in easymode and hardmode fnc
	private boolean walkAway(int roundMoney) {
		boolean validAnswer = false;
		do {
			System.out.println("Do you want to stop here and walk away with $" + roundMoney + "?");
			System.out
					.println("If you answer any question incorrectly in the following rounds, you lose all the money!");
			System.out.print("Yes? No? Enter (Y, N) >> ");
			String userInput = sc.nextLine().trim();

			if (userInput.toUpperCase().equals("Y")) {
				return true;
			} else if (userInput.toUpperCase().equals("N")) {
				return false;
			} else {
				System.out.print("Please enter a valid option!");
			}
		} while (validAnswer == false);

		// Return which never gets triggered.
		return true;
	}

	//exit the game
	private void gameOver() {
		System.out.println("Game Over");
		System.out.print("Program exiting...");
		System.exit(0);
	}
   
	//method used in easyrounds  and hardrounds
	private void askQuestion(int questionIndex) {
		System.out.println(questions[questionIndex].getQuestion());
		System.out.println(questions[questionIndex].getOptions());
	}

	private boolean checkAnswer(int questionIndex, String playerAnswer) {
		playerAnswer = playerAnswer.toUpperCase();
		if (questions[questionIndex].getAnswerLetter().equals(playerAnswer)) {
			return true;
		}
		return false;
	}

	private void rightAnswer(int questionIndex) {
		System.out.println("Correct! The answer is: " + questions[questionIndex].getAnswer());
	}

	private void wrongAnswer(int questionIndex) {
		System.out.println("Sorry, wrong answer!");
		System.out.println("The correct answer is: " + questions[questionIndex].getAnswer());
		gameOver();
	}

	private void easyMode() {
		// Some constants for this mode.
		final int questionsPerRound = 3;
		final int totalRounds = 3;
		final int firstRoundMoney = 1000;
		final int secondRoundMoney = 32000;
		final int thirdRoundMoney = 1000000;

		// Generates random questions!
		questions = questionBank.randomQuestionSet(totalRounds, questionsPerRound); //random set of 9 questions(3*3)

		System.out.println("");
		System.out.println("=========");
		System.out.println("Easy Mode");
		System.out.println("=========");

		// Round 1
		if (easyRound1()) {
			if (walkAway(firstRoundMoney)) {
				System.out.println("Congratulations, you got $" + firstRoundMoney + "!");
				gameOver();
				return;
			}
		} else {
			gameOver();
			return;
		}

		// Round 2
		if (easyRound2()) {
			if (walkAway(secondRoundMoney)) {
				System.out.println("Congratulations, you got $" + secondRoundMoney + "!");
				gameOver();
				return;
			}
		} else {
			gameOver();
			return;
		}

		// Round 3
		if (easyRound3()) {
			System.out.println("YOU ARE NOW A MILLIONAIRE!");
			System.out.println("Congratulations, you got $" + thirdRoundMoney + "!");
			gameOver();
		}

	}

	private void hardMode() {
		// Some constants for this mode.
		final int questionsPerRound = 5;
		final int totalRounds = 3;
		final int firstRoundMoney = 1000;
		final int secondRoundMoney = 32000;
		final int thirdRoundMoney = 1000000;

		// Generates random questions!
		questions = questionBank.randomQuestionSet(totalRounds, questionsPerRound);

		System.out.println("=========");
		System.out.println("Hard Mode");
		System.out.println("=========");

		// Round 1
		if (hardRound1()) {
			if (walkAway(firstRoundMoney)) {
				System.out.println("Congratulations, you got $" + firstRoundMoney + "!");
				gameOver();
				return;
			}
		} else {
			gameOver();
			return;
		}

		// Round 2
		if (hardRound2()) {
			if (walkAway(secondRoundMoney)) {
				System.out.println("Congratulations, you got $" + secondRoundMoney + "!");
				gameOver();
				return;
			}
		} else {
			gameOver();
			return;
		}

		// Round 3
		if (hardRound3()) {
			System.out.println("YOU ARE NOW A MILLIONAIRE!");
			System.out.println("Congratulations, you got $" + thirdRoundMoney + "!");
			gameOver();
		}

	}

	// Treat all rounds for easy mode
	private boolean easyRound1() {
		// Some constants for this round.
		final int questionsPerRound = 3;

		System.out.println("********************************");
		System.out.println("Round 1");
		System.out.println("********************************");
		System.out.println("3 questions will be asked.");
		System.out.println("Q1 = $100, Q2 = $500, Q3 = $1,000");
		System.out.println("********************************");

		// Ask the questions.
		for (int i = 1; i <= questionsPerRound; i++) {
			int questionIndex = i - 1;

			int moneyDisplay = 0;
			switch (i) {
			case 1:
				moneyDisplay = 100;
				break;
			case 2:
				moneyDisplay = 500;
				break;
			case 3:
				moneyDisplay = 1000;
				break;
			}

			String playerAnswer = "";
			String confirmation;
			boolean loop = true;

			while (loop) { // Keep repeating if the player doesn't confirm the answer.
				System.out.println("*Question " + i + " - $" + moneyDisplay + "*");
				
				
				askQuestion(questionIndex);
				
				String lifelineChoice = lifeline.promptLifeline();
				lifeline.handleLifeline(lifelineChoice, questions, questionIndex);
				System.out.print("Your answer >> ");
				playerAnswer = sc.nextLine().trim();

				if (playerAnswer.toUpperCase().equals("A") || playerAnswer.toUpperCase().equals("B")
						|| playerAnswer.toUpperCase().equals("C") || playerAnswer.toUpperCase().equals("D")) {
					System.out.print("Confirm? (Y/N, X to quit) >> ");
					confirmation = sc.nextLine().trim();
					if (confirmation.toUpperCase().equals("Y")) {
						loop = false;
					} else if (confirmation.toUpperCase().equals("X")) {
						loop = false;
						return false;
					} else if (confirmation.toUpperCase().equals("N")) {
						System.out.println();
					} else {
						System.out.println("Invalid response. Please enter valid option (Y/N, X to quit).\n");
					}
				} else {
					System.out.println("Invalid answer. Please enter A, B, C, or D.\n");
				}

			}

			// Check the answer.
			if (!checkAnswer((questionIndex), playerAnswer)) {
				wrongAnswer(questionIndex);
				// Round failed.
				return false;
			} else {
				rightAnswer(questionIndex);
				System.out.println("You've won $" + moneyDisplay + "!\n");
			}
		}

		// Passed the round.
		return true;
	}

	private boolean easyRound2() {
		// Some constants for this round.
		final int questionsPerRound = 3;
		final int startQuestionNumberIndex = 3;

		System.out.println("********************************");
		System.out.println("Round 2");
		System.out.println("********************************");
		System.out.println("3 questions will be asked.");
		System.out.println("Q4 = $8,000, Q5 = $16,000, Q6 = $32,000");
		System.out.println("********************************");

		for (int i = 1; i <= questionsPerRound; i++) {
			int questionIndex = startQuestionNumberIndex - 1 + i;

			int moneyDisplay = 0;
			switch (i) {
			case 1:
				moneyDisplay = 8000;
				break;
			case 2:
				moneyDisplay = 16000;
				break;
			case 3:
				moneyDisplay = 32000;
				break;
			}

			String playerAnswer = "";
			String confirmation;
			boolean loop = true;

			while (loop) { // Keep repeating if the player doesn't confirm the answer.
				System.out.println("*Question " + i + " - $" + moneyDisplay + "*");
				askQuestion(questionIndex);
				String lifelineChoice = lifeline.promptLifeline();
				lifeline.handleLifeline(lifelineChoice, questions, questionIndex);
				System.out.print("Your answer >> ");
				playerAnswer = sc.nextLine().trim();

				if (playerAnswer.toUpperCase().equals("A") || playerAnswer.toUpperCase().equals("B")
						|| playerAnswer.toUpperCase().equals("C") || playerAnswer.toUpperCase().equals("D")) {
					System.out.print("Confirm? (Y/N, X to quit) >> ");
					confirmation = sc.nextLine().trim();
					if (confirmation.toUpperCase().equals("Y")) {
						loop = false;
					} else if (confirmation.toUpperCase().equals("X")) {
						loop = false;
						return false;
					} else if (confirmation.toUpperCase().equals("N")) {
						System.out.println();
					} else {
						System.out.println("Invalid response. Please enter valid option (Y/N, X to quit).\n");
					}
				} else {
					System.out.println("Invalid answer. Please enter A, B, C, or D.\n");
				}
			}

			// Check the answer.
			if (!checkAnswer((questionIndex), playerAnswer)) {
				wrongAnswer(questionIndex);
				// Round failed.
				return false;
			} else {
				rightAnswer(questionIndex);
				System.out.println("You've won $" + moneyDisplay + "!\n");
			}
		}

		// Passed the round.
		return true;
	}

	private boolean easyRound3() {
		// Some constants for this round.
		final int questionsPerRound = 3;
		final int startQuestionNumberIndex = 6;

		System.out.println("********************************");
		System.out.println("Round 3");
		System.out.println("********************************");
		System.out.println("3 questions will be asked.");
		System.out.println("Q6 = $125,000, Q7 = $500,000, Q8 = $1,000,000");
		System.out.println("********************************");

		for (int i = 1; i <= questionsPerRound; i++) {
			int questionIndex = startQuestionNumberIndex - 1 + i;

			int moneyDisplay = 0;
			switch (i) {
			case 1:
				moneyDisplay = 125000;
				break;
			case 2:
				moneyDisplay = 500000;
				break;
			case 3:
				moneyDisplay = 1000000;
				break;
			}

			String playerAnswer = "";
			String confirmation;
			boolean loop = true;

			while (loop) { // Keep repeating if the player doesn't confirm the answer.
				System.out.println("*Question " + i + " - $" + moneyDisplay + "*");
				askQuestion(questionIndex);
				String lifelineChoice = lifeline.promptLifeline();
				lifeline.handleLifeline(lifelineChoice, questions, questionIndex);
				System.out.print("Your answer >> ");
				playerAnswer = sc.nextLine().trim();
				
				// to handle answer of the questions
				if (playerAnswer.toUpperCase().equals("A") || playerAnswer.toUpperCase().equals("B")
						|| playerAnswer.toUpperCase().equals("C") || playerAnswer.toUpperCase().equals("D")) {
					System.out.print("Confirm? (Y/N, X to quit) >> ");
					confirmation = sc.nextLine().trim();
					if (confirmation.toUpperCase().equals("Y")) {
						loop = false;
					} else if (confirmation.toUpperCase().equals("X")) {
						loop = false;
						return false;
					} else if (confirmation.toUpperCase().equals("N")) {
						System.out.println();
					} else {
						System.out.println("Invalid response. Please enter valid option (Y/N, X to quit).\n");
					}
				} else {
					System.out.println("Invalid answer. Please enter A, B, C, or D.\n");
				}
			}

			// Check the answer.
			if (!checkAnswer((questionIndex), playerAnswer)) {
				wrongAnswer(questionIndex);
				// Round failed.
				return false;
			} else {
				rightAnswer(questionIndex);
				System.out.println("You've won $" + moneyDisplay + "!\n");
			}
		}

		// Passed the round.
		return true;
	}

	// Treat all rounds for hard mode
	private boolean hardRound1() {
		// Some constants for this round.
		final int questionsPerRound = 5;

		System.out.println("********************************");
		System.out.println("Round 1");
		System.out.println("********************************");
		System.out.println("5 questions will be asked.");
		System.out.println("Q1 = $100, Q2 = $200, Q3 = $300, Q4 = $500, Q5 = $1000");
		System.out.println("********************************");

		// Ask the questions.
		for (int i = 1; i <= questionsPerRound; i++) {
			int questionIndex = i - 1;

			int moneyDisplay = 0;
			switch (i) {
			case 1:
				moneyDisplay = 100;
				break;
			case 2:
				moneyDisplay = 200;
				break;
			case 3:
				moneyDisplay = 300;
				break;
			case 4:
				moneyDisplay = 500;
				break;
			case 5:
				moneyDisplay = 1000;
				break;
			}

			String playerAnswer = "";
			String confirmation;
			boolean loop = true;

			while (loop) { // Keep repeating if the player doesn't confirm the answer.
				System.out.println("*Question " + i + " - $" + moneyDisplay + "*");
				askQuestion(questionIndex);
				System.out.println("There are no lifelines available for this round.");
				System.out.print("Your answer >> ");
				playerAnswer = sc.nextLine().trim();

				if (playerAnswer.toUpperCase().equals("A") || playerAnswer.toUpperCase().equals("B")
						|| playerAnswer.toUpperCase().equals("C") || playerAnswer.toUpperCase().equals("D")) {
					System.out.print("Confirm? (Y/N, X to quit) >> ");
					confirmation = sc.nextLine().trim();
					if (confirmation.toUpperCase().equals("Y")) {
						loop = false;
					} else if (confirmation.toUpperCase().equals("X")) {
						loop = false;
						return false;
					} else if (confirmation.toUpperCase().equals("N")) {
						System.out.println();
					} else {
						System.out.println("Invalid response. Please enter valid option (Y/N, X to quit).\n");
					}
				} else {
					System.out.println("Invalid answer. Please enter A, B, C, or D.\n");
				}
			}

			// Check the answer.
			if (!checkAnswer((questionIndex), playerAnswer)) {
				wrongAnswer(questionIndex);
				// Round failed.
				return false;
			} else {
				rightAnswer(questionIndex);
				System.out.println("You've won $" + moneyDisplay + "!\n");
			}
		}

		// Passed the round.
		return true;
	}

	private boolean hardRound2() {
		// Some constants for this round.
		final int questionsPerRound = 5;
		final int startQuestionNumberIndex = 5;

		System.out.println("********************************");
		System.out.println("Round 2");
		System.out.println("********************************");
		System.out.println("5 questions will be asked.");
		System.out.println("Q6 = $2,000, Q7 = $4,000, Q8 = $8,000, Q9 = $16,000, Q10 = $32,000");
		System.out.println("********************************");

		for (int i = 1; i <= questionsPerRound; i++) {
			int questionIndex = startQuestionNumberIndex - 1 + i;

			int moneyDisplay = 0;
			switch (i) {
			case 1:
				moneyDisplay = 2000;
				break;
			case 2:
				moneyDisplay = 4000;
				break;
			case 3:
				moneyDisplay = 8000;
				break;
			case 4:
				moneyDisplay = 16000;
				break;
			case 5:
				moneyDisplay = 32000;
				break;
			}

			String playerAnswer = "";
			String confirmation;
			boolean loop = true;

			while (loop) { // Keep repeating if the player doesn't confirm the answer.
				System.out.println("*Question " + i + " - $" + moneyDisplay + "*");
				askQuestion(questionIndex);
				String lifelineChoice = lifeline.promptLifeline();
				lifeline.handleLifeline(lifelineChoice, questions, questionIndex);
				System.out.print("Your answer >> ");
				playerAnswer = sc.nextLine().trim();
				
				if (playerAnswer.toUpperCase().equals("A") || playerAnswer.toUpperCase().equals("B")
						|| playerAnswer.toUpperCase().equals("C") || playerAnswer.toUpperCase().equals("D")) {
					System.out.print("Confirm? (Y/N, X to quit) >> ");
					confirmation = sc.nextLine().trim();
					if (confirmation.toUpperCase().equals("Y")) {
						loop = false;
					} else if (confirmation.toUpperCase().equals("X")) {
						loop = false;
						return false;
					} else if (confirmation.toUpperCase().equals("N")) {
						System.out.println();
					} else {
						System.out.println("Invalid response. Please enter valid option (Y/N, X to quit).\n");
					}
				} else {
					System.out.println("Invalid answer. Please enter A, B, C, or D.\n");
				}

			}

			// Check the answer.
			if (!checkAnswer((questionIndex), playerAnswer)) {
				wrongAnswer(questionIndex);
				// Round failed.
				return false;
			} else {
				rightAnswer(questionIndex);
				System.out.println("You've won $" + moneyDisplay + "!\n");
			}
		}

		// Passed the round.
		return true;
	}

	private boolean hardRound3() {
		// Some constants for this round.
		final int questionsPerRound = 5;
		final int startQuestionNumberIndex = 10;

		System.out.println("********************************");
		System.out.println("Round 3");
		System.out.println("********************************");
		System.out.println("5 questions will be asked.");
		System.out.println("Q11 = $64,000, Q12 = $125,000, Q13 = $250,000, Q14 = $500,000, Q15 = $1,000,000");
		System.out.println("********************************");

		for (int i = 1; i <= questionsPerRound; i++) {
			int questionIndex = startQuestionNumberIndex - 1 + i;

			int moneyDisplay = 0;
			switch (i) {
			case 1:
				moneyDisplay = 64000;
				break;
			case 2:
				moneyDisplay = 125000;
				break;
			case 3:
				moneyDisplay = 250000;
				break;
			case 4:
				moneyDisplay = 500000;
				break;
			case 5:
				moneyDisplay = 1000000;
				break;
			}

			String playerAnswer = "";
			String confirmation;
			boolean loop = true;

			while (loop) { // Keep repeating if the player doesn't confirm the answer.
				System.out.println("*Question " + i + " - $" + moneyDisplay + "*");
				askQuestion(questionIndex);
				String lifelineChoice = lifeline.promptLifeline();
				lifeline.handleLifeline(lifelineChoice, questions, questionIndex);
				System.out.print("Your answer >> ");
				playerAnswer = sc.nextLine().trim();

				if (playerAnswer.toUpperCase().equals("A") || playerAnswer.toUpperCase().equals("B")
						|| playerAnswer.toUpperCase().equals("C") || playerAnswer.toUpperCase().equals("D")) {
					System.out.print("Confirm? (Y/N, X to quit) >> ");
					confirmation = sc.nextLine().trim();
					if (confirmation.toUpperCase().equals("Y")) {
						loop = false;
					} else if (confirmation.toUpperCase().equals("X")) {
						loop = false;
						return false;
					} else if (confirmation.toUpperCase().equals("N")) {
						System.out.println();
					} else {
						System.out.println("Invalid response. Please enter valid option (Y/N, X to quit).\n");
					}
				} else {
					System.out.println("Invalid answer. Please enter A, B, C, or D.\n");
				}
			}

			// Check the answer.
			if (!checkAnswer((questionIndex), playerAnswer)) {
				wrongAnswer(questionIndex);
				// Round failed.
				return false;
			} else {
				rightAnswer(questionIndex);
				System.out.println("You've won $" + moneyDisplay + "!\n");
			}
		}

		// Passed the round.
		return true;
	}

}
