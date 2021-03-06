/**
 * Created by Trach on 12/23/2016.
 */
var sendProblemTo;

$(document).ready(function () {
    var userProblem = $("#userProblem").text();
    switch(userProblem) {
        case 'ForgotPassword' :
            sendProblemTo = "/api/users/sendResetPasswordToken";
            break;
        case 'ResendRegistrationToken' :
            sendProblemTo = "/api/users/resendRegistrationToken";
            break;
    }

    $('#emailField-send-button').click(sendEmail);
});

function sendEmail() {
    $('#emailField-send-button').button('loading');
    $.ajax({
        type: 'GET',
        url: sendProblemTo,
        data: "email=" + $("#emailField").val()
    }).done(function (data) {
        $('#emailField-send-button').button('reset');
        if (data.message == "success") {
            $("#emailField-send-request-server-status").text("Перевірте пошту.");
        }
    }).fail(function (data) {
        $('#emailField-send-button').button('reset');
        $("#emailField-send-request-server-status").text(data.responseJSON.message);
    });
};