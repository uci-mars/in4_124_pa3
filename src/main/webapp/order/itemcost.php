<?php
    require_once "../pdo.php"; 

    $item = $_GET["item"];    //get inputted item name

    //replace zipcode as need, it is the table name
    $sql = "SELECT * FROM items WHERE itemName = :item ";
    
        //select query
    //$result = $conn->query("SELECT * FROM zipcode where zip = :zip");
        //NOTE: query is preferable for using blank statements (just strings)
    //enter in query to sql
   $result = $conn->prepare($sql);
        //NOTE PREPARE IS MORE WGEB YOU NEEDA BIND STUFF
    //prepared statement
    $result->bindParam(':item', $item);
    $result->execute();
    //gets data
    $rtn = $result->fetch(PDO::FETCH_ASSOC);

    //return city, state
    if($rtn["costs"]){
        echo $rtn["costs"];
    }else{
        echo "0";
    }
    //echo $rtn["costs"]??="0";
?>
<?php
    $conn = null;
?>