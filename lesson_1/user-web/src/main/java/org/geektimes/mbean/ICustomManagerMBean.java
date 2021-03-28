package org.geektimes.mbean;

import javax.management.DynamicMBean;

public interface ICustomManagerMBean {

    String getApplicationName();

    void setApplicationName(String applicationName);

}
