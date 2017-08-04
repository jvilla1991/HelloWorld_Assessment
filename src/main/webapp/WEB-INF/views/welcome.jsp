<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/9/2017
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>

<h1>Welcome! Please Register</h1>

<%--This tag can change depending on the violated requirement within the form
Server validation found within the addUser request mapping--%>
<b>${strerr}</b>

<body>

<%--The function validateForm will return true if all the criteria for the zip code are met--%>
<form action="addUser" name="uform" method="get" onsubmit="return validateform()">
    First Name <input type="text" name="firstname" class="stronly" maxlength="45" required>
    <br>
    Last Name <input type="text" name="lastname" class="stronly" maxlength="45" required>
    <br>
    Address1 <input type="text" name="address1" maxlength="45" required>
    <br>
    Address2 <input type="text" name="address2" maxlength="45">
    <br>
    City <input type="text" name="city" class="stronly" maxlength="45" required>
    <br>
    State <input type="text" name="state" class="stronly" maxlength="2" required>
    <br>
    Zip <input type="text" name="zip" maxlength="9" required>
    <br>
    Country <input type="text" value="US" name="country" readonly> Country must be US
    <br>
    <input type="submit" name="Add">
    <br>

</form>

<a href="adminpage">Jump to Admin Page</a>

<script>

//    This is a commonly implemented function for any validation
    function isAlpha(char){
        if ((char >= 'A' && char <= 'Z') || (char >= 'a' && char <= 'z'))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    // This form validation covers integer-only validation for the zip code
    function validateform()
    {
        var zvalid = document.forms["uform"]["zip"].value;

        // This loop checks each index for an alphabetical letter. If it finds one, it throws and alert error
        for (var i = 0; i < zvalid.length; i++)
        {
            if (isAlpha(zvalid[i]) == true)
            {
                alert("Zip must be only numbers");
                return false;
            }
        }

        // This if statement makes sure that the zip code length is either 5 or 9
        if (!((zvalid.length == 5) || (zvalid.length == 9)))
        {
            alert("Zip must be 5 or 9 in length")
            return false;
        }

        else
        {
            return true;
        }
    }

</script>

</body>
</html>
