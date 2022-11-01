module KcmKrb5 {
	exports loader;
	exports com.shawtonabbey.krb5;

	requires com.sun.jna;
	requires java.security.jgss;
	requires net.bytebuddy;
	requires net.bytebuddy.agent;
	requires jdk.security.auth;
}
