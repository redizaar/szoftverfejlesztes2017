package swdevelop.BudgetManager.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	int greetServer(String username,String password) throws IOException;
	
	boolean registerUser(String username,String password) throws IOException;
}
