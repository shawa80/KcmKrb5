package com.shawtonabbey.krb5;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "magic", "length", "data"})
public class krb5_data extends Structure {

	public static class ByReference extends krb5_data implements Structure.ByReference { }
	public static class ByValue extends krb5_data implements Structure.ByValue { }
	
    public int magic;
    public int length;
    public Pointer data;  //this a string... or bytes???
    
    public byte[] getData() {
    	return data.getByteArray(0, length);
    }
    public String getDataAsString() {
    	return new String(getData());
    }
}
