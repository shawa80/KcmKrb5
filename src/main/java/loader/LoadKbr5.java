package loader;

import sun.security.krb5.*;


public class LoadKbr5 {
		
	public static void main(String[] args) throws Exception{

		IntercepterInstaller.install();
		
		var bbTest = Credentials.acquireDefaultCreds();
		
		System.out.println(bbTest);
    }
		
}
