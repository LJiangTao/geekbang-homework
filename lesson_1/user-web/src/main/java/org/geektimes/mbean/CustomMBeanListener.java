package org.geektimes.mbean;

import javax.management.*;
import javax.servlet.*;
import java.lang.management.ManagementFactory;
import java.util.Set;

public class CustomMBeanListener implements ServletContextListener {

    private void initModelBean() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();

            ObjectName objectName = new ObjectName("jolokia:type=CustomManager");
            CustomManager bean = new CustomManager(new CustomApplicationName());
            server.registerMBean(new StandardMBean(bean, ICustomManagerMBean.class), objectName);

        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initModelBean();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
