public class Move 
{
	private String myName; //name of the move
	private String myCode; //each move has a code that is previously established by an organization
	
	
	public Move (String name, String code)
	{
		myName = name;
		myCode = code;
	}
	
	//String version of move
	public String toString()
	{
		String value = String.format("%1s %50s", myName, myCode + "\n");
		return value;
	}
	
	public String getName()
	{
		return myName;
	}
	
	public String getCode()
	{
		return myCode;
	}
	
	public void setName(String name)
	{
		myName = name;
	}
	
	public void setCode(String code)
	{
		myCode = code;
	}
	
	//returns true or false based on whether one move equals the other
	public boolean equals(Move move)
	{
		return move.getCode().equals(myCode) && move.getName().equals(myName); 
	}
	
	//compares two moves and returns an int based on the comparison
	public int compareTo(Move move)
	{
		if(this.getCode().compareTo(move.getCode()) == 0)
		{
			if(this.getName().compareTo(move.getName()) == 0)
			{
				return 0;
			}
			return this.getName().compareTo(move.getName());
		}
		return this.getCode().compareTo(move.getCode());
	}
}