package concord;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest
{
	User user1;
	User user2;
	User user3;
	Message message1;
	Message message2;
	Message message3;
	Message message4;
	DirectConversation directconversation1;
	DirectConversation directconversation2;
	Server server1;
	Server server2;
	Channel channel1;
	Channel channel2;
	RoleBuilder roleBuilder= new RoleBuilder();
	Role admin;
	Role mod;
	DirectConversationManager directconversationmanager=new DirectConversationManager();
	ServerManager servermanager=new ServerManager();
	UserManager usermanager=new UserManager();
	
	@BeforeEach
	void setUp() throws Exception
	{
		user1= new User(usermanager);
		user1.setUserName("jay", "jhoya101");
		user2= new User(usermanager);
		user2.setUserName("qua", "quavo");
		user3= new User(usermanager);
		user3.setUserName("nick", "nickster");
		message1= new Message();
		message2= new Message();
		message3= new Message();
		message4= new Message();
		directconversation1= new DirectConversation(directconversationmanager);
		directconversation2= new DirectConversation(directconversationmanager);
		server1=new Server(servermanager);
		server1.setName("party");
		server2= new Server(servermanager);
		server2.setName("school");
		channel1= new Channel();
		channel1.setName("activities");
		channel2= new Channel();
		channel2.setName("foodlist");
		admin= new Role();
		admin.name="admin";
		mod= new Role();
		mod.name="mod";
		
		
		
		
	}
	
	@Test
	void usertest() {
		assertEquals("jhoya101", user1.getUserName());
		user1.addBlock(user2);
		assertEquals(user2, user1.Blocks.get(0));
		user1.addBlock(user3);
		assertEquals(user3, user1.Blocks.get(1));
		assertEquals(2, user1.Blocks.size());
		user1.removeBlock(user3);
		assertEquals(1, user1.Blocks.size());
		user1.setProfileData("I am 17");
		assertEquals("I am 17", user1.profileData);
	}
	@Test
	void messagetest(){
		message1.setUser(user1);
		message1.setContent("Hi");
		assertEquals("Hi", message1.getContent());
		message1.setTimestamp("2:00");
		assertEquals("2:00", message1.getTimestamp());
		}
	
	@Test
	void directconversationtest(){
		message2.setUser(user3);
		message2.setContent("Hi Friend");
		message2.setTimestamp("5:00");
		directconversation1.sendMessage(message2, user3, user2);
		assertEquals("5:00", directconversation1.getLastTimestamp());
		assertEquals("Hi Friend", directconversation1.messages.get(0).content);
		assertEquals("nickster", directconversation1.users.get(0).userName);
		assertEquals("quavo", directconversation1.users.get(1).userName);
	}
	@Test
	void servertest(){
		server1.setRoleBuilder(user1, admin);
		server1.setRoleBuilder(user2, mod);
		server1.setName("party");
		server1.invite(user1, user3);
		assertEquals("nickster invited to party", server1.invite(user1, user3));
		server1.invite(user2, user3);
		assertEquals("permission denied", server1.invite(user2, user3));
		server1.addMember(user1, user3);
		assertEquals((true), server1.server.containsKey(user3));
		server1.addChannel(user1, channel1);
		assertEquals(("activities"), server1.channels.get(0).name);
		server1.addChannel(user1, channel2);
		assertEquals(("foodlist"), server1.channels.get(1).name);
		server1.addMember(user1, user2);
		server1.addPin(channel1);
		assertEquals(("activities"), server1.pins.get(0).name);
		assertEquals((1), server1.pins.size());
		server1.unPin(channel1);
		assertEquals((0), server1.pins.size());
		server1.kick(user1, user3);
		assertEquals((false), server1.server.containsKey(user3));
		assertEquals((2), server1.channels.size());
		server1.deleteChannel(user1, channel1);
		assertEquals((1), server1.channels.size());
	}
	
	@Test
	void DirectConversationManagerTest() {
	assertEquals(2, directconversationmanager.DCM.size());
	assertEquals(directconversation1, directconversationmanager.DCM.get(0));
	message3.setUser(user2);
	message3.setContent("Hello");
	message3.setTimestamp("8:00");
	directconversation2.sendMessage(message3, user2, user3);
	assertEquals(directconversation2, directconversationmanager.getpastconversations(user2).get(0));
	
		
	}
	
	@Test
	void ServerManagerTest() {
	server1.setRoleBuilder(user1, admin);
	server2.setRoleBuilder(user1, admin);
	server1.addMember(user1, user3);
	server2.addMember(user1, user3);
	assertEquals(2, servermanager.SM.size());
	assertEquals(server1, servermanager.SM.get(0));
	assertEquals("party, school", servermanager.getuserservers(user3));
}
	@Test
	void UserManagerTest() {
		assertEquals(3, usermanager.UM.size());
		assertEquals("quavo", usermanager.UM.get(1).userName);
		
}}