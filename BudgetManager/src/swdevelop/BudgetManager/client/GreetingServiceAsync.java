package swdevelop.BudgetManager.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String username,String password, AsyncCallback<Integer> callback) throws IOException;
	
	void registerUser(String username,String password, AsyncCallback<Boolean> callback) throws IOException;
}
