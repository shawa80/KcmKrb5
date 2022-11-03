package com.shawtonabbey.krb5;

import java.util.Iterator;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Cursor implements AutoCloseable, Iterator<krb5_creds> {

	private boolean isClosed = false;
	
	private Context context;
	private Pointer ccache;
	private krb5_creds cred;
	
	private PointerByReference ccCursor = new PointerByReference();

	public Cursor(Context context, Pointer ccache) {
		this.context = context;
		this.ccache = ccache;
		
		cred = new krb5_creds.ByReference();
		
		CLibrary.INSTANCE.krb5_cc_start_seq_get(context.getContextPointer(), ccache, ccCursor);
	}

	@Override
	public boolean hasNext() {
		
		if (isClosed)
			throw new IllegalStateException("Already cleaned up");
		
		if (cred != null)
			CLibrary.INSTANCE.krb5_free_cred_contents(context.getContextPointer(), cred);
		
		int result = CLibrary.INSTANCE.krb5_cc_next_cred(context.getContextPointer(), ccache, ccCursor, cred);
		
		return result == 0;
	}
	
	@Override
	public krb5_creds next() {
		
		if (isClosed)
			throw new IllegalStateException("Already cleaned up");
		
		return cred;
	}
	
	public void close() { 
		
		if (isClosed)
			return;
		
		isClosed = true;
		
		if (cred != null)
			CLibrary.INSTANCE.krb5_free_cred_contents(context.getContextPointer(), cred);
		
		CLibrary.INSTANCE.krb5_cc_end_seq_get(context.getContextPointer(), ccache, ccCursor);
	}
}
