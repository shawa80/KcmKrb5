package com.shawtonabbey.krb5;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "authtime", "starttime", "endtime", "renew_till"})
public class krb5_ticket_times extends Structure {

	public static class ByValue extends krb5_ticket_times implements Structure.ByValue { }
	
	//TODO all unsigned ints, add functions to convert to long
    public int authtime;    /**< Time at which KDC issued the initial ticket that corresponds to this ticket */
    public int starttime;   /**< optional in ticket, if not present, use @a authtime */
    public int endtime;     /**< Ticket expiration time */
    public int renew_till;  /**< Latest time at which renewal of ticket can be valid */
	
}
