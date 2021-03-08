
package org.geektimes.projects.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/login")
public class LoginController implements PageController {

    private static final UserServiceImpl USER_SERVICE_IMPL = new UserServiceImpl();

    private static final Logger log = Logger.getLogger("AuthenticationController");

    @POST
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            return "login-form.jsp";
        }
        User user = USER_SERVICE_IMPL.queryUserByNameAndPassword(username, password);
        if (!Objects.isNull(user)) {
            return "index.jsp";
        }
        return "login-form.jsp";
    }

}
