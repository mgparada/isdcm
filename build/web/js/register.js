$(function() {

    $("#contactForm").find("input, textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            event.preventDefault(); // prevent default submit behaviour
            
            $.ajax({
                url: "registerUser",
                type: "POST",
                data: $form.serialize(),
                success: function(data) {
                    window.location.href = "index.jsp";
                },
                error: function( jqXHR, textStatus, errorThrown) {
                    if( jqXHR.status === 403 )
                        $('.error').text("These nickname already exists.");
                    else
                        $('.error').text("An internal error has occurred. Try again.");
                    
                    $('html,body').animate({
                        scrollTop: $("#register").offset().top},
                        'slow'
                    );
                }
            });
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});


/*When clicking on Full hide fail/success boxes */
$('#name').focus(function() {
    $('#success').html('');
});
