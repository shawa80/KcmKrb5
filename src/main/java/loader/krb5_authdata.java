package loader;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "magic", "ad_type", "length", "contents"})
public class krb5_authdata extends Structure {
    
	public static class ByReference extends krb5_authdata implements Structure.ByReference { }
	
	public int magic;
    public int ad_type;
    public int length;       //TODO unsized int
    public Pointer contents;
    
    public byte[] getContents() {
    	
    	return contents.getByteArray(0, length);
    }
}
