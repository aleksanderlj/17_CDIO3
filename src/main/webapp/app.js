function addRow() {

    var userID = document.getElementById("userID");
    var username = document.getElementById("username");
    var initials = document.getElementById("initials");
    var roles = getRoles();

    var table = document.getElementById("myTableData");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
    row.insertCell(1).innerHTML= userID.value;
    row.insertCell(2).innerHTML= username.value;
    row.insertCell(3).innerHTML= initials.value;
    row.insertCell(4).innerHTML= roles;


}

function getRoles(){
    var roles = '';
    var admin = false;
    var inputElements = document.getElementsByClassName('admin');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            admin = true;
            break;
        }
    }
    var pharmaceut = false;
    var counter = 0;
    var inputElements = document.getElementsByClassName('pharmaceut');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            pharmaceut = true;
            counter++;
            break;
        }
    }
    var produktionsleder = false;
    var inputElements = document.getElementsByClassName('produktionsleder');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            produktionsleder = true;
            counter++;
            break;
        }
    }
    var laborant = false;
    var inputElements = document.getElementsByClassName('laborant');
    for(var i=0; inputElements[i]; ++i){
        if(inputElements[i].checked){
            laborant = true;
            counter++;
            break;
        }
    }
    if(admin==true){
        roles += 'admin';
        if(counter>=1){
            roles+= ', ';
        }
    }
    if(pharmaceut==true){
        roles += 'pharmaceut';
        if(counter>=1){
            roles+= ', ';
        }
    }
    if(produktionsleder==true){
        roles += 'produktionsleder';
        if(counter>=1){
            roles+= ', ';
        }
    }
    if(laborant==true){
        roles += 'laborant';
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