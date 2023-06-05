function enviar(){
  var http = new XMLHttpRequest();

  let mail = document.getElementById("mail").value;
  let pass = document.getElementById("pass").value;


  http.open("GET", "http://localhost:3002/DAWFarmacia/Login?mail="+mail+"&pass="+pass, true);

  http.onreadystatechange = function(){
      if(this.readyState==4 && this.status==200){
          var session = http.responseText;
          if(session !== null){
              sessionStorage.setItem("session",session)
              sessionStorage.setItem("mail",mail);
              window.location.href = "gestio.html";
          }else{
              alert("Session interrumpida. Vuelva a intentralo");
          }
      }
  }

  http.send();
}

  
  
 

function enviarAlta(){
    window.location.replace("alta.html");
}


function logOut(){
    window.location.replace("login.html")
    sessionStorage.removeItem("session");
}


// GESTION

function getTable() {
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3002/DAWFarmacia/ServeXips?mail=" + mail + "&session=" + session,true);
    
    http.onreadystatechange = function(){
        if(this.readyState== 4 && http.status==200){
            let table = http.response;
            if(table !== null){
                document.getElementById("altesTable").innerHTML = table;
                console.log(table)
            }
            else{
                alert("Error en la conexion")
            }
            
        }
    }
    console.log("getTable() invocada")
    http.send();
}

// ALTA

function getPatients(){
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');
  
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3002/DAWFarmacia/ServePatients?mail=" + mail + "&session=" + session, true);
  
    http.onreadystatechange = function() {
      if (http.readyState === 4 && http.status === 200) {
        var data = JSON.parse(http.responseText);
        console.log('Datos de pacientes:', data);
        
        var dropdown = document.getElementById('patientSelect'); // Obtener referencia al elemento <select>
  
        // Limpiar opciones existentes
        dropdown.innerHTML = '';
  
        // Iterar sobre los datos y crear opciones
       data.forEach(function(p,i) {
            if(i%2 === 0){
                var option = document.createElement("option");
                option.text = p;
                dropdown.add(option);
            }
       });
      }
    };
  
    http.send();
  }


  function getMedicines(){
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');
  
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3002/DAWFarmacia/ServeMedicines?mail=" + mail + "&session=" + session, true);
  
    http.onreadystatechange = function() {
      if (http.readyState === 4 && http.status === 200) {
        var data = JSON.parse(http.responseText);
        console.log('Datos de pacientes:', data);
        
        var dropdown = document.getElementById('medicineSelect'); // Obtener referencia al elemento <select>
  
        // Limpiar opciones existentes
        dropdown.innerHTML = '';
  
        // Iterar sobre los datos y crear opciones
       data.forEach(function(p,i) {
            if(i%2 === 0){
                var option = document.createElement("option");
                option.text = p;
                dropdown.add(option);
            }
       });
      }
    };
  
    http.send();
  }






  function enviarA(){
    var http = new XMLHttpRequest();
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');
    var idXip = document.getElementById('idXip').value;
    var medicineSelect = document.getElementById('medicineSelect').value;
    var patientList = document.getElementById('patientSelect').value;
    var expiryDate = document.getElementById('expiryDate').value;

   

    http.open("POST","http://localhost:3002/DAWFarmacia/Release?mail=" + mail +"&session=" + session + "&idXip=" + idXip + "&medicineSelect=" + medicineSelect + "&patientSelect=" 
    + patientList + "&fecha=" + expiryDate,true);
    
    http.onload = function(){
        if (this.readyState== 4 && http.status==200){
            var response = http.responseText;
            if(response = "ok"){
                console.info("XIP registrado")
                alert("XIP registrado correctamente")

                // Limpiar los inputs
                document.getElementById("idXip").value = "";
                document.getElementById("paciente").value = "";
                document.getElementById("medicamento").value = "";
                document.getElementById("fecha").value = "";
            }
    }else{
        alert("No se ha podido registrar el xip. Revise los parametros ID del chip o fecha límite.");
    }
};
    http.send();
}
  
  