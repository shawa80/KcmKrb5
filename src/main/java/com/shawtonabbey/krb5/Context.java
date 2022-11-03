package com.shawtonabbey.krb5;

import java.util.Stack;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Context implements AutoCloseable
{
	private Pointer context;

	private boolean isClosed = false;
	private Stack<AutoCloseable> thingsToCleanup = new Stack<AutoCloseable>();
	
	public Context() {
		
		PointerByReference contextRef = new PointerByReference();
		
		CLibrary.INSTANCE.krb5_init_context(contextRef);
        context = contextRef.getValue();
		
	}
	
	public CCache getDefaultCache() {
		
		if (isClosed)
			throw new IllegalStateException("Already cleaned up");
		
		var cache = new CCache(this);
		addToCleanup(cache);
		
		return cache;
		
	}

	Pointer getContextPointer() {
		return context;
	}
	
	void addToCleanup(AutoCloseable c) {
		thingsToCleanup.add(c);
	}
	
	@Override
	public void close() throws Exception {
		
		if (isClosed)
			return;
		
		isClosed = true;
		
		while(!thingsToCleanup.isEmpty()) {
			var c = thingsToCleanup.pop();
			c.close();
		}
	}
	
	
}
