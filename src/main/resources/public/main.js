$(document).ready(function () {
        var questionAnswer;

        getQuestionAndAnswer();

        function getAnswer() {
            $.post("/games/fizzBuzz/getAnswer",
                {
                    'question': $("#playInput").val()
                }
            ).done(function (data) {
                $("#result").html(data);
            });
        }

        function getQuestionAndAnswer() {
            $.get("/games/fizzBuzz/getQuestionAndAnswer?bound=" + $('#boundVal').val()
            ).done(function (data) {
                console.log(data);
                questionAnswer = data;
                var question = data.question;
                var answer = data.anwer;
                $("#question").html("Play fizzBuzz with number: " + question);
            });
        }

        $('#playBtn').click(function () {
            getAnswer();
        });

        $('#randomRound').click(function () {
            getQuestionAndAnswer();
        });

        $('.answerBtn').click(function () {
            var vote = $(this).attr("value");
            console.log("answer: " + questionAnswer.answer);
            console.log("vote: " + vote);
            if (vote == questionAnswer.answer || vote == "sameNumber" && questionAnswer.question == questionAnswer.answer) {
                getQuestionAndAnswer();
                console.log("correct!");
            }
        })
    }
);