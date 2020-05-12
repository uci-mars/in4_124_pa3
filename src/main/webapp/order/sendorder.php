<?php
    require_once "../pdo.php";
    
    // Assigning Values
    if(isset($_POST['item'])) {
        $itemID = $_POST['item'];
        //echo $itemID . " ";
    }
    if(isset($_POST['quantity'])) {
        $amount = $_POST['quantity'];
        //echo $amount . " ";
    }
    // orderID auto-incremented in db
    if(isset($_POST['deliv'])) {
        $deliveryMethod = $_POST['deliv'];
        //echo $deliveryMethod . " ";
    }
    if(isset($_POST['fname'])) {
        $fullName = $_POST['fname'];
        //echo $fullName . " ";
    }
    if(isset($_POST['email'])) {
        $email = $_POST['email'];
        //echo $email . " ";
    }
    if(isset($_POST['phoneNum'])) {
        $phoneNumber = $_POST['phoneNum'];
        //echo $phoneNumber . " ";
    }
    if(isset($_POST['street'])) {
        $streetAddress = $_POST['street'];
        //echo $streetAddress . " ";
    }
    if(isset($_POST['city'])) {
        $city = $_POST['city'];
        //echo $city . " ";
    }
    if(isset($_POST['state'])) {
        $stateName = $_POST['state'];
        //echo $stateName . " ";
    }
    if(isset($_POST['zip'])) {
        $zipCode = $_POST['zip'];
        //echo $zipCode . " ";
    }
    if(isset($_POST['country'])) {
        $country = $_POST['country'];
        //echo $country . " ";
    }
    if(isset($_POST['ccard'])) {
        $creditCardNumber = $_POST['ccard'];
        //echo $creditCardNumber . " ";
    }
    if(isset($_POST['exMonth'])) {
        $expirationMonth = $_POST['exMonth'];
        //echo $expirationMonth . " ";
    }
    if(isset($_POST['exYr'][1])) {
        $expirationYear = $_POST['exYr'];
        //echo $expirationYear . " ";
    }
    if(isset($_POST['cvv'])) {
        $cvv = $_POST['cvv'];
        //echo $cvv;
    }

    // Assigning SQL query
    $sql = "INSERT INTO orders (itemID,amount,deliveryMethod,fullName,email,phoneNumber,streetAddress,city,stateName,zipCode,country,creditCardNumber,expirationMonth,expirationYear,cvv) VALUES(:itemID,:amount,:deliveryMethod,:fullName,:email,:phoneNumber,:streetAddress,:city,:stateName,:zipCode,:country,:creditCardNumber,:expirationMonth,:expirationYear,:cvv)";

    // Prepare Query and bind params
    $result = $conn->prepare($sql);
    $result->bindParam(':itemID', $itemID);
    $result->bindParam(':amount', $amount);
    $result->bindParam(':deliveryMethod', $deliveryMethod);
    $result->bindParam(':fullName', $fullName);
    $result->bindParam(':email', $email);
    $result->bindParam(':phoneNumber', $phoneNumber);
    $result->bindParam(':streetAddress', $streetAddress);
    $result->bindParam(':city', $city);
    $result->bindParam(':stateName', $stateName);
    $result->bindParam(':zipCode', $zipCode);
    $result->bindParam(':country', $country);
    $result->bindParam(':creditCardNumber', $creditCardNumber);
    $result->bindParam(':expirationMonth', $expirationMonth);
    $result->bindParam(':expirationYear', $expirationYear);
    $result->bindParam(':cvv', $cvv);

    // Execute SQL
    $result->execute();

?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Confirmation</title>

        <link rel="stylesheet" href="../products/product.css">
        <link rel="stylesheet" href="../navbar.css">
        <link rel="stylesheet" href="order.css">
    </head>
    <body>
        <div class="container">
            <div class="navbar">
                <a href="..">
                    <div class="logo-container">
                        <img id="logo" src="../assets/images/ornn-logo.png">
                        <h1 id="text-logo">ORNN'S<br/>
                        <span style="font-size: 18px">WORKSHOP</span></h1>
                    </div>
                </a>

                <div class="nav-container">
                    <a href="../products">Our Products</a>
                    <a href="../about">About us</a>
                    <a href="../about#team">Our Team</a>
                    <a href="../order"><img style="height: 20px" src="../assets/images/cart.svg"></a>
                </div>
            </div>
            <div style="margin-top: 120px">
                <h2 style="width: 100%; text-align: center; color: #f0e6d2; margin-bottom: 8px">ALL ORDER CONFIRMATIONS</h2>
            </div>
            <table>
                <tr>
                    <td><label for="item">Item</label></td>
                    <td><label for="item">Amount</label></td>
                    <td><label for="item">Delivery Method</label></td>
                    <td><label for="item">Customer Name</label></td>
                    <td><label for="item">Email</label></td>
                    <td><label for="item">Phone Number</label></td>
                    <td><label for="item">Shipping Address</label></td>
                    <td><label for="item">Credit Card Ending In</label></td>
                </tr>
                <?php
                require_once __DIR__ . "/../pdo.php";
                // SQL Return stmt
                $sqlTwo = "SELECT * FROM orders WHERE fullName= :fullName";
                $resultTwo = $conn->prepare($sqlTwo);
                $resultTwo->bindParam(':fullName', $fullName);
                $resultTwo->execute();
                //$rtn = $resultTwo->fetch(PDO::FETCH_ASSOC);
                while($rtn = $resultTwo->fetch(PDO::FETCH_ASSOC)) {
                    echo "<tr>";
                    echo "<td>" . $rtn['itemID'] . "</td>";
                    echo "<td>" . $rtn['amount'] . "</td>";
                    echo "<td>" . $rtn['deliveryMethod'] . "</td>";
                    echo "<td>" . $rtn['fullName'] . "</td>";
                    echo "<td>" . $rtn['email'] . "</td>";
                    echo "<td>" . $rtn['phoneNumber'] . "</td>";
                    echo "<td>";
                    echo $rtn['streetAddress'] . "<br>" . $rtn['city'] . ", " . $rtn['stateName'] . ", " . $rtn['zipCode'];
                    echo "</td>";
                    echo "<td>" . substr($rtn['creditCardNumber'],-4,4) . "</td>";
                    echo "</tr>";
                }
                ?>
            </table>
        </div>
    </body>
</html>
