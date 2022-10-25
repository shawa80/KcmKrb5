package loader;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class LoadKbr5 {

		
		
		public static void main(String[] args) {
			
			PointerByReference contextRef = new PointerByReference();
			PointerByReference ccacheRef = new PointerByReference();
			PointerByReference ccCursor = new PointerByReference();
			
			krb5_creds cred = new krb5_creds.ByReference();
			
	        CLibrary.INSTANCE.krb5_init_context(contextRef);
	        Pointer context = contextRef.getValue();
	        
	        CLibrary.INSTANCE.krb5_cc_default(context, ccacheRef);
	        Pointer ccache = ccacheRef.getValue();
	        
	        String cacheName = CLibrary.INSTANCE.krb5_cc_get_type(context, ccache);
	        System.out.println(cacheName);
	        
	        CLibrary.INSTANCE.krb5_cc_start_seq_get(context, ccache, ccCursor);
	        
	        CLibrary.INSTANCE.krb5_cc_next_cred(context, ccache, ccCursor, cred);
	        
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
