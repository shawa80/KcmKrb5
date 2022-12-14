package com.shawtonabbey.kerberos;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;

import com.sun.security.auth.module.Krb5LoginModule;

public class KerberosLogin extends Krb5LoginModule {

	public static String cachePath = null;
	

	@Override
	public void initialize(Subject sub, CallbackHandler callback, Map<String, ?> sharedState, Map<String, ?> options) {
		
		var map = copy(options);
		
		map.put("useTicketCache", "true");

		map.put("tryFirstPass",  "false");
		map.put("doNotPrompt",  "true");
		map.put("debug",  "true");
		map.put("client",  "true");

		
		var handler = new UserPassPrompt();
		super.initialize(sub, handler, sharedState, map);
		
	}
	
	private Map<String, Object> copy(Map<String, ?> options) {
		
		var map = new HashMap<String, Object>();
		for (var key : options.keySet()) {
			var value = map.get(key);
			
			map.put(key, value);
		}
		return map;
	}


}
