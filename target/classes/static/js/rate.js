$(document).ready(function () {

    var id = $('#ideaId').val();
    var json = {"id=": id};

      $('#rateUp').off('click').on('click', function(){

        $('#ideaForm').submit(function (event) {


            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/idea/rateUp/' + id,
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
 event.preventDefault();
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