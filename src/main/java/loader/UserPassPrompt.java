package loader;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

public class UserPassPrompt implements CallbackHandler {

	
	public static String user = null;
	public static String password = null;
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		return;
		
	}

}

