package com.shawtonabbey.krb5;

import java.util.Iterator;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class CCache implements Iterable<krb5_creds>, AutoCloseable {

	private Pointer ccache;
	private Context context;
	private boolean isClosed = false;
	
	public CCache(Context context) {
		
		this.context = context;
		PointerByReference ccacheRef = new PointerByReference();
		
		CLibrary.INSTANCE.krb5_cc_default(context.getContextPointer(), ccacheRef);
        ccache = ccacheRef.getValue();
		
	}
	
	public String getType() {
		
		if (isClosed)
			throw new IllegalStateException("Already cleaned up");
		
		return CLibrary.INSTANCE.krb5_cc_get_type(context.getContextPointer(), ccache);
	}
	

	@Override
	public Iterator<krb5_creds> iterator() {
		
		if (isClosed)
			throw new IllegalStateException("Already cleaned up");
		
		var cursor = new Cursor(context, ccache);
		
		context.addToCleanup(cursor);
		
		return cursor;
	}

	@Override
	public void close() throws Exception {
		
		if (isClosed)
			return;
		
		//TODO clean up cache?
		
		
	}
	
}
