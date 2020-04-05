$('#myEdit').modal({
    keyboard: false
});

jQuery(document).ready(function(e) {
    $(".clsmodal").on("click", setTimeout(function() {

        $('#myEdit').modal('show');

    },1000))
});
