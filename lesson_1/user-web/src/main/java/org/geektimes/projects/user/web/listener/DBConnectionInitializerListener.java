package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
@Deprecated
public class DBConnectionInitializerListener implements ServletContextListener {

    private final Logger log = Logger.getLogger("DBConnectionInitializerListener");

    @Resource(name = "jdbc/user-platform", lookup = "java:comp/env",
            type = DataSource.class)
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/user-platform");

            Connection conn = ds.getConnection();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
