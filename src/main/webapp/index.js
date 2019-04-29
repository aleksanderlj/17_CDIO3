$(document).ready(function () {
    $('#admin').hide();
    $('#opretBruger').hide();
})

function login() {
    var user = $("#loginUser").val();
    var password = $("#loginPass").val();

        show('#admin');
        hide('#login');
}
function show(element) {
    $(element).show();
}

function hide(element) {
    $(element).hide();
}
