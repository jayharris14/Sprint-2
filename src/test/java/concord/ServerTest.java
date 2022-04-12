package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
	UserManager usermanager= new UserManager();

	@BeforeEach
	void setUp() throws Exception {
		
		cs=new ConcordServer();
		cc=new ConcordClient();
		registry=LocateRegistry.createRegistry(2099);
		registry.rebind("CONCORD", cs);
		user1= new User(usermanager);
		user1.setUserName("jay", "jhoya101");
		user1.setPassword("asdf");
		String[] names=registry.list();
		for (String name:names)
		{
			System.out.println("name " + name);
		}
	}
	

	@Test
	void clientTest()
	{
		ConcordServerInterface cp;
		String answer= "";
		try {
			
			cp = (ConcordServerInterface) registry.lookup("CONCORD");			
			answer = cp.verify(user1, "jhoya101", "asdf");
			} 
			catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Bad call to registry");
		}
			
		assertEquals("Permission Granted", answer);	
		assertEquals(1, cs.getVisits());
		cs.storeToDisk();
		cs.ReadFromDisk();
		
	}
	@After
	void tearDown() throws Exception {
		
		registry.unbind("CONCORD");
	}



}

