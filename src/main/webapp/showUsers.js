$(function() {

    // Add user
    $('#myform').submit(function(e) {
        e.preventDefault();
        ajaxCreate()});

    function ajaxCreate() {
        //event.preventDefault();
        //var datajson = $('form#myform').serializeJSON();
        //var obj = {id: null, username: $('#username').val(), initials: $('#initials').val()};
        //var datajson = JSON.stringify($('form#myform').serializeArray());
        var datajson = jsonString();
        //var datajson2 = JSON.parse(datajson);
        $.ajax({
            url : 'rest/useradmin/create',
            type : 'POST',
            data : datajson,
            contentType : 'application/json',
            //dataType : 'json', //MAYBE!?!?
            //dataType : "text",
            //contentType : 'text/plain',
            success : function(data){
                alert("suc ");
                addRow(JSON.parse(datajson));
            },
            error : function(data){
                alert("An unexpected error occured: " + JSON.stringify(datajson));
            }
        });
        return false;
    }

    function ajaxUpdate() {
        //event.preventDefault();
        //var datajson = $('form#formid').serializeJSON();
        //var obj = {id: $('#id').val(), name: $('#name').val(), amount: $('#amount').val()};
        //var datajson = JSON.stringify($('form#myform').serializeArray());
        // TODO lav update knap
        $.ajax({
            url : 'rest/useradmin/update',
            type : 'POST',
            data : datajson,
            contentType : 'application/json',
            //dataType : 'json', //MAYBE!?!?
            //contentType : 'text/plain',
            success : function(data){
                // TODO ændre række i html
                //addRow(data);
            },
            error : function(data){
                alert("An unexpected error occured");
            }
        });
        return false;
    }

    function ajaxGet(id) {
        $.ajax({
            url : 'rest/useradmin/getuser/' + id,
            type : 'GET',
            dataType : 'json',
            success : function(data){
                addRow(data);
            },
            error : function(data){
                alert("An unexpected error has occured");
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    }

    function ajaxGetUserlist() {
        $.ajax({
            url : 'rest/useradmin/userlist',
            type : 'GET',
            success : function(data){
                addUserlist(data);
            },
            error : function(data){
                alert("An unexpected error has occured");
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    }

    function ajaxDelete(id) {
        $.ajax({
            url : 'rest/useradmin/delete/' + id,
            type : 'POST',
            //dataType : 'json', // TODO datatype?
            success : function(data){
                // TODO slet række i html
                //deleteRow(data);
            },
            error : function(data){
                alert("An unexpected error has occured");
            }
        });
        return false; //for at undgå at knappen poster data (default behavior).
    }

    function addRow(data) {

        alert("heeeu " + data.username);
        var table = document.getElementById("myTableData");

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        // TODO få deleteRow til at slette den rigtige bruger
        row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
        row.insertCell(1).innerHTML= data.id;
        row.insertCell(2).innerHTML= data.username;
        row.insertCell(3).innerHTML= data.initials;


        var rolesList = "";
        var i;
        for (i = 0 ; i < data.roles.length ; i++){
            rolesList += data.roles[i];
            rolesList += ", ";
        }
        row.insertCell(4).innerHTML= rolesList;

    }

    function deleteRow(obj) {
        var index = obj.parentNode.parentNode.rowIndex;
        var table = document.getElementById("myTableData");
        table.deleteRow(index);
    }

    function addUserlist(data) {
        var idArray = data.split(",");

        var i;
        for (i = 0 ; i < idArray.length ; i++){
            addRow(ajaxGet(i));
        }
    }

// '{"id" : "tre", "name" : "etnavn", "amount" : "mange"}'
    function jsonString(){
        var json = '';
        json +=
            '{\"id\" : null, ' +
            '\"username\" : \"' + $('#username').val() + '\", ' +
            '\"initials\" : \"' + $('#initials').val() + '\", ' +
            '\"roles\" : [';


        var notNull = false;
        if (document.getElementById("admincheck").checked){
            json += "\"Admin\",";
            notNull = true;
        }

        if (document.getElementById("pharmcheck").checked){
            json += "\"Pharmaceut\",";
            notNull = true;
        }

        if (document.getElementById("producheck").checked){
            json += "\"Produktionsleder\",";
            notNull = true;
        }

        if (document.getElementById("labcheck").checked){
            json += "\"Laborant\",";
            notNull = true;
        }

        json = json.slice(0, json.length-1);

        if(notNull){
            json += "]";
        } else {
            json += "null"
        }

        json += "}";

        return json;
    }
});

