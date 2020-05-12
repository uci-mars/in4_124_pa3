<?php
    require_once "../pdo.php";

    if (isset($_POST['search'])) {
        $name = $_POST['search'];

        $stmt = $conn->query("SELECT itemID, itemName, img, costs, category 
                            FROM items
                            WHERE itemName 
                            LIKE '%$name%'");
        

        while ( $row = $stmt->fetch(PDO::FETCH_ASSOC)){
?>
<td>
    <?php echo("<p class='category-label'>" . $row['category'] . "</p>"); ?>
    <div class='item-container'>
        <?php echo("<a href='item/?id=" . $row['itemID'] . "'>"); ?>
            <div class='item-img-container'>
                <?php echo("<img class='item-img' src='" . $row['img'] . "'>"); ?>
            </div>
        <?php echo("<p>" . $row['itemName'] . "</p>"); ?>
        <div>
            <img class='gold-img' src='assets/gold.png'>
            <?php  echo("<span style='color: yellow'>Price:<span style='color: white'> $ " . $row['costs'] . "</span></span>"); ?>
        </div>
        </a>
    </div>
</td>

<?php }}?>
