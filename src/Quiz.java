import java.util.ArrayList;
import java.util.List;

public class Quiz {
	
	//list of Questions
	private List<Question> questions = new ArrayList<>();
	
	/**
	 * Removes a question from the Quiz
	 * @param index The index of the Question to remove
	 * @return The removed Question
	 */
	public Question removeQuestion(int index) {
		return questions.remove(index);
	}
	
	/**
	 * Adds a question to the Quiz
	 * @param q The Question to add
	 */
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	/**
	 * Overriding toString() method to create XML
	 */
	@Override
	public String toString() {
		
		//XML header
		String value = "<?xml version=\"1.0\" ?>\r\n";
		
		//opening quiz tag
		value += "<quiz>\r\n";
		
		//questions
		for (Question q : questions) {
			value += q.toString();
		}
		
		//closing quiz tag
		value += "</quiz>";
		
		return value;
	}
}
