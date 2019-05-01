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
});