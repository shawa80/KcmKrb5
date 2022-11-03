package com.shawtonabbey.krb5;

import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "magic", "client", "server", "keyblock", "times", 
	"is_skey", "ticket_flags", "addresses", "ticket", "second_ticket", "authdata"})
public class krb5_creds extends Structure {


	public static class ByReference extends krb5_creds implements Structure.ByReference { }
	public static class ByValue extends krb5_creds implements Structure.ByValue { }

	
	public int magic;
    public krb5_principal.ByReference client;
    public krb5_principal.ByReference server;
    public krb5_keyblock.ByValue keyblock;
    
    public krb5_ticket_times.ByValue times;
    public int is_skey;               /**< true if ticket is encrypted in another ticket's skey */  //TODO add function to convert to bool
    public int ticket_flags;
    
    public krb5_address.ByReference addresses; //krb5_address **addresses;           /**< addrs in ticket */, null terminated list
    public krb5_data.ByValue ticket;
    public krb5_data.ByValue second_ticket;
    public krb5_authdata.ByReference authdata;          //null terminated list?
	
    public boolean[] getFlags() {
    	return TicketFlags.flagSet(ticket_flags);
    }
    
    public List<TicketFlags> getFlagSet() {
    	return TicketFlags.getSet(ticket_flags);
    }


}
