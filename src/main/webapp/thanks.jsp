<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Thanks</title>
    <link rel="stylesheet" href="styles/main.css"/>
</head>
<body>
<main class="container">
    <h1>Thanks for joining our list</h1>

    <p>Here is the information that you entered:</p>

    <ul>
        <li>First name: ${user.firstName}</li>
        <li>Last name: ${user.lastName}</li>
        <li>Email: ${user.email}</li>
        <li>Date of birth: ${user.dob}</li>
        <li>Heard about us: ${user.hearAbout}</li>
        <li>Receive CDs offers: ${user.receiveCds ? "Yes" : "No"}</li>
        <li>Receive email announcements: ${user.receiveEmails ? "Yes" : "No"}</li>
        <li>Preferred contact: ${user.contactBy}</li>
    </ul>

    <p><a href="index.html">Back to survey</a></p>
</main>
</body>
</html>
