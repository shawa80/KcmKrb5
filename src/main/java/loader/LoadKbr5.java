package loader;

public class LoadKbr5 {

		
		
		public static void main(String[] args) {
			
				        
			Context context = new Context();
			
			CCache cache = context.getDefaultCache();
	        System.out.println(cache.getType());
	        
	        Cursor ccCursor = cache.startSeq();
        
	        while(ccCursor.hasNext()) {
	        
	        	krb5_creds cred= ccCursor.get();
        
		        System.out.println(cred.client.getNameFQN());
		        System.out.println(cred.server.getNameFQN());
		            
		        for (byte b : cred.ticket.getData())
		        	System.out.print(String.format("%02x", b));
		        System.out.println();
	        }
	        ccCursor.close();
	        //Pointer[] ps = cred.addresses.getPointer().getPointerArray(0);
	        //System.out.println(ps.length);
	        
	        //Pointer[] p2 = cred.authdata.getPointer().getPointerArray(0);
	        //System.out.println(p2.length);
	    }

	
}
