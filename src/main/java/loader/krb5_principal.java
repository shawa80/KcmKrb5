package loader;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "magic", "realm", "data", "length", "type"})
public class krb5_principal extends Structure {

	public static class ByReference extends krb5_principal implements Structure.ByReference { }
	
    public int magic;
    public krb5_data.ByValue realm;
    public krb5_data.ByReference data;            //An array of datas,len defined by length
    public int length;
    public int type;
    
    public krb5_data[] getData() {

        return (krb5_data[])data.toArray(length);
    }
}
