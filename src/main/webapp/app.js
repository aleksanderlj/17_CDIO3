function addRow() {

    var userID = document.getElementById("userID");
    var username = document.getElementById("username");
    var initials = document.getElementById("initials");
    var roles = "";
    var inputElements = document.getElementsByClassName('admin');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            roles += ", "+inputElements[i].value;
            break;
        }
    }
    var inputElements = document.getElementsByClassName('pharmaceut');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            roles += ", "+inputElements[i].value;
            break;
        }
    }
    var inputElements = document.getElementsByClassName('produktionsleder');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            roles += ", "+inputElements[i].value;
            break;
        }
    }
    var inputElements = document.getElementsByClassName('laborant');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            roles += ", "+inputElements[i].value;
            break;
        }
    }
    var table = document.getElementById("myTableData");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
    row.insertCell(1).innerHTML= userID.value;
    row.insertCell(2).innerHTML= username.value;
    row.insertCell(3).innerHTML= initials.value;
    row.insertCell(4).innerHTML= roles.value;


}

function deleteRow(obj) {

    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("myTableData");
    table.deleteRow(index);

}

function load() {

    console.log("Page load finished");

}