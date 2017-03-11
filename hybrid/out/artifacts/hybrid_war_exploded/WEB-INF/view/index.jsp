<html>

<head>
    <title>
        标题
    </title>

    <!--<script>
        document.write(Date());
        alert("我敢保证，你现在用的是演示一");
    </script>-->
    <script>
        function displayDate() {
            document.getElementById("demo").innerHTML = Date();
        }
    </script>
</head>

<body>

    <h1>My First Heading----------------------------</h1>

    <p id="demo">My first paragraph.</p>

    <button type="button" onclick="displayDate()">显示日期</button>


</body>

</html>