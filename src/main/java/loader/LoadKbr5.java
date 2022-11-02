package loader;

import java.sql.DriverManager;

import sun.security.krb5.*;


public class LoadKbr5 {
		
	public static void main(String[] args) throws Exception{

		javax.security.auth.login.Configuration.setConfiguration(new CustomConfig());
		IntercepterInstaller.install();
		
		//var bbTest = Credentials.acquireDefaultCreds();
		
		//System.out.println(bbTest);
		
		var con = DriverManager.getConnection("jdbc:postgresql://pi3.shawtonabbey.com/addressbook");
    }
		
}
