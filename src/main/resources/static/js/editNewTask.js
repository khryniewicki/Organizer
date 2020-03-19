let addCard = document.getElementById('addCard');

$('#myModal'),$('#myEdit').modal({
    keyboard: false
});

function passHref(text) {

    $.get({
        url: "/dashboardview/" + text,
        success: function (data) {
            window.location.href = "/dashboard?name=" + text;
        }
    });
}

// $('#myModal'),$('#myEdit').modal('toggle');
// $('#myModal').modal('show');
// $('#myModal'),$('#myEdit').modal('hide');
