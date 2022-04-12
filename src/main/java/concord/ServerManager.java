package concord;

import java.util.ArrayList;

public class ServerManager
{
	Server server;
	ArrayList<Server> SM= new ArrayList<Server>();
	
	public void addServer(Server server) {
		SM.add(server);
	}
	
	public ServerManager() {
		
	}
	
	public String getuserservers(User user) {
		String userservers="";
		for (int i=0; i < SM.size(); i++) {
			if (SM.get(i).server.containsKey(user)==true) {
				if (i==0) {
				userservers=userservers +(SM.get(i).name);
			}
				else {
					userservers=userservers +", "+ (SM.get(i).name);
				}
		}
	} return userservers;
}
}