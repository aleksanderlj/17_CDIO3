function addRow() {

    var userID = getNextUserID();
    var username = document.getElementById("username");
    var initials = document.getElementById("initials");
    var roles = makeRoles();

    var table = document.getElementById("myTableData");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
    row.insertCell(1).innerHTML= userID.value;
    row.insertCell(2).innerHTML= username.value;
    row.insertCell(3).innerHTML= initials.value;
    row.insertCell(4).innerHTML= roles;


}
function getInitials(){
    var initials = null;
    //TODO return initials from database
    //TODO and insert function at line 16
    return initials;
}
function getUsername(){
    var username = null;
    //TODO return username from database
    //TODO and insert function at line 15
    return username;
}
function getRoles(){
    var roles = null;
    //TODO return roles from database
    //TODO and insert function at line 17
    return roles;
}
function getUserID(){
 var userID = null;
 //TODO return userID from database
    //TODO and insert function at line 14
    return userID;
}
function getNextUserID(){
    var userID = 0;
    //TODO return next userID from database
    return userID;
}

function makeRoles(){
    //TODO optional: simplify this method
    var roles = '';
    var admin = false;
    var pharmaceut = false;
    var produktionsleder = false;
    var laborant = false;
    var counter = 0;

    var inputElements = document.getElementsByClassName('admin');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            admin = true;
            break;
        }
    }
    var inputElements = document.getElementsByClassName('pharmaceut');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            pharmaceut = true;
            counter++;
            break;
        }
    }
    var inputElements = document.getElementsByClassName('produktionsleder');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            produktionsleder = true;
            counter++;
            break;
        }
    }
    var inputElements = document.getElementsByClassName('laborant');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            laborant = true;
            counter++;
            break;
        }
    }
    if(admin==true){
        roles += 'Admin';
        if(counter>=1){
            roles+= ', ';
        }
    }
    if(pharmaceut==true){
        roles += 'Pharmaceut';
        if(counter>=1){
            roles+= ', ';
        }
    }
    if(produktionsleder==true){
        roles += 'Produktionsleder';
        if(counter>=1){
            roles+= ', ';
        }
    }
    if(laborant==true){
        roles += 'Laborant';
        if(counter>=1){
            roles+= ', ';
        }
    }
    var newRoles = roles;
    if(counter>=1){
        newRoles = roles.substr(0,roles.length-2);
    }
    return newRoles;
}

function deleteRow(obj) {
    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("myTableData");
    table.deleteRow(index);
}

function load() {
    console.log("Page load finished");
}