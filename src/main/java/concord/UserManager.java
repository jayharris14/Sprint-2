package concord;

import java.util.ArrayList;

public class UserManager
{
	User user;
	ArrayList<User> UM=new ArrayList<User>();
	
	public void addUser(User user) {
		UM.add(user);
	}
}
