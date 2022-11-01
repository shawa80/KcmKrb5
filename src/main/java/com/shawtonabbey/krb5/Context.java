package com.shawtonabbey.krb5;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Context implements AutoCloseable
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

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
