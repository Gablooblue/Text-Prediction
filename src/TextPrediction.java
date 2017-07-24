import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextPrediction extends JPanel 
{
	//Creating the first node
	public static Node head = new Node(0);

	//Change Filename with path to your file
	private static final String FILENAME = "dictionary.txt";
	
	//temp pointer starts at head
	public static Node temp = head;

	//Outputs for later
	public static JTextField historyLogger;
	public static JTextArea wordOutput;

	public static void main(String[] args)
	{
		readFile();
		initComponents();
	}
	
	public static void input(int number)
	{
		try
		{
			//Gets the child corresponding to the number pressed
			temp = temp.getChild(number);
			//Then prints words inside the node
			printWords(temp);
		}
		//Error incase the node doesn't exist
		catch(NullPointerException e)
		{
			wordOutput.setText("No words with that combination");
		}
		finally
		{
			//adds pressed number to the history of numbers pressed
			addHistory(number);
		}
	}
	
	public static void reset()
	{
		temp = head; //Resets the temp pointer to point to the head node
		
		//Resets both outputs
		historyLogger.setText("");
		wordOutput.setText("Click on a number");
	}
	
	
	public static void addHistory(int entry)
	{
		//Prints out history
		historyLogger.setText(historyLogger.getText() + entry);
	}
	

	public static void initComponents()
	{

		JPanel panel = new JPanel(new BorderLayout());
		JFrame frame = new JFrame("Text Predicter");
		
		frame.setContentPane(panel);
		frame.setTitle("Text Predicter");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttons = new JPanel(new GridLayout(3,3));
		
		//adding all buttons
		JButton twoButton = new JButton("2");
		twoButton.addActionListener(new TwoListener());
		JButton threeButton = new JButton("3");
		threeButton.addActionListener(new ThreeListener());
		JButton fourButton = new JButton("4");
		fourButton.addActionListener(new FourListener());
		JButton fiveButton = new JButton("5");
		fiveButton.addActionListener(new FiveListener());
		JButton sixButton = new JButton("6");
		sixButton.addActionListener(new SixListener());
		JButton sevenButton = new JButton("7");
		sevenButton.addActionListener(new SevenListener());
		JButton eightButton = new JButton("8");
		eightButton.addActionListener(new EightListener());
		JButton nineButton = new JButton("9");
		nineButton.addActionListener(new NineListener());
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetListener());

		
		//Initializing historyLogger
		historyLogger = new JTextField();
		historyLogger.setEditable(false);

		//Initializing wordOutput
		wordOutput = new JTextArea("Click on a number");
		wordOutput.setRows(3);
		wordOutput.setLineWrap(true);
		wordOutput.setEditable(false);
		wordOutput.setWrapStyleWord(true);

		//Adding a scrollbar to wordOutput
		JScrollPane scroll = new JScrollPane (wordOutput);

		buttons.add(twoButton);
		buttons.add(threeButton);
		buttons.add(fourButton);
		buttons.add(fiveButton);
		buttons.add(sixButton);
		buttons.add(sevenButton);
		buttons.add(eightButton);
		buttons.add(nineButton);
		buttons.add(resetButton);
		
		panel.add(buttons, BorderLayout.WEST);
		panel.add(historyLogger, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);

		frame.pack();
	}
	
	public static void printWords(Node temp)
	{
		try
		{
			wordOutput.setText("Predicted words: ");

			//Creating an upper limit, can be changed 
			int limit = temp.getWords().size() > 50 ? 50 : temp.getWords().size();
			//Limiting only 50 words to be output
			ArrayList<String> words = new ArrayList<> (temp.getWords().subList(0, limit));
			for(String word : words)
			{
				//Appends words to the wordOutput
				wordOutput.setText(wordOutput.getText() + '\n' +  word);
			}
			//If there are more than 50 words, add ...
			if(temp.getWords().size() > 50)
				wordOutput.setText(wordOutput.getText() + '\n' + "...");
			
		}
		catch(NullPointerException e)
		{
			wordOutput.setText("No words with that combination");
		}
	}
	
	public static void readFile()
	{
		//Initializing readers
		FileReader fr = null;
		BufferedReader  br = null;
		try 
		{
			//Opening the file
			fr = new FileReader(FILENAME);

			//Adding file to buffer
			br = new BufferedReader(fr);
			br = new BufferedReader(new FileReader(FILENAME));
			String word;
			
			//Reading all lines in the file
			while((word = br.readLine()) != null)
			{
				//Storing the word in respective nodes
				store(word);
			}
		}
		catch(IOException e)
		{
			//In case of error
			e.printStackTrace();
		}
		//Closing readers 
		finally
		{
			try
			{
				if(br != null)
					br.close();
				if(fr != null)
					fr.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
	public static void store(String word)
	{
		Node temp = head;
		int number;
		//Adding every word to the head
		head.addWord(word);
		
		//Loops over every character 
		for(int i = 0; i < word.length(); i++)
		{
			number = getNumber(word.charAt(i)); //Gets each character 
			if(!temp.childExists(number)) //If the node with num doesn't exist yet
			{
				temp.addNode(number); // Create said node
			}
			else if(number == 0) //If char is not a letter, just ignore it
				continue;
			temp = temp.getChild(number); //Goes to the node with corresponding number
			temp.addWord(word); // adds word
		}
	}
	

	/*
	 * Haha this function is so ugly but it works!
	 */
	public static int getNumber(char letter)
	{
		//Sets the letter to lowercase to prevent problems 
		letter = Character.toLowerCase(letter);
		//Gets every possible letter
		if(letter == 'a' || letter == 'b' || letter == 'c')
		{
			return 2;
		}
		else if (letter == 'd' || letter == 'e' || letter == 'f')
		{
			return 3;
		}
		else if (letter == 'g' || letter == 'h' || letter == 'i')
		{
			return 4;
		}
		else if (letter == 'j' || letter == 'k' || letter == 'l')
		{
			return 5;
		}
		else if (letter == 'm' || letter == 'n' || letter == '0')
		{
			return 6;
		}
		else if (letter == 'p' || letter == 'q' || letter == 'r' || letter == 's')
		{
			return 7;
		}
		else if (letter == 't' || letter == 'u' || letter == 'v')
		{
			return 8;
		}
		else if (letter == 'w' || letter == 'x' || letter == 'y' || letter == 'z')
		{
			return 9;
		}
		
		return 0;
	}

	//Action listeners for buttons
	static class TwoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(2);
		}
	}

	static class ThreeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(3);
		}
	}

	static class FourListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(4);
		}
	}

	static class FiveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(5);
		}
	}
	static class SixListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(6);
		}
	}
	static class SevenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(7);
		}
	}
	static class EightListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			input(8);
		}
	}
	static class NineListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			input(9);
		}
	}
	
	static class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			reset();
		}
	}
}
