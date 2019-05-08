$(function() {
    ajaxGetUserlist();

    // Add user button
    $('#myform').submit(function(e) {
        e.preventDefault();
        ajaxCreate()});

    // Creates a user from the form
    function ajaxCreate() {
        var datajson = jsonString(null);
        var datajsonParsed = JSON.parse(datajson);
        $.ajax({
            url : 'rest/useradmin/create',
            type : 'POST',
            data : datajson,
            contentType : 'application/json',
            success : function(data){
                datajsonParsed.id = data;
                addRow(datajsonParsed)
            },
            error : function(data){
                alert("Upload cancelled:\nPlease make sure that all necessary information was entered");
            }
        });
        return false;
    }

    // Updates a user by using the details from the form
    function ajaxUpdate(id) {
        var datajson = jsonString(id);
        var datajsonParsed = JSON.parse(datajson);
        $.ajax({
            url : 'rest/useradmin/updateuser',
            type : 'POST',
            data : datajson,
            contentType : 'application/json',
            success : function(data){
                var row = document.getElementById("row" + id);
                row.parentNode.removeChild(row);
                addRow(datajsonParsed);
            },
            error : function(data){
                alert("Update cancelled:\nPlease make sure that all necessary information was entered");
            }
        });
        return false;
    }

    // Gets a user from the database
    function ajaxGet(id) {
        $.ajax({
            url : 'rest/useradmin/getuser/' + id,
            type : 'GET',
            dataType : 'json',
            success : function(data){
                addRow(data);
            },
            error : function(data){
                alert("An unexpected error has occured: GET_ERROR");
            }
        });
        return false;
    }

    // Gets the ID's for all users currently in the database
    function ajaxGetUserlist() {
        $.ajax({
            url : 'rest/useradmin/userlist',
            type : 'GET',
            success : function(data){
                if (data != null) {
                    addUserlist(data);
                }
            },
            error : function(data){
                alert("An unexpected error has occured: USERLIST_ERROR");
            }
        });
        return false;
    }

    // Adds all the DB users into a table on the webpage
    function addUserlist(data) {
        var idArray = data.split(",");

        var i;
        for (i = 0 ; i < idArray.length ; i++){
            ajaxGet(idArray[i]);
        }
    }

    // Deletes a user from the database
    function ajaxDelete(id) {
        $.ajax({
            url : 'rest/useradmin/delete/' + id,
            type : 'POST',
            success : function(data){

            },
            error : function(data){
                alert("An unexpected error has occured: DELETE_ERROR");
            }
        });
        return false;
    }

    // Creates a button for deleting a row on the webpage
    function makeDeleteButton(id){
        var btn = document.createElement('input');
        btn.type = "button";
        btn.name = "deletebutton";
        btn.value = "Delete";
        btn.onclick = (function() {deleteRow(this, id)});
        return btn;
    }

    // Creates a button for updating a row on the webpage
    function makeUpdateButton(id){
        var btn = document.createElement('input');
        btn.type = "button";
        btn.name = "updatebutton";
        btn.value = "Update";
        btn.onclick = (function() {ajaxUpdate(id)});
        return btn;
    }

    // Adds a row to the webpage and sorts the table
    function addRow(data) {
        var table = document.getElementById("myTableData");

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        row.id = "row" + data.id;

        //row.insertCell(0).innerHTML= '<input type="button" name="deletebutton" value = "Delete" onClick="Javacsript:deleteRow(this,' + data.id + ')">';
        row.insertCell(0).appendChild(makeDeleteButton(data.id));
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

        row.insertCell(5).appendChild(makeUpdateButton(data.id));

        sortTable();

    }

    // Deletes a row from the webpage
    function deleteRow(obj, id) {
        var index = obj.parentNode.parentNode.rowIndex;
        var table = document.getElementById("myTableData");
        table.deleteRow(index);
        ajaxDelete(id);
    }

    // Creates a String-json object from the form
    function jsonString(id){
        var json = '';
        json +=
            '{\"id\" : ' + id + ', ' +
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

    // Sorts the table (update this to be a merge-sort for epic speed)
    function sortTable() {
        var table, rows, hasSwitched, x, y;
        table = document.getElementById("myTableData");
        hasSwitched = true;

        while (hasSwitched) {
            hasSwitched = false;
            rows = table.rows;

            for (var i = 1; i < (rows.length - 1); i++) {
                x = rows[i].getElementsByTagName("TD")[1];
                y = rows[i + 1].getElementsByTagName("TD")[1];

                if (parseInt(x.innerHTML) > parseInt(y.innerHTML)) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    hasSwitched = true;
                    break;
                }
            }
        }
    }

    // Old method for deleting all users
    function deleteAllUsers() {
        var buttons = document.getElementsByName("deletebutton");

        for (var i = 0; i < buttons.length; i++) {
            buttons[i].click();
        }
    }
});






