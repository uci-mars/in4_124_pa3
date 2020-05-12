<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Page</title>
        <link rel="stylesheet" href="item.css">
        <link rel="stylesheet" href="../product.css">
        <link rel="stylesheet" href="../../navbar.css">
    </head>
    
    <body>
        <div class="container">
        <div class="navbar">
            <a href="../..">
                <div class="logo-container">
                    <img id="logo" src="../../assets/images/ornn-logo.png">
                    <h1 id="text-logo">ORNN'S<br/>
                    <span style="font-size: 18px">WORKSHOP</span></h1>
                </div>
            </a>

            <div class="nav-container">
                <a class="active" href="..">Our Products</a>
                <a href="../../about">About us</a>
                <a href="../../about#team">Our Team</a>
                <a href="../../order"><img style="height: 20px" src="../../assets/images/cart.svg"></a>
            </div>
        </div>
            
        <div class="product-container">
        <?php
            require_once "../../pdo.php";
            $id = htmlspecialchars($_GET["id"]);
            $stmt = $conn->query("SELECT * FROM items WHERE itemID = " . $id);
            while ( $row = $stmt->fetch(PDO::FETCH_ASSOC)){
                echo('<h2 id="product-name">'. $row['itemName'] .'</h2>');
                echo('<img style="display: flex; margin: auto" id="product-image" src="../' . $row['img'] . '">');
        ?>
    
            
            
            
            <div style="display: flex; margin: auto; margin-top: 12px;">
                <img class="gold-img" src="../assets/gold.png">
                <span style="color: yellow">Price:</span> <span style="color: white">&nbsp;$ </span> <span id="product-cost" style="color: white">&nbsp;<?php echo ($row['costs'])?></span>
            </div>

            <p id="product-description">
                <?php echo($row['itemDescription']); }?>
            </p>
            
             <a href="../../order">
                <section class="product-button">
                    <span>GO TO ORDER FORM</span>
                </section>
            </a>

        </div>

        </div>
        
    </body>

</html>