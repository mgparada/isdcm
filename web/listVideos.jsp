<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Video"%>
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

        <section id="listVideos">
            <div class="container">
                <div class="col-lg-12">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-8">
                        <%
                            LinkedList<Video> videos = (LinkedList) request.getAttribute("videos");
                            for (Video video : videos) { %>
                        <div class="row video collapse-group">
                            <% String image = "http://www.elomag.com/wp-content/uploads/2011/03/pourquoi-dire-non.jpg";
                                if(video.getImageUrl() != null)
                                    image = video.getImageUrl().toString();
                            %>
                            <img src="<% out.print(image); %>" 
                                 style="float: left; width: 25%;" />
                            <div style="margin-left: 27%">
                                <p style="font-size: 2em">
                                    <% out.print(video.getTitle()); %>
                                </p>
                                <span style="font-size: 1.5em;">(<% out.print(video.getAuthor()); %>)</span>
                            </div>
                            <div style="margin-left: 27%">
                                <p style="word-wrap: break-word;">Language: <% out.print( video.getLanguage() ); %></p>
                                <p style="word-wrap: break-word;">Duration: <% out.print( video.getDuration()); %>H</p>
                            </div>
                            <div class="collapse" style="margin-left: 27%">
                                <p style="word-wrap: break-word; text-overflow: ellipsis;"><% out.print(video.getDescription()); %></p>
                                <p style="word-wrap: break-word;">URL: 
                                    <a href="<% out.print(video.getVideoUrl()); %>" target="_blank" class="url">
                                        <% out.print(video.getVideoUrl()); %>
                                    </a>
                                </p>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 15px;"></div>
                        <% }%>
                    </div>
                    <div class="col-lg-2"></div>
                </div>
            </div>
        </section>

        <jsp:include page="sectionPages/footer.jsp" />


        <!-- Plugin JavaScript -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
        <script src="js/classie.js"></script>
        <script src="js/cbpAnimatedHeader.js"></script>
        <script>
            $('.url')
                    .on('click', function(e){
                        window.open($(this).attr('href'), '_blank');
                    })
            $( '.video' )
                    .mouseenter( function () {
                        $( this ).css( 'background', '#F0F0F0' );
                        $( this ).css( 'cursor', 'pointer' );
                    } )
                    .mouseleave( function () {
                        $( this ).css( 'background', 'white' );
                    } )
                    .click( function ( e ) {
                        e.preventDefault();
                        var $this = $( this );
                        var $collapse = $this.closest( '.collapse-group' ).find( '.collapse' );
                        $collapse.collapse( 'toggle' );
                    } );
        </script>
    </body>

</html>