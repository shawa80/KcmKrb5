module KcmKrb5 {
	exports loader;

	requires com.sun.jna;
	requires java.security.jgss;
	requires net.bytebuddy;
	requires net.bytebuddy.agent;
	requires jdk.security.auth;
}
