$(document).ready(function () {

    $('#ideaUp').submit(function(event) {
        var id = $('#ideaId').val();
        var score = $('#ideaScore').val();
        var json = { "score" : score};

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/idea/1/rateUp",
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


    $('#ideaDown').submit(function(event) {

        var score = $('#ideaScore').val();
        var json = { "score" : score};

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/idea/1/rateDown",
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