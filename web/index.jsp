<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<!--<html>
    <head>
	<script language="javascript" type="text/javascript" src="js/jquery.min.js" ></script>        
	<link rel="stylesheet" type="text/css" href="css/index.css" />	
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Registro de usuarios</title>
    </head>
    <body>
        <div class="container">
	    <div class="row clearfix">
		<div class="col-md-12 column">
		    <p>asfsa</p>
		</div>
	    </div>
	    <div class="row clearfix">
		<div class="col-md-12 column">
		    <h3>
			Formulario de registro
		    </h3>
		    <form role="form">
			<div class="form-group">
			    <label for='name'> Nombre </label>
			    <input type='text' maxlength='50' required='required' name='name' placeholder='Mauro'><br />
			</div>
			<div class="form-group">
			    <label for='surname'> Apellido </label>
			    <input type='text' maxlength='50' required='required' name='surname' placeholder='Gómez'><br />
			</div>
			<div class="form-group">
			    <label for='email'> Email </label>
			    <input type='email' maxlength='255' required='required' name='email' 
				   placeholder='maurogomez@gmail.com'><br />
			</div>
			<div class="form-group">
			    <label for='nickname'> Nombre de usuario </label>
			    <input type='text' maxlength='50' required='required' name='nickname' placeholder='mauro.gomez'><br />
			</div>
			<div class="form-group">
			    <label for='password'> Contraseña </label>
			    <input type='password' maxlength='50' required='required' name='password' placeholder='****'>
			</div>
			<div class="form-group">
			    <label for='confirmPass'> Repita la contraseña </label>
			    <input type='password' maxlength='50' required='required' name='confirmPass' placeholder='****'>
			</div>
			<button type="submit" class="btn btn-default">Guardar</button>
		    </form>
		</div>
	    </div>
	</div>
	<script language="javascript" type="text/javascript">
            $( 'form' ).submit( function ( e ) {
                var pass = $( 'input[name="password"' ).val();
                var passConfirm = $( 'input[name="confirmPass"' ).val();

                if ( pass !== passConfirm ) {
                    $( 'input[name="confirmPass"' ).after( '<span id="error-pass">Las contraseñas no coinciden</span>' );
                    e.preventDefault();
                }
            } );
	</script>
    </body>
</html>-->

<!DOCTYPE html>
<html lang="en">

    <head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Freelancer - Start Bootstrap Theme</title>

	<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
	<link href="css/bootstrap.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="css/freelancer.css" rel="stylesheet">
       
        <%
            boolean isLoged = false;
            Cookie[] cookies = request.getCookies();
            if( cookies != null ) {
                for( Cookie cookie : cookies ) {
                    if( cookie.getName().equals("token") )
                        isLoged = true;
                }
            }
        %>
        
    </head>

    <body id="page-top" class="index">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
	    <div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
		    <a class="navbar-brand" href="#page-top">Start Bootstrap</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    <ul class="nav navbar-nav navbar-right">
			<li class="page-scroll">a</li>
                        <% if(!isLoged) { %>
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
                                                <!--<div class="form-group">-->
                                                    <label for="nicknameLogin">Nickname</label>
                                                    <input type="text" class="form-control" id="nicknameLogin" name="nickname" placeholder="Nickname">
                                                <!--</div>-->
                                                <!--<div class="form-group">-->
                                                    <label for="passwordLogin">Password</label>
                                                    <input type="password" class="form-control" id="passwordLogin" name="password" placeholder="Password">
                                                <!--</div>-->
                                                <input type="submit" id="submitLogin" class="btn btn-default" />
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        <% }else{ %>
                            <li class="page-scroll">
                                <a href="/logout">Logout</a>
                            </li>
                        <% } %>
		    </ul>
		</div>
	    </div>
	</nav>

	<!-- Header -->
	<header>
	    <div class="container">
		<div class="row">
		    <div class="col-lg-12">
			<div class="intro-text">
			    <span class="name">Start Bootstrap</span>
			    <hr class="star-light">
			    <span class="skills">Web Developer - Graphic Artist - User Experience Designer</span>
			</div>
		    </div>
		</div>
	    </div>
	</header>

	<!-- Contact Section -->
	<section id="register">
	    <div class="container">
		<div class="row">
		    <div class="col-lg-12 text-center">
			<h2>Contact Me</h2>
			<hr class="star-primary">
		    </div>
		</div>
		<div class="row">
		    <div class="col-lg-8 col-lg-offset-2">
			<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
			<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
			<form name="sentMessage" id="contactForm" novalidate>
			    <div class="row control-group">
				<div class="form-group col-xs-12 floating-label-form-group controls">
				    <label>Name</label>
				    <input type="text" class="form-control" placeholder="Name" id="name" required data-validation-required-message="Please enter your name.">
				    <p class="help-block text-danger"></p>
				</div>
			    </div>
			    <div class="row control-group">
				<div class="form-group col-xs-12 floating-label-form-group controls">
				    <label>Email Address</label>
				    <input type="email" class="form-control" placeholder="Email Address" id="email" required data-validation-required-message="Please enter your email address.">
				    <p class="help-block text-danger"></p>
				</div>
			    </div>
			    <div class="row control-group">
				<div class="form-group col-xs-12 floating-label-form-group controls">
				    <label>Phone Number</label>
				    <input type="tel" class="form-control" placeholder="Phone Number" id="phone" required data-validation-required-message="Please enter your phone number.">
				    <p class="help-block text-danger"></p>
				</div>
			    </div>
			    <div class="row control-group">
				<div class="form-group col-xs-12 floating-label-form-group controls">
				    <label>Message</label>
				    <textarea rows="5" class="form-control" placeholder="Message" id="message" required data-validation-required-message="Please enter a message."></textarea>
				    <p class="help-block text-danger"></p>
				</div>
			    </div>
			    <br>
			    <div id="success"></div>
			    <div class="row">
				<div class="form-group col-xs-12">
				    <button type="submit" class="btn btn-success btn-lg">Send</button>
				</div>
			    </div>
			</form>
		    </div>
		</div>
	    </div>
	</section>

	<!-- Footer -->
	<footer class="text-center">
	    <div class="footer-above">
		<div class="container">
		    <div class="row">
			<div class="footer-col col-md-4">
			</div>
		    </div>
		</div>
	    </div>
	    <div class="footer-below">
		<div class="container">
		    <div class="row">
			<div class="col-lg-12">
			</div>
		    </div>
		</div>
	    </div>
	</footer>

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-top page-scroll visible-xs visble-sm">
	    <a class="btn btn-primary" href="#page-top">
		<i class="fa fa-chevron-up"></i>
	    </a>
	</div>

	<!-- jQuery Version 1.11.0 -->
	<script src="js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<!--<script src="js/contact_me.js"></script>-->

	<!-- Custom Theme JavaScript -->
	<script src="js/freelancer.js"></script>

	<script>
	    $('#submitLogin').on('click', function(e) {
		$('#contactForm').remove();
//		$.post(
//		    'login',
//		    $(this).serialize()
//		);
		$("#formLogin").submit();
	    });
	</script>
    </body>

</html>