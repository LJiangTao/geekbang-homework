package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class DBConnectionInitializerListener implements ServletContextListener {

    private final Logger log = Logger.getLogger("DBConnectionInitializerListener");

    @Resource(name = "jdbc/UserPlatformDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBConnectionManager instance = DBConnectionManager.getInstance();
        try {
            instance.setConnection(dataSource.getConnection());
            System.out.println("NDI datasource getConnection success !");
            log.log(Level.FINE, "JNDI datasource getConnection success !");
        } catch (SQLException throwables) {
            log.log(Level.SEVERE, "JNDI datasource getConnection failure !");
            throwables.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionManager instance = DBConnectionManager.getInstance();
        instance.releaseConnection();
    }
}
