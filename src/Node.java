import java.util.ArrayList;

public class Node 
{
	private int number;
	private ArrayList<String> words = new ArrayList<String>();

	//Used 10 for the sake of readability
	//I thought that having two null array entries was a good tradeoff
	//just to make the code more readable
	//You can re-factor this if you want to
	private Node[] next = new Node[10];
	
	public Node(int number)
	{
		this.number = number;
		//Setting all children nodes to null
		for(int i = 0; i < 10; i++)
		{
			this.next[i] = null;
		}
	}
	
	/*
	 * Adds word to node
	 * @param word to be added
	 */
	public void addWord(String word)
	{
		this.words.add(word);
	}
	

	/*
	 * Creates a child node 
	 * @param number value of node
	 */
	public void addNode(int number)
	{
		
		if(!childExists(number)) //Checks if the child does not exist
		{
			//Creates a new node
			Node newNode = new Node(number); 
			//Sets node as child
			this.next[number] = newNode;
		}
	}
	
	/*
	 * Checks if child exists
	 * @param number value of child
	 * @return True if node exists, otherwise false
	 */
	public boolean childExists(int number)
	{
		return(this.next[number] != null);
	}
	
	/*
	 * Gets the child with corresponding number value
	 * @param number value of child
	 * @return node of child with number value
	 */
	public Node getChild(int number) throws NullPointerException
	{
		return next[number];
	}
	
	/*
	 * Gets all words of current node
	 * @return an arraylist of words
	 */
	public ArrayList<String> getWords()
	{
		return(words);
	}
	
}
