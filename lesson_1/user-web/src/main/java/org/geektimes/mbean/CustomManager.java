package org.geektimes.mbean;

public class CustomManager implements ICustomManagerMBean{

    private final CustomApplicationName applicationName;

    @Override
    public String getApplicationName() {
        return applicationName.getApplicationName();
    }

    @Override
    public void setApplicationName(String applicationName) {
        this.applicationName.setApplicationName(applicationName);
    }

    @Override
    public String toString() {
        return String.format("application name is [ %s ]", applicationName.getApplicationName());
    }

    public CustomManager(CustomApplicationName applicationName){
        this.applicationName = applicationName;
    }
}
