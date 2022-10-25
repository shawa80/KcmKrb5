package loader;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class LoadKbr5 {

		
		
		public static void main(String[] args) {
			
				        
			Context context = new Context();
			
			CCache cache = context.getDefaultCache();
	        System.out.println(cache.getType());
	        
	        Cursor ccCursor = cache.startSeq();
        
	        krb5_creds cred = ccCursor.Next();
	        
	        System.out.println(cred.client.realm.length);
	        
	        System.out.println(cred.client.getNameFQN());
	        System.out.println(cred.server.getNameFQN());
	        
	        System.out.println(cred.keyblock.enctype);
	        System.out.println(cred.times.authtime);
	        System.out.println(cred.is_skey);
	        System.out.println(cred.ticket_flags);
	        
	        for (byte b : cred.ticket.getData())
	        	System.out.print(String.format("%02x", b));
	        
	        System.out.println();
	        
	        
	        Pointer[] ps = cred.addresses.getPointer().getPointerArray(0);
	        System.out.println(ps.length);
	        
	        Pointer[] p2 = cred.authdata.getPointer().getPointerArray(0);
	        System.out.println(p2.length);
	    }

	
}
