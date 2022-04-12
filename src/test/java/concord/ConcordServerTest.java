package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerTest {
	
	ConcordServer cs;
	ConcordClient cc;
	Registry registry;
	User user1;
	User user2;
	Role role1;
	Channel channel;
	Channel channel2;
	Server server1;
	Message message1;
	DirectConversation directconversation1;
	DirectConversationManager directconversationmanager=new DirectConversationManager();
	UserManager usermanager= new UserManager();
	ServerManager servermanager= new ServerManager();
	RoleBuilder roleBuilder= new RoleBuilder();
	HashMap<User, Role> users= new HashMap<User, Role>();
	ArrayList<Channel> channels= new ArrayList<Channel>();
	ArrayList<Channel> pins= new ArrayList<Channel>();
	String name;
	RoleBuilder rolebuilder= new RoleBuilder();


	@BeforeEach
	void setUp() throws Exception {
		
		
		cs=new ConcordServer();
		cc=new ConcordClient();
		registry=LocateRegistry.createRegistry(2099);
		registry.rebind("CONCORD", cs);
		user1= new User(usermanager);
		user1.setUserName("jay", "jhoya101");
		user1.setPassword("asdf");
		user2= new User(usermanager);
		user2.setUserName("qua", "quavo");
		user2.setPassword("qawe");
		message1= new Message();
		directconversation1= new DirectConversation(directconversationmanager);
		server1= new Server(users, channels, pins, "school",
				rolebuilder, servermanager);
		cs.setServer(server1);
		cs.setDirectconversation(directconversation1);
		role1 = new Role();
		role1.name="admin";
		channel= new Channel();
		channel.setName("activities");
		channel2= new Channel();
		channel2.setName("prizes");
		message1.setUser(user1); /*set user of message*/
		message1.setContent("Hi");
		cs.setRoleBuilder(user1, role1);
		String[] names=registry.list();
		for (String name:names)
		{
			System.out.println("name " + name);
		}
	}
	

	@Test
	void clientTest() throws Exception
	{
		ConcordServerInterface cp;
		String answer= "";
		String result ="";
		try {
			
			cp = (ConcordServerInterface) registry.lookup("CONCORD");			
			answer = cp.verify(user1, "jhoya101", "asd"); /*test entering bad password. and attaempt to perform actions without valid credentials */
			result= cp.setRoleBuilder(user1, role1);
			assertEquals("Access Denied", answer);
			assertEquals("not authorized", result);
			result=cp.addMember(user1, user1); 
			assertEquals("not authorized", result);
			result=cp.addMember(user1, user2);
			assertEquals("not authorized", result);
			result=cp.addChannel(user1, channel);
			assertEquals("not authorized", result);
			result=cp.addChannel(user1, channel2);
			assertEquals("not authorized", result);
			result=cp.deleteChannel(user1, channel2);
			assertEquals("not authorized", result);
			result=cp.sendMessage(message1, user1, user2);
			assertEquals("not authorized", result);
			answer = cp.verify(user1, "jhoya101", "asdf");
			result= cp.setRoleBuilder(user1, role1);
			assertEquals("Permission Granted", answer); /*test with valid password and test that each function reads to disk after call*/
			assertEquals("success", result);
			result=cp.addMember(user1, user1);
			assertEquals("success", result);
			result=cp.addMember(user1, user2);
			ConcordServer diskf=ConcordServer.ReadFromDisk();
			assertEquals(true, cs.serverequals(diskf));
			assertEquals("success", result);
			result=cp.addChannel(user1, channel);
			assertEquals(false, cs.channelsequals(diskf));
			ConcordServer diskf1=ConcordServer.ReadFromDisk();
			assertEquals(true, cs.channelsequals(diskf1));
			assertEquals("success", result);
			result=cp.addChannel(user1, channel2);
			assertEquals("success", result);
			result=cp.deleteChannel(user1, channel2);
			assertEquals("success", result);
			result=cp.sendMessage(message1, user1, user2);
			assertEquals("success", result);
			assertEquals(false, cs.directconversationequals(diskf1));
			ConcordServer diskf2=ConcordServer.ReadFromDisk();
			assertEquals(true, cs.directconversationequals(diskf2));
			answer = cp.verify(user2, "quavo", "qawe");
			result=cp.addChannel(user2, channel); /*test valid role to perform certain actions*/
			assertEquals("not authorized", result);
			result=cp.removeMember(user2, user1);
			assertEquals("not authorized", result);
			result=cp.deleteChannel(user2, channel);
			assertEquals("not authorized", result);
		}
			catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Bad call to registry");
		}
			
		
		
		
		
		
	}
	@After
	void tearDown() throws Exception {
		
		registry.unbind("CONCORD");
	}



}

