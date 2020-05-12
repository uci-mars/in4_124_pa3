<?php
    require_once "../pdo.php"; 

    $zip = $_GET["zip"];    //get inputted zipcode

    //replace zipcode as need, it is the table name
    $sql = "SELECT * FROM taxRates WHERE zipCode = :zip ";
    
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

    //return rate(double)
    if($rtn["combinedRate"]){
        echo $rtn["combinedRate"];
    }else{
        echo "0";
    }
    //echo $rtn["combinedRate"]??="0";
?>
<?php
    $conn = null;
?>