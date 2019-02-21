$(document).ready(function () {
    $('.table .edit').on('click', function(event) {
        event.preventDefault();

        var link = $(this).attr('href');
        $.get(link, function (idea) {
            $('.myForm #id').val(idea.id);
            $('.myForm #title').val(idea.title);
            $('.myForm #description').val(idea.description);
        });

        $('.myForm #idea').modal();

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
