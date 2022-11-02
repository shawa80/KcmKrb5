package com.shawtonabbey.krb5;

import sun.security.krb5.Asn1Exception;
import sun.security.krb5.Credentials;
import sun.security.krb5.EncryptedData;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.PrincipalName;
import sun.security.krb5.Realm;
import sun.security.krb5.RealmException;
import sun.security.krb5.internal.KerberosTime;
import sun.security.krb5.internal.Ticket;
import sun.security.krb5.internal.TicketFlags;

public class CredentialsMapper {

	
	public static Credentials map(krb5_creds cred) {
		
		try {
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
	        
	        var credential = new Credentials(
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
	        
	        return credential;
        
		} catch (RealmException | Asn1Exception e) {
			return null;
		}
	}
}
