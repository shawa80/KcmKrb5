package loader;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class LoadKbr5 {

		
		
		public static void main(String[] args) {
			
			PointerByReference context = new PointerByReference();
			PointerByReference ccache = new PointerByReference();
			PointerByReference ccCursor = new PointerByReference();
			
			krb5_creds[] cred = new krb5_creds[1];
			krb5_creds c = new krb5_creds();
			cred[0] = c;
			
	        int result = CLibrary.INSTANCE.krb5_init_context(context);
	        System.out.println(result);
	        
	        result = CLibrary.INSTANCE.krb5_cc_default(context.getValue(), ccache);
	        System.out.println(result);
	        
	        String cacheName = CLibrary.INSTANCE.krb5_cc_get_type(context.getValue(), ccache.getValue());
	        System.out.println(cacheName);
	        
	        result = CLibrary.INSTANCE.krb5_cc_start_seq_get(context.getValue(), ccache.getValue(), ccCursor);
	        
	        result = CLibrary.INSTANCE.krb5_cc_next_cred(context.getValue(), ccache.getValue(), ccCursor, cred);
	        
	        System.out.println(cred[0].client.realm.length);
	        
	        for (krb5_data x : cred[0].client.getData()) {
	        	System.out.print(x.getDataAsString());
	        }
	        System.out.println(cred[0].client.realm.data);
	        
	        for (krb5_data x : cred[0].server.getData()) {
	        	System.out.print(x.getDataAsString());
	        }
	        System.out.println(cred[0].server.realm.data);
	        
	        System.out.println(cred[0].keyblock.enctype);
	        System.out.println(cred[0].times.authtime);
	        System.out.println(cred[0].is_skey);
	        System.out.println(cred[0].ticket_flags);
	        
	        for (byte b : cred[0].ticket.getData())
	        	System.out.print(String.format("%02x", b));
	        
	        System.out.println();
	        
	        
	        Pointer[] ps = cred[0].addresses.getPointer().getPointerArray(0);
	        System.out.println(ps.length);
	        
	        Pointer[] p2 = cred[0].authdata.getPointer().getPointerArray(0);
	        System.out.println(p2.length);
	    }

	
}
