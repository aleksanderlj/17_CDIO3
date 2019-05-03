$(function() {

    $('#myform').submit(function() {
        //event.preventDefault();
        //var datajson = $('form#formid').serializeJSON();
        //var obj = {id: $('#id').val(), name: $('#name').val(), amount: $('#amount').val()};
        //var datajson = JSON.stringify(obj);
        var datajson = JSON.stringify($('form#formid').serializeArray());
        //var datajson = $('#formid').serializeArray();
        $.ajax({
            url : 'rest/useradmin/create',
            type : 'POST',
            //dataType : 'json', //MAYBE!?!?
            //data : "Hello",
            //contentType : 'text/plain',
            data : datajson,
            contentType : 'application/json',
            success : function(data){
                //alert("succes" + data);
                //$('#mydiv').html("success " + data);
                //SKRIV NOGET MED "User created"
                //UPDATE HTML LISTEN AF BRUGERE
            },
            error : function(data){
                alert("An unexpected error occured");
            }
        });
        return false;
    });
});

// TODO skal være i success delen af et ajax 'GET' kald
function addRow(data) {

    // TODO Roll me daddy
    var roles = makeRoles();

    var table = document.getElementById("myTableData");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
    row.insertCell(1).innerHTML= data.id;
    row.insertCell(2).innerHTML= data.username;
    row.insertCell(3).innerHTML= data.initials;
    row.insertCell(4).innerHTML= roles; //TODO Rolltown USA


}

// TODO metode med for-loop som kører addRow() for alle brugere i databasen.
// TODO Kald for-loop addRow metoden når siden loades + når en bruger slettes eller tilføjes.

// TODO Delete user metode med kald til database

// TODO Create user metode med kald til database

// TODO Update user methode med kald til database