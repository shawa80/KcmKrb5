package com.shawtonabbey.krb5;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "magic", "enctype", "length", "contents"})
public class krb5_keyblock extends Structure {

	public static class ByValue extends krb5_keyblock implements Structure.ByValue { }
	
	public int magic;
	public int enctype;
    public int length;  //unsigned
    public Pointer contents;  //bytes
	
    
    public byte[] getContents() {
    	
    	return contents.getByteArray(0, length);
    }
}
