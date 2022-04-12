package concord;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;


public class ConcordClient {
	
	ConcordServer cs;
	static User user;
	static User admin;
	static String Username;
	static String Password;
	static Channel channel;
	static Role newrole;
	static Message message;
	static ConcordServer concorddata;
	
	public void submit(String Username, String Password){
		
	}
	public static void main(String[] args) throws Exception 
	{
	try {
		ConcordServerInterface cs =(ConcordServerInterface) Naming.lookup("rmi://127.0.0.1/MATHS");
	
		String entry= cs.verify(user, Username, Password);
		String acceptance=cs.invite(admin, user);
		String kicked=cs.kick(admin, user);
		cs.addChannel(admin, channel);
		cs.deleteChannel(admin, channel);
		cs.addPin(channel);
		cs.unPin(channel);
		cs.addMember(admin, user);
		cs.removeMember(admin, user);
		cs.changeRole(newrole, user);
		cs.setRoleBuilder(user, newrole);
		cs.sendMessage(message, user, user);
		cs.serverequals(concorddata);
		cs.channelsequals(concorddata);
		cs.directonversationequals(concorddata);
		
		System.out.println("Entry is " + entry);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	


}
