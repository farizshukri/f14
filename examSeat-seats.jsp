<!DOCTYPE html>
<html>
<head>
    <title>Seat Allocation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Seat Allocation</h1>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Roll Number</th>
                <th>Seat Number</th>
            </tr>
        </thead>
        <tbody>
            <% 
                ResultSet rs = (ResultSet) request.getAttribute("seats");
                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getString("roll_no") %></td>
                <td><%= rs.getString("seat_number") %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
