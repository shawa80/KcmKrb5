package loader;

import com.shawtonabbey.krb5.Context;
import com.shawtonabbey.krb5.CredentialsMapper;
import com.shawtonabbey.krb5.TicketFlags;

import sun.security.krb5.Credentials;
import sun.security.krb5.PrincipalName;

public class CreateCredIntercepter {
	
	
	public static Credentials acquireTGTFromCache(PrincipalName princ,
            String ticketCache) {
		return acquireDefaultCreds();
	}
	
	public static Credentials acquireDefaultCreds() {
		
		try (var context = new Context()) {
			
			var cache = context.getDefaultCache();
			System.out.println(cache.getType());
    
        	//TODO need to do memory management
        	for(var cred : cache) {
        		//System.out.println(cred.client.getNameFQN());
		        //System.out.println(cred.server.getNameFQN());
		            
		        //for (var b : cred.ticket.getData())
		        //	System.out.print(String.format("%02x", b));
		        //System.out.println();

        		if (cred.getFlagSet().stream().anyMatch(x -> x == TicketFlags.TKT_FLG_INITIAL))
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


//Pointer[] ps = cred.addresses.getPointer().getPointerArray(0);
//System.out.println(ps.length);

//Pointer[] p2 = cred.authdata.getPointer().getPointerArray(0);
//System.out.println(p2.length);
