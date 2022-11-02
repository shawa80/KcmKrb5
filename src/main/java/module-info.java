module KcmKrb5 {
	exports loader;
	exports com.shawtonabbey.krb5;

	requires transitive com.sun.jna;
	requires transitive java.security.jgss;
	requires net.bytebuddy;
	requires net.bytebuddy.agent;
	requires jdk.security.auth;
	requires java.sql;
}
