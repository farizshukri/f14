<!DOCTYPE html>
<html>
<head>
    <title>Update Traffic Light</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px;
        }
        .update-form {
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
    <h1>Update Traffic Light</h1>
    <div class="update-form">
        <form action="traffic" method="get">
            <input type="hidden" name="action" value="update" />
            <label>Traffic Light ID: <input type="number" name="lightId" required /></label><br/>
            <label>Status: 
                <select name="status" required>
                    <option value="RED">Red</option>
                    <option value="GREEN">Green</option>
                    <option value="YELLOW">Yellow</option>
                </select>
            </label><br/>
            <input type="submit" value="Update Status" />
        </form>
    </div>
    <a href="index.jsp" class="button">Back to Home</a>
</body>
</html>
