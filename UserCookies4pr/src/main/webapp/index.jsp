<%@ page contentType="text/html;charset=UTF-8" language="java" import = "java.io.*,java.util.*,ru.javavision.servlet.User" %>

<html>
<head language="ru">
    <meta charset="UTF-8" />
    <title>Registration</title>
</head>
<body>
    <%
        User curruser = (User)session.getAttribute("user");
        Cookie cookie = null;
        Cookie[] cookies = null;

        if(curruser == null) {
            out.print("Sesson is not found");
        }
        else
        {
            cookies = request.getCookies();

            out.print("You sign in as: <br/>");
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if(cookie.getName().equals("name") || cookie.getName().equals("favcolor")) {
                    out.print(cookie.getName() + " - ");
                    out.print(cookie.getValue()+" <br/>");
                }
            }

        }
    %>
    <form action="/signin">
        <h3>Sign In</h3>
        <div class="signUpPanel">
            <div class="signUpDiv"><input type="text" name="login"  id="login" placeholder="Login" required></div>
            <div class="signUpDiv"><input type="password" name="password"  id="password" placeholder="Password" required></div>
            <input type="submit" value="Sign In" id="btn_add">
        </div>
    </form>
    <form action="/user">
        <h3>Sign Up</h3>
        <div><input type="text" name="login" id="login" placeholder="Login" required/></div>

        <div><input type="password" name="password" id="password" placeholder="Password" required/></div>

        <div><input type="text" name="name" id="name" placeholder="Name" required/></div>

        <div><input type="text" name="email" id="email" placeholder="EMail" required/></div>

        <div>Gender</div>
        <div><input type="radio" name="gender" value="Not specify" checked/><label>Not specify</label></div>
        <div><input type="radio" name="gender" value="Male"/><label>Male</label></div>
        <div><input type="radio" name="gender" value="Female"/><label>Female</label></div>
        <div><input type="radio" name="gender" value="Other"/><label>Other</label></div>


        <div>Favorite color</div>
        <select name="favColor" id="favColor" required>
            <option value="Not specify">Not specify</option>
            <option value="Red" style="background-color: Red;">Red</option>
            <option value="Orange" style="background-color: Orange;">Orange</option>
            <option value="Yellow" style="background-color: Yellow;">Yellow</option>
            <option value="Green" style="background-color: Green;">Green</option>
            <option value="Blue" style="background-color: Blue;">Blue</option>
            <option value="Purple" style="background-color: Purple;">Purple</option>
            <option value="Pink" style="background-color: Pink;">Pink</option>
            <option value="Black" style="background-color: Black;">Black</option>
            <option value="Brown" style="background-color: Brown;">Brown</option>
            <option value="Beige" style="background-color: Beige;">Beige</option>
            <option value="White" style="background-color: White;">White</option>
        </select>

        <div>Mailing</div>
        <input type="checkbox" name="mailing" id="mailing" value="Agree"/>

        <div>User agreement</div>
        <input type="checkbox" name="usrAgrmnt" id="usrAgrmnt" value="Agree" required/>

        <div>Description</div>
        <textarea name="description" id="description" size="100"></textarea>

        <div><input type="submit" value="MyServlet"></div>
    </form>

    <script>
        let myElement = document.querySelector("#signUpPopUp");
        function open_signup() {
            if (myElement.style.display == "block")
                myElement.style.display = "none";
            else
                myElement.style.display = "block";
        }
    </script>
</body>
</html>
