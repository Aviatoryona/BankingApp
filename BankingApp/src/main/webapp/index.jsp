<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Concorde Bank</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <style>
            body{
                background-color: white;
                font-family: sans-serif;
                font-size: 14px;
            }

            #container{
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                justify-content: center;
                /*align-items: center;*/
            }

            .container{
                position: relative;
                width: 250px;
                height: 450px;
                color: #21759b;
                background-color: #f1f1f1;
                border-radius: 3px;
                box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.1);
                /*margin-left: 45%;*/
                margin-top: 5%;
                /*background-image: url('img/bg.png');*/
                background-position: center;
                background-repeat: no-repeat;

            }

            input{
                padding: 8.0px;
                border-top: none;
                border-bottom:  1px solid gray;
                border-left: none;
                border-right: none;
                font-family: sans-serif;
                font-size: 16px;
                margin-top: 22px;
            }

            button{
                margin-top: 22px;
                width: 200px;
                color: #ffffff;
                background-color: #330000;
                border-radius: 22px;
                box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.6);
                padding: 8px;
            }

            .copy{
                margin-top: 55px;
                text-align: center;
            }

        </style>
    </head>
    <body>
        <div class="limiter">
            <div id="container">
                <div class="container">
                    <img alt="bg image" src="img/bg.png" height="50" width="100%"/>
                    <form action="javascript:void(0)" style="padding: 22px">
                        <h3>Concorde Bank</h3>
                        <h4>Sign In</h4>

                        <input type="text" name="input_email" value="" placeholder="Username"/>
                        <br/>
                        <input type="password" name="input_pwd" value="" placeholder="Password"/>
                        <br/>
                        <button type="submit">Submit</button>

                        <div class="copy">
                            &copy;Concorde 2020
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>

        <script>
            (function ($) {
                alert("Hello");
            })(JQuery);
        </script>

    </body>
</html>