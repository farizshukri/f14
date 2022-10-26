<!DOCTYPE html>
<html>
<head>
    <title>View Traffic Data</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Traffic Data</h1>
    <table>
        <thead>
            <tr>
                <th>Road Name</th>
                <th>Position</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <% 
                ResultSet rs = (ResultSet) request.getAttribute("trafficData");
                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("road_name") %></td>
                <td><%= rs.getString("position") %></td>
                <td><%= rs.getString("status") %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <a href="index.jsp" class="button">Back to Home</a>
</body>
</html>
