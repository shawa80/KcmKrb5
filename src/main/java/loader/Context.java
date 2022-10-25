package loader;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Context
{
	private Pointer context;

	public Context() {
		
		PointerByReference contextRef = new PointerByReference();
		
		CLibrary.INSTANCE.krb5_init_context(contextRef);
        context = contextRef.getValue();
		
	}
	
	public CCache getDefaultCache() {
		return new CCache(context);
	}
	
	
}
