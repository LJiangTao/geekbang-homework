<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf"/>
    <title>My Home Page</title>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>
<body>
<%
    String path = request.getContextPath();

    request.setCharacterEncoding("utf-8");

    String username = request.getParameter("inputEmail");
    String pwd = request.getParameter("inputPassword");
    String[] hobby = request.getParameterValues("hobby");
    String sex = request.getParameter("sex");
    String home = request.getParameter("home");
    System.out.println(username + ", " + pwd);
    if("admin".equals(username) && "000".equals(pwd)){
        session.setAttribute("username", username);
        session.setAttribute("hobby", hobby);
        session.setAttribute("home", home);
        session.setAttribute("sex", sex);
        request.getRequestDispatcher("show.jsp").forward(request, response);
    }
%>
<div class="container">
    <form class="form-signin" action="login-form.jsp">
        <h1 class="h3 mb-3 font-weight-normal">登录</h1>

        <label for="inputEmail" class="sr-only">请输出电子邮件</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="请输入电子邮件" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="请输入密码" required>

        <div class="checkbox mb-3">
            <label> <input type="checkbox" value="remember-me">
                Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign
            in
        </button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
    </form>
</div>
</body>