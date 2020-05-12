

function processPurchaseForm(){
   
    //NOTE: don't need to check if is empty because cannot submit the form if any of the inputs are not filled out

    /*aler
    if(!validAddr()){ //addr doesnt match
       alert("please enter a valid address! (the State must be fully spelled out)");
        return;
        
    }*/
   
    if(!Number.isInteger(+document.getElementById("phoneNum").value))
    {
        alert("please enter a valid phone number (10 or 11 digits, no spaces or special character)");
        return false;
    }
    if(!validCCard()){
        alert("please enter a valid credit card! (16 digit number, expiration month/expiration year, CVV number)");
        return false;
    }
    if(!validItem()){
        alert("please choose a valid item");
        return false;
    }
    if(!validAmount()){
        alert("please choose a valid amount");
        return false;
    }

    
    return true;
    //sendMail();
}

function validAmount(){
    var amount = document.getElementById("quantity").value;
    if (amount == '') {
        return false;
    } else {
        return true;
    }
}

function validItem(){
    var item = document.getElementById("item").value;
    if (item == '') {
        return false;
    } else {
        return true;
    }
}

function validCCard(){

    var card = +document.getElementById("ccard").value;
    var mm = +document.getElementById("exMonth").value;
    var yy = +document.getElementById("exYr").value;
    var cvv = +document.getElementById("cvv").value;
    
    if(Number.isInteger(card) && Number.isInteger(mm) && Number.isInteger(yy) && Number.isInteger(cvv) && mm>0 && mm<=12){
        return true;
        
    }
    return false;
}


function validAddr() {
    var city = document.getElementById("city").value.toUpperCase();
    var state = document.getElementById("state").value.toUpperCase();
    var country = document.getElementById("country").value.toUpperCase();
   // alert(city);
    //var jso = JSON.parse(jsoData);
    //alert(jsoData[0].city_ascii);
    for (var i = 0; i < jsoData.length; i++) {
        if (jsoData[i].city_ascii.toUpperCase() == city) {
         //   alert("CITY FOUND");
            if (state == jsoData[i].admin_name.toUpperCase() && (country == jsoData[i].country.toUpperCase() || country == jsoData[i].iso2.toUpperCase() || country == jsoData[i].iso3.toUpperCase())) {
              //  alert("found");
                return true;
            }
        }
    }
    return false;
}
function updateTotal(){
    var sum = 0;
    sum += parseFloat(document.getElementById("itemTotal").innerHTML);
    sum += parseFloat(document.getElementById("taxTotal").innerHTML);
    sum += getShipping();
    document.getElementById("shippingTotal").innerHTML = getShipping();

    //replace total
    document.getElementById("totalPrice").innerHTML = sum;
}
function getItemCost(){
    var origin = document.getElementById ("item").value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function ()
    { 
        if (xhr.readyState == 4 && xhr.status == 200)
        { 
            
            var quantity = parseInt(document.getElementById("quantity").value);
            if(!isNaN(quantity)){
                var result = xhr.responseText;
                var cost = parseFloat(result) * quantity;
               //alert(cost); 
                document.getElementById("itemTotal").innerHTML = cost;
                updateTotal();
            }
            
        }
    }
      // Call the response software component
      xhr.open ("GET", "itemcost.php?item=" + origin,true);
      xhr.send (null);
}
function getShipping(){
    //alert(document.getElementById("overnight").checked);
    if(document.getElementById("overnight").checked){
        return 20;
    }else if(document.getElementById("2day").checked){
        return 13;
    }else if(document.getElementById("6day").checked){
        return 3;
    }
}
function updateZip(zip){
    getPlace(zip);
    getTax(zip);
    updateTotal();
}
function updateItemCost(){
    getItemCost();
    getTax(document.getElementById("zip").value);
    updateTotal();
}
function getTax(zip){
    //alert(zip);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function ()
    { 
        if (xhr.readyState == 4 && xhr.status == 200)
        { 
            var result = xhr.responseText;//result should be combined tax rate
            var itemCost = parseFloat(document.getElementById ("itemTotal").innerHTML);
            var cost = itemCost * parseFloat(result);
            //alert("result" + result);
            document.getElementById("taxTotal").innerHTML = cost;
            updateTotal();
        }
    }
      // Call the response software component
      xhr.open ("GET", "taxrate.php?zip=" + zip,true);
      xhr.send (null);
}

function getPlace(zip){
   
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function ()
    { 
        if (xhr.readyState == 4 && xhr.status == 200)
        { 
            
            var result = xhr.responseText;//result should be state,city
            //alert(result);
            var parsed = result.split (",");
            if (document.getElementById ("city").value == "")
                document.getElementById ("city").value = parsed[1];
            if (document.getElementById ("state").value == "")
                document.getElementById ("state").value = parsed[0];
        }
    }
      // Call the response software component
      xhr.open ("GET", "zip.php?zip=" + zip,true);
      xhr.send (null);
}



function sendMail() {
    var quantity = document.getElementById("quantity").value;
    var item = document.getElementById("item").value;
    
    var subject = "?subject=Order Successful: " +  quantity + " " + item + "(s).";
    
    var days = 1;
    if(document.getElementById("2day").checked){
        days = 2;
    }else if(document.getElementById("6day").checked){
        days = 6;
    }
    
   
    var card = document.getElementById("ccard").value.substr(12);
    var street = document.getElementById("street").value;
    var city = document.getElementById("city").value;
    var state =document.getElementById("state").value;
    var zip= document.getElementById("zip").value;
    var country= document.getElementById("country").value;
    
    var address = ""+street+ " " + city + " "+state+" "+zip+" "+country;
    
    var body = "&body=Your order of " + quantity + item + "(s) is on its way. It will arrive in " + days +" day(s) to "+address+". This purchase was made with card ending in " + card;
    

    window.location.href = "mailto:" + document.getElementById("email").value + subject + body;

    
    alert("success check your email!");
}
