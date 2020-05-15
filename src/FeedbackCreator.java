import java.util.Arrays;
import java.util.List;

public class FeedbackCreator {
	
	private FeedbackCreator() {}
	
	public static Feedback createLevelChoice(int numLevels,	String[] descriptions, String[] labels, String[] choices) {
		
		if (numLevels > choices.length) numLevels = choices.length;
		
		int startingId = XMLTools.generateInitialId();
		
		Feedback fb = new Feedback();
		
		
		addLevel(fb, startingId, 0, "", numLevels, 1, descriptions, labels, choices);
		
		
		return fb;
	}
	
	private static void addLevel(Feedback fb, int id, int previousId, String previousChoice, int levels, int currentlvl, String[] descriptions, String[] labels, String[] remainingChoices) {

		
		fb.addItem(new MultiChoice(id, descriptions[currentlvl-1], labels[currentlvl-1], remainingChoices, previousId, previousChoice));
		
		fb.addItem(new Pagebreak(id + 1));
		
		if (currentlvl < levels) {
			for (int i = 0; i < remainingChoices.length; i++) {
				
				List<String >nextChoices = Arrays.asList(remainingChoices);
				nextChoices.remove(i);
				addLevel(fb, id + 2, id, remainingChoices[i], levels, currentlvl + 1, descriptions, labels, nextChoices.toArray(new String[0]));
			}
		}
	}
}
