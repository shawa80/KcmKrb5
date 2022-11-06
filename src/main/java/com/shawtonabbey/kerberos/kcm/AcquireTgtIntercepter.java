package com.shawtonabbey.kerberos.kcm;

import com.shawtonabbey.krb5.Context;
import com.shawtonabbey.krb5.TicketFlags;

import sun.security.krb5.Credentials;
import sun.security.krb5.PrincipalName;

public class AcquireTgtIntercepter {
	
	
	public static Credentials acquireTGTFromCache(
			PrincipalName princ,
            String ticketCache) 
	{
		
		try (var context = new Context()) {
			
			var cache = context.getDefaultCache();
    
        	for(var cred : cache) {

        		if (cred.getFlagSet().stream()
        			.anyMatch(x -> x == TicketFlags.TKT_FLG_INITIAL))
        		{
        			var test = CredentialsMapper.map(cred);
        			return test;
        		}
        	}
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		
		return null;
	}

}

