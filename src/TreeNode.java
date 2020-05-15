import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
	
	private String[] choices;
	private String prevChoice;
	private TreeNode previous;
	private MultiChoice item = null;
	private List<TreeNode> nodesBelow = new ArrayList<>();
	
	public TreeNode(String[] choices, String prevChoice, TreeNode previous) {
		this.choices = choices;
		this.prevChoice = prevChoice;
		this.previous = previous;
		
		for (int i = 0; i < choices.length; i++) {
			List<String> choicesCopy = new ArrayList<>(Arrays.asList(choices));
			String removed = choicesCopy.remove(i);
			this.nodesBelow.add(new TreeNode(choicesCopy.toArray(new String[0]), removed, this));
		}
	}

	public String[] getChoices() {
		return choices;
	}
	
	public List<TreeNode> getNodesBelow() {
		return nodesBelow;
	}

	public String getPrevChoice() {
		return prevChoice;
	}
	
	public TreeNode getPrevious() {
		return previous;
	}

	public MultiChoice getItem() {
		return item;
	}

	public void setItem(MultiChoice item) {
		this.item = item;
	}
}
