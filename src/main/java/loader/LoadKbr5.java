package loader;

import java.sql.DriverManager;


public class LoadKbr5 {
		
	public static void main(String[] args) throws Exception{

		javax.security.auth.login.Configuration.setConfiguration(new CustomConfig());
		IntercepterInstaller.install();
		
		//var bbTest = Credentials.acquireDefaultCreds();
		
		//System.out.println(bbTest);
		
		var con = DriverManager.getConnection("jdbc:postgresql://pi3.shawtonabbey.com/addressbook");

		var sqlStr = "SELECT datname FROM pg_database " +
		"WHERE datistemplate = false;";
		
		var stm = con.prepareStatement(sqlStr);
		
		stm.execute();
		var result = stm.getResultSet();
		
		while(result.next()) {
			System.out.println(result.getString(1));
		}
		
	}
		
}
