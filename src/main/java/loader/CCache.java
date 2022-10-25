package loader;

import java.util.Iterator;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class CCache implements Iterable<krb5_creds> {

	private Pointer ccache;
	private Pointer context;
	
	public CCache(Pointer context) {
		
		this.context = context;
		PointerByReference ccacheRef = new PointerByReference();
		
		CLibrary.INSTANCE.krb5_cc_default(context, ccacheRef);
        ccache = ccacheRef.getValue();
		
	}
	
	public String getType() {
		
		return CLibrary.INSTANCE.krb5_cc_get_type(context, ccache);
	}
	
	public Cursor startSeq() {
		
	
		return new Cursor(context, ccache);
	}

	@Override
	public Iterator<krb5_creds> iterator() {
		return startSeq();
	}
	
}
