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
	Concord concord;
	
	@BeforeEach
	void setUp() throws Exception
	{	
		concord=new Concord();
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
		assertEquals("jhoya101", user1.getUserName()); /*get username from user1*/
		user1.addBlock(user2); /*add user2 to block list*/
		assertEquals(user2, user1.Blocks.get(0)); /*test to see if user is on blocklist*/
		user1.addBlock(user3); /*add second user to block list*/
		assertEquals(user3, user1.Blocks.get(1)); /*test to see if user3 is on blocklist*/
		assertEquals(2, user1.Blocks.size()); /*assure list is size 2 with two members added to block list*/
		user1.removeBlock(user3); /*remove user from blocklist*/
		assertEquals(1, user1.Blocks.size()); /*assure size is reduced to 1*/
		user1.setProfileData("I am 17"); 
		assertEquals("I am 17", user1.profileData);
	}
	@Test
	void messagetest(){
		message1.setUser(user1); /*set user of message*/
		message1.setContent("Hi"); /*set content of message*/ 
		assertEquals("Hi", message1.getContent()); /*assure message content is Hi*/
		message1.setTimestamp("2:00"); /*set timestamp*/
		assertEquals("2:00", message1.getTimestamp()); /*assure timestamp matches one set*/
		}
	
	@Test
	void directconversationtest(){
		message2.setUser(user3); /*set message user*/
		message2.setContent("Hi Friend"); /*set message content*/
		message2.setTimestamp("5:00"); /*set message timestamp*/
		directconversation1.sendMessage(message2, user3, user2); /*send message set from user3 to user2.through direct conversation*/
		assertEquals("5:00", directconversation1.getLastTimestamp());
		assertEquals("Hi Friend", directconversation1.messages.get(0).content); /*assure message content equals message set */
		assertEquals("nickster", directconversation1.users.get(0).userName); /*assure username is same as username of user3*/
		assertEquals("quavo", directconversation1.users.get(1).userName); /*assure username of user2 set in setUp*/
	}
	@Test
	void servertest(){
		server1.setRoleBuilder(user1, admin); /*set user1 to admin*/
		server1.setRoleBuilder(user2, mod); /*set user 2 to mod*/
		server1.setName("party"); /*set name of server to party*/
		server1.invite(user1, user3); /*have user1(admin) invite user3*/
		assertEquals("nickster invited to party", server1.invite(user1, user3)); /*assure invite message equals what it should*/
		server1.invite(user2, user3); /*try to have mod invite user3*/
		assertEquals("permission denied", server1.invite(user2, user3)); /*receive permission denied as mod does not have those permissions*/
		server1.addMember(user1, user3); /*admin adds user*/
		assertEquals((true), server1.server.containsKey(user3)); /*check if user is in server*/
		server1.addChannel(user1, channel1); /*admin add channel*/
		assertEquals(("activities"), server1.channels.get(0).name); /*assure channels is in server and name matches channel name defined in setUp*/
		server1.addChannel(user1, channel2); /*admin add second channel*/
		assertEquals(("foodlist"), server1.channels.get(1).name); /*assure new channel added and name is correct*/
		server1.addMember(user1, user2);
		server1.addPin(channel1); /*pin channel1*/
		assertEquals(("activities"), server1.pins.get(0).name); /*assure added*/
		assertEquals((1), server1.pins.size()); /*check size of pins*/
		server1.unPin(channel1); /*unpin channel1*/
		assertEquals((0), server1.pins.size()); /* assure size was reduced from 1 to 0*/
		server1.kick(user1, user3); /*kick/ remove member from server*/
		assertEquals((false), server1.server.containsKey(user3)); /*assure user is not found in server*/
		assertEquals((2), server1.channels.size()); /*check channel size*/
		server1.deleteChannel(user1, channel1); /*delete channel*/
		assertEquals((1), server1.channels.size()); /*assure channel is deleted by size going from 2 to 1*/
	}
	
	@Test
	void DirectConversationManagerTest() {
	assertEquals(2, directconversationmanager.DCM.size()); /*assure size of directconversation2 is 2*/
	assertEquals(directconversation1, directconversationmanager.DCM.get(0)); /* assure directconversation1 is 1*/
	message3.setUser(user2); /*create new message and send*/
	message3.setContent("Hello");
	message3.setTimestamp("8:00");
	directconversation2.sendMessage(message3, user2, user3);
	assertEquals(directconversation2, directconversationmanager.getpastconversations(user2).get(0)); /*assure can get past conversations by input of user*/
	
		
	}
	
	@Test
	void ServerManagerTest() {
	server1.setRoleBuilder(user1, admin); /*set user1 as admin to server1*/
	server2.setRoleBuilder(user1, admin); /*set user1 as admin to server2*/
	server1.addMember(user1, user3); /*have admin add member to server 1*/
	server2.addMember(user1, user3); /*have admin add member to server2*/
	assertEquals(2, servermanager.SM.size()); /*get size of servermanager and assure it is 2*/
	assertEquals(server1, servermanager.SM.get(0)); /*assure server1 is in servermanager*/
	assertEquals("party, school", servermanager.getuserservers(user3)); /*assure names of two servers user is in is party and school*/
}
	@Test
	void UserManagerTest() {
	/*	assertEquals(3, usermanager.UM.size()); /*get size of usermanager*/
		assertEquals("quavo", usermanager.UM.get(1).userName); /*assure member added in setUP is indeed in usermanager*/;
	}}