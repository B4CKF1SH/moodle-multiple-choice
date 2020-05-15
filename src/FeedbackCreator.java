import java.util.ArrayList;
import java.util.List;

/**
 * Util class to create Moodle Feedback forms
 * 
 * @author MMC Team
 *
 */
public class FeedbackCreator {
	
	/**
	 * private constructor to hide default public one
	 */
	private FeedbackCreator() {}
	
	/**
	 * Method to create level-choice-based feedback forms for Moodle
	 * 
	 * @param numLevels Number of Levels/Choices to pick
	 * @param descriptions Descriptions of individual levels (visible to end-user)
	 * @param labels Labels of individual levels (have to be unique, non-visible)
	 * @param choices Possible choices
	 * @return A Feedback-object, the toString()-method of which return the XML content
	 */
	public static Feedback createLevelChoice(int numLevels,	String[] descriptions, String[] labels, String[] choices) {
		
		if (numLevels > choices.length) numLevels = choices.length;
		
		int startingId = XMLTools.generateInitialId();
		
		//all the calculation magic in one line - seemingly harmless
		TreeNode start = new TreeNode(choices, "", null);
		
		Feedback fb = new Feedback();
		
		List<TreeNode> current = new ArrayList<>();
		current.add(start);
		
		int level = 0;
		
		int currentId = startingId;
		
		while (!current.isEmpty() && level < numLevels) {
			
			if (level > 0) {
				fb.addItem(new Pagebreak(currentId));
				currentId++;
			}
			
			List<TreeNode> next = new ArrayList<>();
			
			int labelAddition = 1;
			
			for (TreeNode node : current) {
				
				TreeNode previous = node.getPrevious();
				
				MultiChoice item = new MultiChoice(currentId, descriptions[level], labels[level] + String.valueOf(labelAddition), node.getChoices(), previous == null ? 0 : previous.getItem().getId(), node.getPrevChoice());
				
				node.setItem(item);
				fb.addItem(item);
				currentId++;
				
				next.addAll(node.getNodesBelow());
				
				labelAddition++;
			}
			
			
			current = next;
			
			level++;
		}
		
		return fb;
	}
}
