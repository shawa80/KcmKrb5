module KcmKrb5 {
	exports com.shawtonabbey.krb5;
	exports com.shawtonabbey.kerberos;
	exports com.shawtonabbey.kerberos.kcm;

	requires transitive com.sun.jna;
	requires transitive java.security.jgss;
	requires net.bytebuddy;
	requires net.bytebuddy.agent;
	requires jdk.security.auth;
	requires java.sql;
}
