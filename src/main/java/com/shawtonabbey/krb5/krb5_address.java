package com.shawtonabbey.krb5;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "magic", "addrtype", "length", "contents"})
public class krb5_address extends Structure {
	
	public static class ByReference extends krb5_address implements Structure.ByReference { }
	
    public int magic;
    public int addrtype;
    public int length; //unsigned
    public Pointer contents;
    
    public byte[] getContents() {
    	
    	return contents.getByteArray(0, length);
    }

}
