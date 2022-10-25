package loader;

public class LoadKbr5 {

		
		
		public static void main(String[] args) {
			
				        
			var context = new Context();
			
			var cache = context.getDefaultCache();
	        System.out.println(cache.getType());
	        
	        //try (var ccCursor = cache.startSeq()){
        
	        	//TODO need to do memory management
	        	for(var cred : cache) {
	        		System.out.println(cred.client.getNameFQN());
			        System.out.println(cred.server.getNameFQN());
			            
			        for (var b : cred.ticket.getData())
			        	System.out.print(String.format("%02x", b));
			        System.out.println();
	        	}
	        	
	        //}

	        //Pointer[] ps = cred.addresses.getPointer().getPointerArray(0);
	        //System.out.println(ps.length);
	        
	        //Pointer[] p2 = cred.authdata.getPointer().getPointerArray(0);
	        //System.out.println(p2.length);
	    }

	
}
