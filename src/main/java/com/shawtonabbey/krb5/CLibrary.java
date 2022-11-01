package com.shawtonabbey.krb5;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface CLibrary extends Library {
	
    CLibrary INSTANCE = (CLibrary)Native.load("krb5", CLibrary.class);

    int krb5_init_context(PointerByReference context);
    int krb5_cc_default(Pointer context, PointerByReference ccache);
    String krb5_cc_get_type(Pointer context, Pointer ccache);
    int krb5_cc_start_seq_get(Pointer context, Pointer ccache, PointerByReference cursor);
    
    int krb5_cc_next_cred(Pointer context, Pointer ccache, PointerByReference cursor, 
    		krb5_creds cred);
    
    void krb5_free_cred_contents(Pointer context, krb5_creds cred);
    int krb5_cc_end_seq_get(Pointer context, Pointer ccache, PointerByReference cursor);
}
