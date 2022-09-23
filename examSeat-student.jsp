<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
    </style>
</head>
<body>
    <h1>Student Dashboard</h1>
    <h2>Welcome, <%= request.getAttribute("studentName") %>!</h2>
    <p>Your seat number is: <%= request.getAttribute("seatNumber") %></p>
</body>
</html>
