package project;

public class Question {

	private String question;
	private String options;
	private String answer;
	
	public Question(String question, String options, String answer) {
		this.question = question;
		this.options = options;
		this.answer = answer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getOptions() {
		return options;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getAnswerLetter() {
		// Since all answer are in the format of "X. Answer",
		// we can split at "." and get the first part.
		// Need double "\" since it uses regular expression to actually split at ".".
		return answer.split("\\.")[0];
	}
}
