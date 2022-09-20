<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
        .login-form {
            display: inline-block;
            text-align: left;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <h1>Login</h1>
    <div class="login-form">
        <h2>Admin Login</h2>
        <form action="admin" method="post">
            <input type="hidden" name="action" value="loginAdmin" />
            <label>Username: <input type="text" name="username" /></label><br/>
            <label>Password: <input type="password" name="password" /></label><br/>
            <input type="submit" value="Login" />
        </form>

        <h2>Student Login</h2>
        <form action="student" method="post">
            <input type="hidden" name="action" value="loginStudent" />
            <label>Roll Number: <input type="text" name="rollNo" /></label><br/>
            <input type="submit" value="Login" />
        </form>
    </div>
</body>
</html>
