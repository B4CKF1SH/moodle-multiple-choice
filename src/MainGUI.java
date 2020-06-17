import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainGui extends JFrame {

	private static final long serialVersionUID = 6810557583205795439L;
	
	private JPanel contentPane;
	private JTextField labelField;
	private JTextField descriptionField;
	private DefaultMutableTreeNode mainNode;
	private JSlider selectionNumberSlider;
	private JTree tree;
	private JTextArea txtrChoicesinput;
	
	private int prevNumber = -1;
	
	private HashMap<Integer, String[]> selectionHash = new HashMap<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/logo_square.png")));
		
		setTitle("Moodle Multiple Choice");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogolabel = new JLabel("");
		lblLogolabel.setBounds(5, 5, 424, 88);
		lblLogolabel.setIcon(new ImageIcon(MainGui.class.getResource("/logo.png")));
		contentPane.add(lblLogolabel);
		
		txtrChoicesinput = new JTextArea();
		txtrChoicesinput.setBounds(15, 138, 161, 235);
		contentPane.add(txtrChoicesinput);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 384, 414, 2);
		contentPane.add(separator);
		
		JButton btnHelp = new JButton("Help?");
		btnHelp.setBounds(15, 400, 89, 23);
		contentPane.add(btnHelp);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(329, 397, 89, 23);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		contentPane.add(btnSave);
		
		tree = new JTree();
		
		
		mainNode = new DefaultMutableTreeNode("Selections");
		
		tree.setModel(new DefaultTreeModel(mainNode));
		tree.setBounds(186, 138, 232, 176);
		
		tree.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) return;
                try {
                	selectTreeElement(Integer.valueOf((String) node.getUserObject()));
                }
                catch (Exception except) {}
		    }
		});
		
		contentPane.add(tree);
		
		labelField = new JTextField();
		labelField.setBounds(186, 322, 232, 20);
		TextPrompt tp1 = new TextPrompt("Selection Label", labelField);
		tp1.setForeground( Color.GRAY );
		tp1.changeAlpha(0.5f);
		tp1.setAlignmentY(TOP_ALIGNMENT);
		contentPane.add(labelField);
		labelField.setColumns(10);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(186, 353, 232, 20);
		TextPrompt tp2 = new TextPrompt("Selection Description", descriptionField);
		tp2.setForeground( Color.GRAY );
		tp2.changeAlpha(0.5f);
		tp2.setAlignmentY(TOP_ALIGNMENT);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		JLabel lblNumberOfSelections = new JLabel("Selections:");
		lblNumberOfSelections.setBounds(186, 110, 105, 14);
		contentPane.add(lblNumberOfSelections);
		
		selectionNumberSlider = new JSlider();
		selectionNumberSlider.setMinorTickSpacing(1);
		selectionNumberSlider.setSnapToTicks(true);
		selectionNumberSlider.setPaintTicks(true);
		selectionNumberSlider.setValue(1);
		selectionNumberSlider.setMaximum(7);
		selectionNumberSlider.setMinimum(1);
		selectionNumberSlider.setBounds(257, 103, 161, 31);
		
		selectionNumberSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				updateSelectionTree();
			}
		});
		
		contentPane.add(selectionNumberSlider);
		
		JLabel lblChoices = new JLabel("Choices:");
		lblChoices.setBounds(15, 120, 89, 14);
		contentPane.add(lblChoices);
		
		updateSelectionTree();
		
	}
	
	private void updateSelectionTree() {
		mainNode.removeAllChildren();
		
		int numSelections = selectionNumberSlider.getValue();
		
		for (int i = 1; i <= numSelections; i++) {
			mainNode.add(new DefaultMutableTreeNode(String.valueOf(i)));
			
		}
		
		tree.setModel(new DefaultTreeModel(mainNode));

	}
	
	private void selectTreeElement(int number) {
		
		if (prevNumber > 0) {
			String prevLabel = labelField.getText();
			String prevDescription = descriptionField.getText();
			
			String[] values = new String[2];
			
			if (prevLabel != null && !prevLabel.isEmpty()) {
				values[0] = prevLabel;
			}
			if (prevDescription != null && !prevDescription.isEmpty()) {
				values[1] = prevDescription;
			}
			
			selectionHash.put(new Integer(prevNumber), values);
			
		}
		
		
		String[] fieldValues = selectionHash.get(new Integer(number));
		
		if (fieldValues != null) {
			if (fieldValues[0] != null && !fieldValues[0].isEmpty()) {
				labelField.setText(fieldValues[0]);
			}
			else {
				labelField.setText("");
			}
			if (fieldValues[1] != null && !fieldValues[1].isEmpty()) {
				descriptionField.setText(fieldValues[1]);
			}
			else {
				descriptionField.setText("");
			}
		}
		else {
			labelField.setText("");
			descriptionField.setText("");
		}
		
		this.setVisible(true);
		
		prevNumber = number;
		
	}
	
	private void save() {
		
		if (prevNumber > 0) {
			String prevLabel = labelField.getText();
			String prevDescription = descriptionField.getText();
			
			String[] values = new String[2];
			
			if (prevLabel != null && !prevLabel.isEmpty()) {
				values[0] = prevLabel;
			}
			if (prevDescription != null && !prevDescription.isEmpty()) {
				values[1] = prevDescription;
			}
			
			selectionHash.put(new Integer(prevNumber), values);
			
		}
		
		int numSelections = selectionNumberSlider.getValue();
		List<String> descriptions = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> choices = new ArrayList<>(Arrays.asList(txtrChoicesinput.getText().split("\\n")));
		
		for (String choice : choices) {
			if (choice == null || choice.isEmpty()) {
				choices.remove(choice);
			}
		}
		
		
		for (int i = 1; i <= numSelections; i++) {
			mainNode.add(new DefaultMutableTreeNode(String.valueOf(i)));
			String[] fieldValues = selectionHash.get(new Integer(i));
			
			if (fieldValues != null) {
				if (fieldValues[0] != null && !fieldValues[0].isEmpty()) {
					labels.add(fieldValues[0]);
				}
				else {
					labels.add(String.valueOf(i));
				}
				if (fieldValues[1] != null && !fieldValues[1].isEmpty()) {
					descriptions.add(fieldValues[1]);
				}
				else {
					descriptions.add("Choice " + String.valueOf(i));
				}
			}
			else {
				labels.add(String.valueOf(i));
				descriptions.add("Choice " + String.valueOf(i));
			}
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
		fileChooser.setFileFilter(filter);
		 
		int userSelection = fileChooser.showSaveDialog(this);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    
		    if (!fileToSave.getAbsolutePath().endsWith(".xml")) {
		    	fileToSave = new File(fileToSave.getAbsolutePath() + ".xml");
		    }
		    
		    
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    
			Feedback fb = FeedbackCreator.createLevelChoice(descriptions.size(), descriptions.toArray(new String[0]), labels.toArray(new String[0]), choices.toArray(new String[0]));
		    
			try {
				FileWriter myWriter = new FileWriter(fileToSave.getAbsolutePath());
				myWriter.write(fb.toString());
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		    	System.out.println("An error occurred.");
		    }
		}
	}
}
