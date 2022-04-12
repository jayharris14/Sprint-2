package concord;

import java.util.ArrayList;

public class UserManager
{
	User user;
	static ArrayList<User> UM=new ArrayList<User>();
	
	public UserManager() {
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static ArrayList<User> getUM() {
		return UM;
	}

	public static void setUM(ArrayList<User> uM) {
		UM = uM;
	}

	public void addUser(User user) {
		UM.add(user);
	}
	
	public void createUser() {
		this.user=new User(this);
	}
}
