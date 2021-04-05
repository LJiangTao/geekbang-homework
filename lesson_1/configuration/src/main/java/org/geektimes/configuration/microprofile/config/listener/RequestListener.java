package org.geektimes.configuration.microprofile.config.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

public class RequestListener implements ServletRequestListener {

    private static final ThreadLocal<Config> CONFIG_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        ServletContext servletContext = request.getServletContext();
        ClassLoader classLoader = servletContext.getClassLoader();
        ConfigProviderResolver configProviderResolver = ConfigProviderResolver.instance();
        Config config = configProviderResolver.getConfig(classLoader);
        CONFIG_THREAD_LOCAL.set(config);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        CONFIG_THREAD_LOCAL.remove();
    }
}
