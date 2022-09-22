<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
        .admin-form {
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
    <h1>Admin Dashboard</h1>

    <div class="admin-form">
        <h2>Add Student</h2>
        <form action="admin" method="post">
            <input type="hidden" name="action" value="addStudent" />
            <label>Name: <input type="text" name="name" /></label><br/>
            <label>Roll Number: <input type="text" name="rollNo" /></label><br/>
            <label>Section: <input type="text" name="section" /></label><br/>
            <label>Branch: <input type="text" name="branch" /></label><br/>
            <label>Year: <input type="number" name="year" /></label><br/>
            <input type="submit" value="Add Student" />
        </form>

        <h2>Allocate Seats</h2>
        <form action="admin" method="post">
            <input type="hidden" name="action" value="allocateSeats" />
            <input type="submit" value="Allocate Seats" />
        </form>
    </div>
</body>
</html>
