<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Welcome to your CV Builder</h1>
    <form action="addSkill" method="post">
        <label>Skill: <input type="text" name="skillName" /></label><br/>
        <label>Proficiency: <input type="text" name="proficiencyLevel" /></label><br/>
        <input type="submit" value="Add Skill" />
    </form>

    <form action="generateResume" method="get">
        <input type="submit" value="Generate Resume" />
    </form>
</body>
</html>
