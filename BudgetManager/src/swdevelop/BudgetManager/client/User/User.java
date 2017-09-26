package swdevelop.BudgetManager.client.User;

public class User {
	private String username;
	private int id;
	
	public String getUsername()
	{
		return username;
	}
	public int getId()
	{
		return id;
	}
	public User(String _username,int _id)
	{
		username=_username;
		id=_id;
	}
}
