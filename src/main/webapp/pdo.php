<?php
    
    $servername = "circinus-39.ics.uci.edu:3306";
    $dbname = "s2020Iae";
    $username = "root";
    $password = "bl0b-39";
    

    //michelle testing locally
/*    $servername = "localhost";
    $dbname = "in4_124";
    $username = "root";
    $password = "";
*/

    try 
    {

        //connects to the db
        $conn = new PDO("mysql:host=$servername; dbname=$dbname", $username, $password);

        //sets the error mode attribute to throw exceptions
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }

    catch (PDOException $e) {
       echo "Connection failed: ";
    }

?>