package loader;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import sun.security.krb5.Credentials;

public class IntercepterInstaller {

	public static void install() {
	
		new ByteBuddy()
		  .redefine(Credentials.class)
		  //.method(ElementMatchers.named("acquireDefaultCreds"))
		  .method(ElementMatchers.named("acquireTGTFromCache"))
		  .intercept(MethodDelegation.to(CreateCredIntercepter.class))
		  .make()
		  .load(
				  Credentials.class.getClassLoader(), 
				  ClassReloadingStrategy.fromInstalledAgent()
		    );
	}
}
