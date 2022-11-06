package com.shawtonabbey.kerberos.kcm;

import com.shawtonabbey.krb5.krb5_creds;

import sun.security.krb5.Credentials;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.PrincipalName;
import sun.security.krb5.Realm;
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
	
	
	       
	        var ticket = new Ticket(cred.ticket.getData());

	        
	        var encrKey = new EncryptionKey(
	        		cred.keyblock.getContents(),
	        		cred.keyblock.enctype,
	                null);
	        
	        var flags = new TicketFlags(cred.getFlags());
	        
	        var authTimeMilsec = (cred.times.authtime & 0xFFFFFFFFL) * 1000;
	        var startTimeMilsec = (cred.times.starttime & 0xFFFFFFFFL) * 1000;
	        var endTimeMilsec = (cred.times.endtime & 0xFFFFFFFFL) * 1000;
	        var renewTimeMilsec = (cred.times.renew_till & 0xFFFFFFFFL) * 1000;
	        
	        
	        var authTime = new KerberosTime(authTimeMilsec);
	        var startTime = new KerberosTime(startTimeMilsec);
	        var endTime = new KerberosTime(endTimeMilsec);
	        var renew = new KerberosTime(renewTimeMilsec);
	        
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
        
		} catch (Exception e) {
			return null;
		} 
	}
}
