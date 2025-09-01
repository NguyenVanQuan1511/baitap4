<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java Error</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            color: #333;
            margin: 40px;
            line-height: 1.6;
        }
        h1 {
            color: #2c5f77;
            font-size: 50px;
            margin-bottom: 10px;
        }
        p {
            margin: 6px 0;
        }
        h2 {
            color: #2c5f77;
            font-size: 45px;
            margin-top: 20px;
            margin-bottom: 8px;
        }
    </style>
</head>
<body>
<h1>Java Error</h1>
<p>Sorry, Java has thrown an exception.</p>
<p>To continue, click the Back button.</p>

<h2>Details</h2>
<p>Type: <%= exception.getClass() %></p>
<p>Message: <%= exception.getMessage() %></p>
</body>
</html>
