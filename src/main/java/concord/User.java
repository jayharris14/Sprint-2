package concord;

import java.util.ArrayList;
import java.util.HashMap;


public class User
{
	String profileData;
	String userName;
	Role role;
	HashMap<Server, Role> roles= new HashMap<Server, Role>();
	UserManager usermanager;
	
	User(UserManager usermanager){
		usermanager.addUser(this);
	}
	
	public void setRole(Role role, Server server) {
		this.role=role;
		roles.put(server, role);
	}
	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	String realName;
	int id;
	ArrayList<User> Blocks= new ArrayList<User>();
	String profilePic;
	
		public void addBlock(User BlockedUser)
		{
			Blocks.add(BlockedUser);
		}
		public void removeBlock(User BlockedUser)
		{
			Blocks.remove(BlockedUser);
		}
		public void setProfileData(String profileData)
		{
			this.profileData=profileData;
		}
		public void setUserName(String realName, String userName)
		{
			this.realName=realName;
			this.userName=userName;
			
		}
		
		
}
