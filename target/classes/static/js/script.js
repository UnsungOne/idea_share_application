$(document).ready(function () {

    $('#ideaUp').submit(function(event) {
        var id = $('#ideaId').val();
        var score = $('#ideaScore').val();

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url :'/idea/rateUp/'+ id,
            data :{ id },
            success: function(idea) {
                $.each(idea, function (index, value) {
                    $('#sPhoneFromResponse').empty().append("<p>" + this.id+ "</p>");

                });
                 console.log('data sent');
            }
        });

        event.preventDefault();
    });


    $('#ideaDown').submit(function(event) {

        var score = $('#ideaScore').val();
        var json = { "score" : score};

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/idea/rateDown/id",
            data : JSON.stringify(json),
            dataType : 'json',

            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },

            success: function(idea) {
                var respContent = "";
                respContent += "<p class='success'>";
                respContent += idea.score + "</p>";

                $("#sPhoneFromResponse").html(respContent);
            }
        });

        event.preventDefault();
    });
});