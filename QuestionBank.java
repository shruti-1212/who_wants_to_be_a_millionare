package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuestionBank {
	
    private Question[] easyQuestionList;

    private Question[] midQuestionList;

    private Question[] hardQuestionList;

    public QuestionBank() {
        // Temporary lists that are easier to use than arrays when adding new elements.
        ArrayList<Question> easyQuestions = new ArrayList<>();

        ArrayList<Question> midQuestions = new ArrayList<>();

        ArrayList<Question> hardQuestions = new ArrayList<>();

        try {
        	// File location
            File file = new File("src/project/questions.txt");
            
            /**
             * The text file is structured in the following format:
             * *Easy*
             * 
             * Sample question here
             * A. ...  B. ...  C. ...  D. ...
             * Correct Answer: X. ...
             * 
             * *Mid*
             * ...
             * 
             * *Hard*
             * ...
             * 
             */
                
            Scanner sc = new Scanner(file);

            // To avoid redundant code, use a temporary list.
            ArrayList<Question> currentQuestions = easyQuestions;

            // Temporary variables.
            Question q;
            String question = "";
            String options = "";
            String answer = "";

            // Reading of the text file starts here...
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.equals("*Easy*")) { // The following blocks of questions are all easy questions.
                    currentQuestions = easyQuestions;
                    
                } else if (line.equals("*Mid*")) {  // The following blocks of questions are all mid questions.
                    currentQuestions = midQuestions;
                    
                } else if (line.equals("*Hard*")) { // The following blocks of questions are all hard questions.
                    currentQuestions = hardQuestions;
                    
                } else if (line.isEmpty()) { // End of a block in the file.
                	// Since an empty line can be after a question block or after a title (easy, mid, hard) block,
                	// We need to determine which type it is.
                	// Do nothing if it is after a title block.
                	// Add questions, options, answers if it is after a question block.
                    if (!question.isEmpty() && !options.isEmpty() && !answer.isEmpty()) {
                    	q = new Question(question, options, answer);
                    	currentQuestions.add(q);

                        // Reset temporary variables for the next question block.
                        question = "";
                        options = "";
                        answer = "";
                    }
                } else if (line.startsWith("A.")) { // This line contains options which always starts with "A.".
                    options = line;
                } else if (line.startsWith("Correct Answer:")) { // This line contains the answer.
                    // Extract the answer part
                    answer = line.replace("Correct Answer:", "").trim();
                } else { // This line contains the question.
                    question = line;
                }
            }

            // Handle the last question block in case there is no trailing blank line.
            if (!question.isEmpty() && !options.isEmpty() && !answer.isEmpty()) {
            	q = new Question(question, options, answer);
            	currentQuestions.add(q);
            }

            // Good habit the close resources!
            sc.close();

            // Initialize the arrays with list data for each difficulty level.
            // String[0] creates an array with the same type and size as the collection.
            easyQuestionList = easyQuestions.toArray(new Question[0]);

            midQuestionList = midQuestions.toArray(new Question[0]);

            hardQuestionList = hardQuestions.toArray(new Question[0]);


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage() + "\nCheck your file path.");
        }
    }

    public Question[] getEasyQuestionList() { 
    	return easyQuestionList; 
    }   

    public Question[] getMidQuestionList() { 
    	return midQuestionList; 
    }
    
    public Question[] getHardQuestionList() { 
    	return hardQuestionList; 
    }
    
    // Method to display all questions for testing and troubleshooting.
    public void displayQuestions() {
        System.out.println("Easy Questions:");
        displayQuestionSet(easyQuestionList);
        System.out.println("\nMid Questions:");
        displayQuestionSet(midQuestionList);
        System.out.println("\nHard Questions:");
        displayQuestionSet(hardQuestionList);
    }

    private void displayQuestionSet(Question[] questions) {
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question: " + questions[i].getQuestion());
            System.out.println("Options: " + questions[i].getOptions());
            System.out.println("Answer: " + questions[i].getAnswer());
            System.out.println();
        }
    }
    
    private boolean questionUsed(int[] record, int index) {
    	// If a index is already in the record, it is used already!
    	for (int i = 0; i < record.length; i++) {
    		if (record[i] == -1) {
                break;  // Stop checking once we reach uninitialized entries
            }
    		if (index == record[i]) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public Question[] randomQuestionSet(int rounds, int perRound) {
    	// Total questions generated = rounds * perRound.
    	int total = rounds * perRound;
    	
    	// If we are asking for more questions than there are available.
    	if (total > easyQuestionList.length + midQuestionList.length + hardQuestionList.length) {
    	    throw new IllegalArgumentException("Not enough questions available to generate a unique set.");
    	}
    	
    	Question[] randomQuestionList = new Question[total];
    	
    	// Random generator.
    	Random random = new Random();
    	
    	// Variables to check if all the questions are used up (how many questions left for each diff).
    	int easyRemaining = easyQuestionList.length;
        int midRemaining = midQuestionList.length;
        int hardRemaining = hardQuestionList.length;
    	
    	// Index counter for storing the used questions in the used list.
    	int easyUsedCounter = 0; // To access the index
    	int midUsedCounter = 0; // To access the index
    	int hardUsedCounter = 0; // To access the index
    	
    	// These Arrays store the indexes of used questions for each difficulty.
    	int[] easyUsed = new int[easyQuestionList.length];
    	int[] midUsed = new int[midQuestionList.length];
    	int[] hardUsed = new int[hardQuestionList.length];
    	
    	// The default value is 0, which is a valid index, we don't want that.
    	// Fill the entire array with -1.
    	Arrays.fill(easyUsed, -1);  
    	Arrays.fill(midUsed, -1);
    	Arrays.fill(hardUsed, -1);
    	
    	// Generate questions.
    	for (int i = 0; i < total; i++) {
    		
    		// Random difficulty selection.
    		// The while condition makes sure there are enough questions (keep it out of infinite loop).
    		int randomDiff;
            do {
                randomDiff = random.nextInt(3) + 1;
            } while ((randomDiff == 1 && easyRemaining == 0) ||
                     (randomDiff == 2 && midRemaining == 0) ||
                     (randomDiff == 3 && hardRemaining == 0));
        	
        	// Random question index.
        	int randomIndex;
        	
        	// Is this question already used?
        	boolean questionIsUsed = false;
        	
        	// Generate random question based on difficulty.
        	switch(randomDiff) {
        	case 1: // Easy.        		
        		do {
        			randomIndex = random.nextInt(easyQuestionList.length);
        			questionIsUsed = questionUsed(easyUsed, randomIndex);
        		} while (questionIsUsed == true);
        		
        		randomQuestionList[i] = easyQuestionList[randomIndex];		
        		        		
        		// Mark used
        		easyUsed[easyUsedCounter] = randomIndex;
        		easyUsedCounter++;
        		easyRemaining--;
        		
        		break;
        	case 2: // Mid.
        		do {
        			randomIndex = random.nextInt(midQuestionList.length);
        			questionIsUsed = questionUsed(midUsed, randomIndex);
        		} while (questionIsUsed == true);
        		
        		randomQuestionList[i] = midQuestionList[randomIndex];
        		
        		// Mark used
        		midUsed[midUsedCounter] = randomIndex;
        		midUsedCounter++;
        		midRemaining--;
        		
        		break;
        	case 3: // Hard.
        		do {
        			randomIndex = random.nextInt(hardQuestionList.length);
        			questionIsUsed = questionUsed(hardUsed, randomIndex);
        		} while (questionIsUsed == true);
        		
        		randomQuestionList[i] = hardQuestionList[randomIndex];
        		
        		// Mark used
        		hardUsed[hardUsedCounter] = randomIndex;
        		hardUsedCounter++;
        		hardRemaining--;
        		
        		break;
        	}
    	}
    	
    	return randomQuestionList;
    }
    
}