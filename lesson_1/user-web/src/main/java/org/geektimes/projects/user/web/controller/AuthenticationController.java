
package org.geektimes.projects.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/register")
public class AuthenticationController implements PageController {

    private UserServiceImpl userService = new UserServiceImpl();

    private static final Logger log = Logger.getLogger("AuthenticationController");

    @POST
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        String user = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        log.log(Level.INFO, String.format("username: %s, password: %s", user, password));
        if (StringUtils.isBlank(user) && StringUtils.isBlank(password)) {
            return "register-form.jsp";
        }

        if (userService.register(new User(user, password, phone))) {
            return "login-form.jsp";
        }
        return "register-form.jsp";
    }

}
