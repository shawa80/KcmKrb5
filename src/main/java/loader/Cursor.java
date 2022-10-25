package loader;

import java.io.Closeable;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Cursor implements Closeable {

	private Pointer context;
	private Pointer ccache;
	private krb5_creds cred;
	
	private PointerByReference ccCursor = new PointerByReference();

	public Cursor(Pointer context, Pointer ccache) {
		this.context = context;
		this.ccache = ccache;
		
		cred = new krb5_creds.ByReference();
		
		CLibrary.INSTANCE.krb5_cc_start_seq_get(context, ccache, ccCursor);
	}

	public boolean hasNext() {
		
		if (cred != null) //TODO where to free???
			CLibrary.INSTANCE.krb5_free_cred_contents(context, cred);
		
		int result = CLibrary.INSTANCE.krb5_cc_next_cred(context, ccache, ccCursor, cred);
		
		return result == 0; //TODO close on finished?
	}
	public krb5_creds get() {
		
		return cred;
	}
	
	public void close() { //TODO add to final?
		CLibrary.INSTANCE.krb5_cc_end_seq_get(context, ccache, ccCursor);
	}
}
