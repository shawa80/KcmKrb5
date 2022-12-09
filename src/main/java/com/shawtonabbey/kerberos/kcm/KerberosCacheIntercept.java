package com.shawtonabbey.kerberos.kcm;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import sun.security.krb5.Credentials;

public class KerberosCacheIntercept {

	public static void installKcm() {
	
		ByteBuddyAgent.install();
		
		new ByteBuddy()
		  .redefine(Credentials.class)
		  .method(ElementMatchers.named("acquireTGTFromCache"))
		  .intercept(MethodDelegation.to(AcquireTgtIntercepter.class))
		  .make()
		  .load(
				  Credentials.class.getClassLoader(), 
				  ClassReloadingStrategy.fromInstalledAgent()
		    );
	}
}
