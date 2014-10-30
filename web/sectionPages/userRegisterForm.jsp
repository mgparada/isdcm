<%-- 
    Document   : registerForm
    Created on : 27-oct-2014, 15:50:06
    Author     : mauro
--%>

<!-- Contact Section -->
<section id="register">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Register</h2>
                <hr class="star-primary">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <h4 class="error"></h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <form id="contactForm" novalidate>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='name'>Name</label>
                            <input type="text" maxlength="50" class="form-control" name='name' 
                                   placeholder="Name" id="name" required 
                                   data-validation-required-message="Please enter your name.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='surname'>Surname</label>
                            <input type="text" maxlength="50" class="form-control" name='surname' 
                                   placeholder="Surname" id="surname" required 
                                   data-validation-required-message="Please enter your surname.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='email'>Email</label>
                            <input type="email" maxlength="255" class="form-control" name='email'
                                   placeholder="Email Address" id="email" required 
                                   data-validation-required-message="Please enter your email address.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='nickname'>Nickname</label>
                            <input type="text" maxlength="255" class="form-control" name='nickname' 
                                   placeholder="Nickname" id="nickname" required 
                                   data-validation-required-message="Please enter your nickname.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='password'>Password</label>
                            <input type="password" maxlength="255" class="form-control" name='password' 
                                   placeholder="****" id="password" required 
                                   data-validation-required-message="Please enter your password.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='confirmPass'>Password</label>
                            <input type="password" maxlength="255" class="form-control" name='confirmPass' 
                                   placeholder="****" id="confirmPass" required 
                                   data-validation-matches-match="password"
                                   data-validation-matches-message="Re-enter password incorrect."
                                   data-validation-required-message="Please re-enter your password.">
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