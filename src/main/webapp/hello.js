$(function() { // Sikrer sig at dokumentet er indlæst,
    // før click handlers håndteres
    $('#formid').submit(function() { //Sætter en click handler på knappen
        //event.preventDefault();
        //var datajson = $('form#formid').serializeJSON();
        //var obj = {id: $('#id').val(), name: $('#name').val(), amount: $('#amount').val()};
        //var datajson = JSON.stringify(obj);
        var datajson = JSON.stringify($('form#formid').serializeArray());
        //var datajson = $('#formid').serializeArray();
        $.ajax({				 //Indleder et asynkront ajax kald
            url : 'rest/hello/postclass',	 //specificerer endpointet
            type : 'POST', //Typen af HTTP requestet (GET er default)
            //dataType : 'json', //MAYBE!?!?
            //data : "Hello",
            //contentType : 'text/plain',
            data : datajson,
            contentType : 'application/json',
            success : function(data){//Funktion der skal udføres når data er hentet
                //alert("succes" + data);
                $('#mydiv').html("success " + data); //Manipulerer #mydiv.
            },
            error : function(data){
                //alert("error" + data);
                $('#mydiv').html("error " + data);
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    });

    $('#hellobutton').click(function() { //Sætter en click handler på knappen
        $.ajax({				 //Indleder et asynkront ajax kald
            url : 'rest/hello/normal',	 //specificerer endpointet
            type : 'POST',	       //Typen af HTTP requestet (GET er default)
            data : $('#testfield').val(),
            contentType : 'text/plain',
            success : function(data){//Funktion der skal udføres når data er hentet
                $('#mydiv').html(data); //Manipulerer #mydiv.
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    });

    $('#testbtn').click(function() { //Sætter en click handler på knappen
        var testjson = '{"id" : "tre", "name" : "etnavn", "amount" : "mange"}';
        $.ajax({				 //Indleder et asynkront ajax kald
            url : 'rest/hello/postclass',	 //specificerer endpointet
            type : 'POST',	       //Typen af HTTP requestet (GET er default)
            data : testjson,
            contentType : 'application/json',
            dataType : 'text',
            success : function(data){//Funktion der skal udføres når data er hentet
                $('#mydiv').html("Stor success"); //Manipulerer #mydiv.
            },
            error : function (data) {
                $('#mydiv').html("Stor fejl");
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    });

    $('#getUser').click(function() { //Sætter en click handler på knappen
        $.ajax({				 //Indleder et asynkront ajax kald
            url : 'rest/hello/getjson',	 //specificerer endpointet
            type : 'GET',	       //Typen af HTTP requestet (GET er default)
            dataType : 'json',
            success : function(data){//Funktion der skal udføres når data er hentet
                $('#usertext').html(data.name); //Manipulerer #mydiv.
            },
            error : function(data){
                alert("An unexpected error has occured");
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    });
});