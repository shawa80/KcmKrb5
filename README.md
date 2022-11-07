# KcmKrb5


This project makes Java loaded the ticket granting ticket (TGT) from the KCM cache instead of the file cache when running under Linux.  Linux distributions have switched to using the KCM cache for its credential caching.  KCM caching uses a service that provides better control over renewal of the tickets in the cache.


The current Java Kerberos implementation only checks the filesystem cache for tickets.  This library uses JNA to load the native krb5 library (libkrb5) to read the ticket granting ticket from the KCM cache. It then uses ByteBuddy to inject itself into the Kerberos authentication process.  Where it intercepts calls to get the default ticket granting ticket.  On invocation, it will returns the credential from the KCM cache instead of the file cache.

## com.shawtonabbey.kerberos.kcm

JNA bindings to load kerberos credentials from libkrb5.so.

## com.shawtonabbey.kerberos

Utility to using cached credentials from the KCM cache instead of the filesytem cache in linux.

## Coding

### Start up arguments

The bytebuddy agent needs to get installed to allow injection of the code.  The java.security.jgss needs to be granted read rights to the KcmKrb5 mondel.

```
-javaagent:/home/andrew/.m2/repository/net/bytebuddy/byte-buddy-agent/1.12.18/byte-buddy-agent-1.12.18.jar
--add-reads java.security.jgss=KcmKrb5
```
### Code Start up

The kerberos auth manager needs to be configured.  This can be done using a file and a command line arg, or using a Configuration object.  The cache interception can be installed using installKcm(). 

```
javax.security.auth.login.Configuration.setConfiguration(new CustomConfig());
KerberosCacheIntercept.installKcm();
```

### Configuring Postgresql to use Kerberos Authentication.

The following will instruction the postgres jdbc driver to use Kerberos for authentication.

```
public class CustomConfig extends javax.security.auth.login.Configuration {

	private static final String AUTH_MODULE =
            "com.shawtonabbey.kerberos.KerberosLogin";
  
    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        if ("pgjdbc".equals(name)) {
        	
        	return new AppConfigurationEntry[] { 
        			new AppConfigurationEntry(
        					AUTH_MODULE,
        					AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, 
        					new HashMap<String, String>()
        			)
        	};
        }
        return new AppConfigurationEntry[0];
    }
	
}
```
