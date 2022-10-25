package loader;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Cursor {

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

	public krb5_creds Next() {
		CLibrary.INSTANCE.krb5_cc_next_cred(context, ccache, ccCursor, cred);
		
		return cred;
	}
}
