package org.geektimes.projects.user.web.services;

import org.geektimes.mbean.CustomMBeanListener;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

public class ServletInitialization implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ctx.addListener(CustomMBeanListener.class);
    }
}
