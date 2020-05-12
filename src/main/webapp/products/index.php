<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Our Products</title>
        <link rel="stylesheet" href="product.css">
        <link rel="stylesheet" href="../navbar.css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

    </head>
    
    <body>
        
        <div class="container">
        <div class="navbar">
            <a href="..">
                <div class="logo-container">
                    <img id="logo" src="../../in4_124/assets/images/ornn-logo.png">
                    <h1 id="text-logo">ORNN'S<br/>
                    <span style="font-size: 18px">WORKSHOP</span></h1>
                </div>
            </a>

            <div class="nav-container">
                <a class="active" href="../products">Our Products</a>
                <a href="../about">About us</a>
                <a href="../about#team">Our Team</a>
                <a href="../order"><img style="height: 20px" src="../../in4_124/assets/images/cart.svg"></a>
            </div>
        </div>

        <div style="margin-top: 120px">
            <h2 class="page-title">OUR PRODUCTS</h2>
        </div>

        <div class="content-container">
            <div class="search-container">
                <input type="text" id="search" name="search" autocomplete="off" placeholder="Search Items">
            </div>

            <div class="item-table-container">
                <table>
                    <tr id="result"></tr>
                </table>

            </div>
                <div class="footer">
                    <p>Created for Educational Purposes. All assets belongs to Riot Games. </p>
                </div>
            </div>
        </div>



        <script type="text/javascript">
            function queryItems(q) {
                $.ajax({
                    type: "POST",
                    url: "search.php",
                    data: {
                        search: q
                    },
                    success: function(html) {
                        $("#result").html(html).show();
                    }
                });
            };

            queryItems("");

            $(document).ready(function() {
                $("#search").keyup(function() {
                    var name = $('#search').val();

                    queryItems(name);
                });
            });
        </script>
        
    </body>
</html>
