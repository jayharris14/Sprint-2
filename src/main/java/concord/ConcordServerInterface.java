package concord;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ConcordServerInterface extends Remote{

		public String verify(User user, String u, String pw) throws RemoteException;
		public String invite(User admin, User User) throws RemoteException;
		public String kick(User admin, User user) throws RemoteException;
		public String addChannel(User admin, Channel channel) throws RemoteException;
		public String deleteChannel(User admin, Channel channel) throws RemoteException;
		public String addPin(Channel channel) throws RemoteException;
		public String unPin(Channel channel) throws RemoteException;
		public String addMember(User admin, User user) throws RemoteException;
		public String removeMember(User admin, User user) throws RemoteException;
		public String changeRole(Role newrole, User user) throws RemoteException;
		public String setRoleBuilder(User user, Role role) throws RemoteException;
		public String sendMessage(Message message, User user, User user2) throws RemoteException;
		public boolean equals(ConcordServer concorddata) throws RemoteException;
		public boolean serverequals(ConcordServer concorddata) throws RemoteException;
		public boolean channelsequals(ConcordServer concordddata) throws RemoteException;
		public boolean directonversationequals(ConcordServer concordddata) throws RemoteException;
		
}
	

