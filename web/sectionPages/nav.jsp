<%-- 
    Document   : header
    Created on : 27-oct-2014, 15:52:07
    Author     : mauro
--%>

<%
    boolean isLoged = false;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                isLoged = true;
            }
        }
    }
%>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <a class="navbar-brand" href="#page-top">ISDCM</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="page-scroll">a</li>
                <% if (!isLoged) { %>
                    <li class="page-scroll">
                        <a href="#register">Registro</a>
                    </li>
                    <li class="page-scroll">
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                                Login
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation">
                                    <form id="formLogin" action="login.jsp" method="POST" class="dropdown-form" role="form">
                                        <label for="nicknameLogin">Nickname</label>
                                        <input type="text" class="form-control" id="nicknameLogin" name="nickname" placeholder="Nickname">
                                        <label for="passwordLogin">Password</label>
                                        <input type="password" class="form-control" id="passwordLogin" name="password" placeholder="Password">
                                        <input type="submit" id="submitLogin" class="btn btn-default" />
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </li>
                <% } else { %>
                    <li class="page-scroll">
                        <a href="logout">Logout</a>
                    </li>
                <% }%>
            </ul>
        </div>
    </div>
</nav>