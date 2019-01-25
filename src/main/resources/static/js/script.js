$(document).ready(function () {
    $('.table .eBtn').on('click', function(e) {
        e.preventDefault();

        var link = $(this).attr('href');
        $.get(link, function (idea, status) {
            $('.ideaEditForm #title').val(idea.title);
            $('.ideaEditForm #description').val(idea.description);
        });

        $('.ideaEditForm #idea').modal();

    });

});














//
//
//
// $('#ideaUp').on('submit', function(e) {
//   e.preventDefault();
//     var $dataElements = $('#ideaId').find('td').val(),
//     data = [];
//
//  $.each($dataElements, function(i, elem){
//         data.push($(elem).html());
//     });
//
//         $.ajax({
//             type : "POST",
//             contentType : "application/json",
//             url :'/idea/rateUp/'+ data,
//             data :{ data },
//
//            success: function(idea) {
//                  var respContent = "";
//                  respContent += "<p class='success'>";
//                  respContent += idea.score + "</p>";
//                    console.log("data " + id);
//
//                  $("#sPhoneFromResponse").html(respContent);
//              }
//          });
//
//         event.preventDefault();
//     });

//
//    $('#ideaDown').submit(function(event) {
//
//        var score = $('#ideaScore').val();
//        var json = { "score" : score};
//
//        $.ajax({
//            type : "POST",
//            contentType : "application/json",
//            url : "/idea/rateDown/id",
//            data : JSON.stringify(json),
//            dataType : 'json',
//
//            beforeSend: function(xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//                xhr.setRequestHeader("Content-Type", "application/json");
//            },
//
//            success: function(idea) {
//                var respContent = "";
//                respContent += "<p class='success'>";
//                respContent += idea.score + "</p>";
//
//                $("#sPhoneFromResponse").html(respContent);
//            }
//        });
//
//        event.preventDefault();
//    });
