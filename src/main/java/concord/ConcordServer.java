package concord;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ConcordServer 
	extends UnicastRemoteObject
	implements ConcordServerInterface 
{
	private static final long serialVersionUID= -2;
	private static final String SERIALIZED_FILE_NAME1="Concord.xml";
	
	private int visits=0;
	private String c;
	String check;
	String run;
	Concord concord;
    Server server;
	User user;
	DirectConversation directconversation;
	Channel channel;
    Role role;
	private String password;
	String Y;




	public Server getServer() {
		return server;
	}


	public void setServer(Server server) {
		this.server = server;
	}


	public ConcordServer() throws RemoteException
	{
		super();
	}
	
	
	@Override
	public String verify(User user, String u, String pw) {
		visits++;
		System.out.println("verifying");
		if (u==user.userName && pw==user.password) {
				c="Permission Granted";
				check="yes";
				this.setUser(user);
			}
		else {
			c="Access Denied";
			check="no";
		}
		return c;
	}
	
	public static void main(String[] args)
	{
		try
		{
			ConcordServer M = new ConcordServer();
			Naming.rebind("Concord.xml", M);
			
		 }catch (RemoteException e)
		{
			 e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	


	public int getVisits() {
		return visits;
	}


	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public String invite(User admin, User user) {
		String accept=server.invite(admin, user);
		return accept;
	}
	
	public String kick(User admin, User user) {
		String d=server.kick(user, admin);
		this.storeToDisk();
		return d;
	}
	
	public String addChannel(User admin, Channel channel){
		if (check=="yes") {
			Y= server.addChannel(admin, channel);
			if (Y=="yes") {
				run="success";
			}
			else {
				run="not authorized";
			}
		}
		else {
			run="not authorized";
			
		}
		this.storeToDisk();
		return run;
	}
	public String deleteChannel(User admin, Channel channel) {
		if (check=="yes") {
			Y=server.deleteChannel(admin, channel);
			if (Y=="yes") {
				run="success";
			}
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String addPin(Channel channel) {
		if (check=="yes") {
			server.addPin(channel);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String unPin(Channel channel) {
		if (check=="yes") {
			server.unPin(channel);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String addMember(User admin, User user) {
		if (check=="yes") {
			Y=server.addMember(admin, user);
			if (Y=="yes") {
				run="success";
			}
			
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String removeMember(User admin, User user) {
		if (check=="yes"){
			Y=server.removeMember(admin, user);
			if (Y=="yes") {
				run="success";
			}
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	
	public String changeRole(Role newrole, User user) {
		if (check=="yes") {
			server.changeRole(newrole, user);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	
	public String sendMessage(Message message, User user, User user2)
	{
		if (check=="yes") {
			directconversation.sendMessage(message, user, user2);
			run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public DirectConversation getDirectconversation() {
		return directconversation;
	}


	public void setDirectconversation(DirectConversation directconversation) {
		this.directconversation = directconversation;
	}


	public String setRoleBuilder(User user, Role role)
	{
		if (check=="yes") {
			server.setRoleBuilder(user, role);
			run="success";
			
		}
		else {
			run="not authorized";
			
		}
		this.storeToDisk();
		return run;
	}
	
	public void setPassword(String password) {
		user.setPassword(password);
	}

		public void addBlock(User BlockedUser)
		{
			user.addBlock(user);
		}
		public void removeBlock(User BlockedUser)
		{
			user.removeBlock(user);;
		}
		public void setProfileData(String profileData)
		{
			user.setProfileData(profileData);
		}
		public void setUserName(String realName, String userName)
		{
			user.realName=realName;
			user.userName=userName;
			
		}
		
		
	
	
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try {
			encoder=new XMLEncoder(new BufferedOutputStream
					(new FileOutputStream("Concord.xml")));
		}catch(FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening");
			
		}
	
		encoder.writeObject(this);
		encoder.close();

	}

	public static ConcordServer ReadFromDisk() 
	{	
			
			XMLDecoder decoder=null;
			try {
				decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME1)));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File Concord.xml not found");
			}
			ConcordServer concorddata=(ConcordServer)decoder.readObject();
			return concorddata;
			
			
		}

	public boolean serverequals(ConcordServer that) {
		ConcordServer x;
		x=this.ReadFromDisk();
		boolean y;
		if ((server.server.size())==(that.server.server.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}
	
	public boolean channelsequals(ConcordServer that) {
		ConcordServer x;
		x=this.ReadFromDisk();
		boolean y;
		if (server.channels.size()==(that.server.channels.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}
	
	public boolean directconversationequals(ConcordServer that) {
		ConcordServer x;
		x=this.ReadFromDisk();
		boolean y;
		if (directconversation.messages.size()==(that.directconversation.messages.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public boolean equals(ConcordServer concorddata) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean directonversationequals(ConcordServer concordddata) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}


	
	



