$(function() {

    $("input").jqBootstrapValidation({
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
                error: function(data) {
                    $('.error').text("These nickname already exists.");
                    $('html,body').animate({
                        scrollTop: $("#register").offset().top},
                        'slow'
                    );
                }
            })
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
