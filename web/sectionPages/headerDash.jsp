<%-- 
    Document   : header
    Created on : 27-oct-2014, 16:00:44
    Author     : mauro
--%>

<!-- Header -->
<header>
    <div class="container-dash">
        <div class="row">
            <div class="col-lg-12">
                <div class="col-lg-6" style="padding-top: 25px;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default all">View videos</button>
                        <button type="button" class="btn btn-default add">Add video</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<script>
    $(".all").on('click', function(e){
        e.preventDefault();
        window.location.href = "getAll"
    })
    $('.add').on('click', function(e) {
        e.preventDefault();
        window.location.href = "index.jsp";
    })
</script>
