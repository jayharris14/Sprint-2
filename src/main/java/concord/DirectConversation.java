package concord;

import java.util.ArrayList;

public class DirectConversation
{
	ArrayList<User> users= new ArrayList<User>();
	ArrayList<Message> messages= new ArrayList<Message>();
	DirectConversationManager directconversationmanager=new DirectConversationManager();
	String name;
	
		DirectConversation(DirectConversationManager directconversationmanager){
			directconversationmanager.addDirectConversation(this);
		}

	
	public void sendMessage(Message message, User user, User user2)
	{
		messages.add(message);
		users.add(user);
		users.add(user2);
		
		
	}
	
	public String getLastTimestamp() {
		String c = messages.get(messages.size()-1).timestamp;
		return c;
		
		
	}
	
	
}