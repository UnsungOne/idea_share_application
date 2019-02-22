$(document).ready(function () {

//    var id = $('#ideaId').val();
//    var json = {"id=": id};

      $('body').on('click', '#rateUp', function(event){
      console.log('click');
         event.preventDefault();
       var form = $(this).closest("form")
       var id = form.find("#ideaId").val()
       var json = {"id=": id};


            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/idea/rateUp/' + id,
                data: json,
                dataType: 'json',
            })

                .done(function (idea) {
                    var respContent = "";
                    respContent += "<p class='success'>";
                    respContent += idea.score + "</p>";
                    $("#ideaScore").html(respContent);

                })

                .fail(function () {
                    $('.form-message').html("<div class=\"alert alert-danger fade in\">\n" +
                        "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n" +
                        "    <strong>Oops!</strong> Musisz się zalogować, aby móc głosować.\n" +
                        "</div>");

                });

    });

    $('#rateDown').on('click', function () {

        $('#ideaForm').submit(function (event) {
            event.preventDefault();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/idea/rateDown/' + id,
                data: JSON.stringify(json),
                dataType: 'json',
            })

                .done(function (idea) {
                    var respContent = "";
                    respContent += "<p class='success'>";
                    respContent += idea.score + "</p>";
                    $("#ideaScore").html(respContent);

                })

                .fail(function () {
                    $('.form-message').html("<div class=\"alert alert-danger fade in\">\n" +
                        "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n" +
                        "    <strong>Oops!</strong> Musisz się zalogować, aby móc głosować.\n" +
                        "</div>");

                });

        });

    });

});