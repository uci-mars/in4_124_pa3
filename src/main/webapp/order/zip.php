<?php
    require_once "../pdo.php"; 

    $zip = $_GET["zip"];    //get inputted zipcode

    //replace zipcode as need, it is the table name
    $sql = "SELECT * FROM zipCode WHERE zipCode = :zip ";
    
        //select query
    //$result = $conn->query("SELECT * FROM zipcode where zip = :zip");
        //NOTE: query is preferable for using blank statements (just strings)
    //enter in query to sql
   $result = $conn->prepare($sql);
        //NOTE PREPARE IS MORE WGEB YOU NEEDA BIND STUFF
    //prepared statement
    $result->bindParam(':zip', $zip);
    $result->execute();
    //gets data
    $rtn = $result->fetch(PDO::FETCH_ASSOC);

    //return city, state
    $state = "";
    $city = "";

    if($rtn["stateName"]){
        $state = $rtn["stateName";]
    }

    if($rtn["stateName"]){
        $state = $rtn["stateName"];
    }
    echo $state.",".$city;
?>
<?php
    $conn = null;
?>