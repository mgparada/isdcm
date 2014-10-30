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
                <h2>Adding Video</h2>
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
                <form id="videoRegisterForm" novalidate>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='title'>Title</label>
                            <input type="text" maxlength="100" class="form-control" name='title' 
                                   placeholder="Title" id="title" required 
                                   data-validation-required-message="Please enter the title." />
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='image'>Image</label>
                            <input type="url" class="form-control" name='image' 
                                   placeholder="http://example.com/image.jpg" id="image" required 
                                   data-validation-required-message="Please enter the image url." />
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='author'>Author</label>
                            <input type="text" maxlength="100" class="form-control" name='author' 
                                   placeholder="Author" id="author" required 
                                   data-validation-required-message="Please enter the author." />
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='creationDate'>Creation Date</label>
                            <input class="form-control" name='creationDate'
                                   placeholder="01/01/2000" id="creationDate" required 
                                   data-validation-required-message="Please enter the video creation date." />
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='duration'>Duration</label>
                            <input type="text" maxlength="255" class="form-control" name='duration' 
                                   placeholder="00:00:00" id="duration" required 
                                   pattern="^[0-9]{2}:[0-9]{2}:[0-9]{2}$"
                                   data-validation-required-message="Please enter the video duration." />
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='description'>Description</label>
                            <textarea rows="5" cols="5" class="form-control" name='description' 
                                      placeholder="Description" id="description" required 
                                      data-validation-required-message="Please enter your the description.">

                            </textarea>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='language'>Language</label>
                            <select class="form-control" name='language' id="language" required >
                                <option value="english">English</option>
                                <option value="spanish">Spanish</option>
                                <option value="french">French</option>
                                <option value="german">German</option>
                            </select>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='format'>Format</label>
                            <select class="form-control" name='format' id="format" required >
                                <option value="video/avi">video/avi</option>
                                <option value="video/mpeg">video/mpeg</option>
                                <option value="video/mp4">video/mp4</option>
                                <option value="video/ogg">video/ogg</option>
                            </select>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label for='url'>Url</label>
                            <input type="url" maxlength="255" class="form-control" name='url' 
                                   placeholder="http://example.com/video.ogg" id="url" required 
                                   data-validation-required-message="Please enter the video url." />
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
<script>
    $('#creationDate').datetimepicker( {
        timepicker:false,
        format: 'd/m/Y'
    });
    
    $('#duration').datetimepicker( {
        datepicker: false,
        format: 'h:m:s'
    });
</script>