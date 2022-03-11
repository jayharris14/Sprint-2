package concord;

import java.util.ArrayList;
import java.util.HashMap;


public class Server 
{
	HashMap<User, Role> server= new HashMap<User, Role>();
	ArrayList<Channel> channels= new ArrayList<Channel>();
	ArrayList<Channel> pins= new ArrayList<Channel>();
	String name;
	RoleBuilder rolebuilder= new RoleBuilder();
	ServerManager servermanager;
	
	Server(ServerManager servermanager){
		servermanager.addServer(this);
	}
	
	public void setRoleBuilder(User user, Role role)
	{
		Role x=rolebuilder.createUserRole(role.name);
		user.setRole(x, this);
		
	}

	public String invite(User admin, User User) {
		String e="";
	    if (admin.roles.get(this).inviteUser==true){
		String c=User.userName;
		e=c+ " invited to "+ this.name;
		}	
	    else {
	    	e="permission denied";
	    }
		return e;
	}

		
	
	public String kick(User admin, User user) {
		String w="";
		if (admin.roles.get(this).removeMember==true) {
		String a=user.userName;
		String b=name;
		this.removeMember(admin, user);
		w=a+ "kicked from"+ b;
		}
		else {
			w="permission denied";
		}
		return w;
		
	}
	
	public void addChannel(User admin, Channel channel) {
		if (admin.roles.get(this).addChannel==true) {
		channels.add(channel);
		}
	}
	public void deleteChannel(User admin, Channel channel) {
		if (admin.roles.get(this).removeChannel==true) {
		channels.remove(channel);
		}
	}
	
	public void addPin(Channel channel) {
		pins.add(channel);
	}
	
	public void unPin(Channel channel) {
		pins.remove(channel);
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	public void addMember(User admin, User user){
		if (admin.roles.get(this).addMember==true) {
		Role D= new Role();
		D.name="member";
		this.setRoleBuilder(user, D);
		server.put(user, user.roles.get(this));
	}
	}
	
	public void removeMember(User admin, User user) {
		if (admin.roles.get(this).addMember==true) {
		server.remove(user);
	}}
	
	public void changeRole(Role newrole, User user) {
		server.put(user, newrole);
	}
	
	
}


	
	
	
