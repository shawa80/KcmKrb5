package loader;

import sun.security.krb5.*;
import sun.security.krb5.internal.KerberosTime;
import sun.security.krb5.internal.Ticket;
import sun.security.krb5.internal.TicketFlags;


public class LoadKbr5 {
		
		public static void main(String[] args) {

			try (var context = new Context()) {
			
				var cache = context.getDefaultCache();
				System.out.println(cache.getType());
        
	        	//TODO need to do memory management
	        	for(var cred : cache) {
	        		System.out.println(cred.client.getNameFQN());
			        System.out.println(cred.server.getNameFQN());
			            
			        for (var b : cred.ticket.getData())
			        	System.out.print(String.format("%02x", b));
			        System.out.println();

			        
			        var realm = new Realm(cred.client.realm.getDataAsString());
			        
			        var clientPrincipalName = new PrincipalName(
		        		cred.client.type,
		        		cred.client.getDatas(),
		        		realm
			        );

			        var serverPrincipalName = new PrincipalName(
			        		cred.server.type,
			        		cred.server.getDatas(),
			        		realm
				        );

			        
			        var encrypted = new EncryptedData(
						cred.keyblock.enctype, //ummm is this right?
						null,
						cred.ticket.getData()
			        );
	        	
			       
			        var ticket = new Ticket(
			        	clientPrincipalName, //which principal goes here??
			        	encrypted
			        );
			        
			        var encrKey = new EncryptionKey(
			        		cred.keyblock.getContents(),
			        		cred.keyblock.enctype,
	                        null);
			        
			        var flags = new TicketFlags(cred.getFlags());
			        
			        var authTime = new KerberosTime(cred.times.authtime);
			        var startTime = new KerberosTime(cred.times.starttime);
			        var endTime = new KerberosTime(cred.times.endtime);
			        var renew = new KerberosTime(cred.times.renew_till);
			        
			        var test = new Credentials(
			        		ticket,
			        		clientPrincipalName, 
			        		null,
			        		serverPrincipalName,
			        		null,
			        		encrKey,
			        		flags,
			        		authTime,
			        		startTime,
			        		endTime,
			        		renew,
			        		null //HostAddresses
			        );
	        	}
	        	
	        } catch (Exception ex) {
	        	
	        }

	        //Pointer[] ps = cred.addresses.getPointer().getPointerArray(0);
	        //System.out.println(ps.length);
	        
	        //Pointer[] p2 = cred.authdata.getPointer().getPointerArray(0);
	        //System.out.println(p2.length);
	        	
	        	

	    }

	
}
