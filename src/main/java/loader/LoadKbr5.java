package loader;

import java.sql.DriverManager;

import com.shawtonabbey.kerberos.kcm.KerberosCacheIntercept;


public class LoadKbr5 {
		
	public static void main(String[] args) throws Exception{

		System.out.println("----" + args.length + "---");
		for (var a : args) {
			System.out.println(a);
		}
		
		javax.security.auth.login.Configuration.setConfiguration(new CustomConfig());
		KerberosCacheIntercept.installKcm();
		
	
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
