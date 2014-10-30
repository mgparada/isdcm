<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>ISDCM</title>

        <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
        <link href="css/bootstrap.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/freelancer.css" rel="stylesheet">
        <link href="css/jquery.datetimepicker.css" rel="stylesheet">
        
        <!-- ***** SCRIPT SECTION ***** -->
        <!-- jQuery Version 1.11.0 -->
        <script src="js/jquery-1.11.0.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.datetimepicker.js"></script>

        <!-- Contact Form JavaScript -->
        <script src="js/jqBootstrapValidation.js"></script>
        <script src="js/register.js"></script>
        <script src="js/addVideo.js"></script>
        

        <!-- Custom Theme JavaScript -->
        <script src="js/freelancer.js"></script>

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
    </head>
    <body id="page-top" class="index">

        <jsp:include page="sectionPages/nav.jsp" />
        <jsp:include page="sectionPages/headerDash.jsp" />
        
        <jsp:include page="sectionPages/videoRegisterForm.jsp" />
        
        <jsp:include page="sectionPages/footer.jsp" />

        
        <!-- Plugin JavaScript -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
        <script src="js/classie.js"></script>
        <script src="js/cbpAnimatedHeader.js"></script>
        <script>
            $( '#submitLogin' ).on( 'click', function ( e ) {
                e.preventDefault();
                $( "#formLogin" ).submit();
            } );
        </script>
    </body>

</html>