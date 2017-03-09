/**
 * Created by Andreas on 08.03.2017.
 */


$(document).ready(function(){
    var url = '/quizChart';
    $("#quizChart").load(url);

    setInterval(function () {
        var url = '/quizChart';
        $("#quizChart").load(url)


    }, 2000);
});