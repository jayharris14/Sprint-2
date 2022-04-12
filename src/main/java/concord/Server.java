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
    String autho="yes";
	
	Server(ServerManager servermanager){
		servermanager.addServer(this);
	}
	
	public Server() {

	}
	
	/**
	 * @param server
	 * @param channels
	 * @param pins
	 * @param name
	 * @param rolebuilder
	 * @param servermanager
	 */
	public Server(HashMap<User, Role> server, ArrayList<Channel> channels, ArrayList<Channel> pins, String name,
			RoleBuilder rolebuilder, ServerManager servermanager) {
		super();
		this.server = server;
		this.channels = channels;
		this.pins = pins;
		this.name = name;
		this.rolebuilder = rolebuilder;
		this.servermanager = servermanager;
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
	
	public String addChannel(User admin, Channel channel) {
		if (admin.roles.get(this).addChannel==true) {
		channels.add(channel);
		}
		else {
			autho="no";
		}
		return autho;
	}
	public String deleteChannel(User admin, Channel channel) {
		if (admin.roles.get(this).removeChannel==true) {
		channels.remove(channel);
		}
		else {
			autho="no";
		}
		return autho;
		
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

	public String addMember(User admin, User user){
		
		if (admin.roles.get(this).addMember==true) {
		Role D= new Role();
		if (user.role==null) {
			D.name="member";	
		}
		else {
			D.name="admin";
		}
		this.setRoleBuilder(user, D);
		server.put(user, user.roles.get(this));
		}
		else {
			autho="no";
	}
		return autho;
	}
	
	public String removeMember(User admin, User user) {
		if (admin.roles.get(this).addMember==true) {
		server.remove(user);
	}
		else {
			autho="no";
		}
		return autho;
	}
	
	public void changeRole(Role newrole, User user) {
		server.put(user, newrole);
	}

	public void put(User user, Role role) {
		// TODO Auto-generated method stub
		
	}

	public HashMap<User, Role> getServer() {
		return server;
	}

	public void setServer(HashMap<User, Role> server) {
		this.server = server;
	}

	public ArrayList<Channel> getChannels() {
		return channels;
	}

	public void setChannels(ArrayList<Channel> channels) {
		this.channels = channels;
	}

	public ArrayList<Channel> getPins() {
		return pins;
	}

	public void setPins(ArrayList<Channel> pins) {
		this.pins = pins;
	}

	public RoleBuilder getRolebuilder() {
		return rolebuilder;
	}

	public void setRolebuilder(RoleBuilder rolebuilder) {
		this.rolebuilder = rolebuilder;
	}

	public ServerManager getServermanager() {
		return servermanager;
	}

	public void setServermanager(ServerManager servermanager) {
		this.servermanager = servermanager;
	}
	
	
}


	
	
	
