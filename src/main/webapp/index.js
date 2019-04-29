$(document).ready(function () {
    $('#admin').hide();
    $('#opretBruger').hide();
})

function login() {
    var user = $("#loginUser").val();
    var password = $("#loginPass").val();


    if (user === "Søren" && password === "1234"){
        show('#admin');
        hide('#login');
    }
    else{
        alert("NEJ")
    }
}
function show(element) {
    $(element).show();
}

function hide(element) {
    $(element).hide();
}
