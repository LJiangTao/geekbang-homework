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
<div class="container">
    <h1>用户注册</h1>
    <form action="/register" method="post">
        <table>
            <tr>
                <td>用户：<input name="username" type="text"></td>
            </tr>
            <tr>
                <td>密码：<input name="password" type="password"></td>
            </tr>
            <tr>
                <td>手机号：<input name="phone" type="number"></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</div>
</body>