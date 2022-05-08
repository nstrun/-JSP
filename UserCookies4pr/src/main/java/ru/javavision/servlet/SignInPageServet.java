package ru.javavision.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import ru.javavision.servlet.User;

public class SignInPageServet extends HttpServlet {
    Connection con;
    Statement stmt;

    public void DBConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:D:\\userdata.db";
        String user = "root";
        String password = "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Statement getStatement() {
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\"/>");
        out.println("</head>");
        try {
            DBConnection();
            getStatement();
            String Login, Password;
            Login = req.getParameter("login");
            Password = req.getParameter("password");
            if(Login.length() > 3 || Password.length() > 3)
            {
                ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE Login = '" + Login + "' AND Password = '" + Password + "'");
                if (res != null) {
                    HttpSession session = req.getSession();
                    User curruser = new User(res.getString("ID"), res.getString("Login"), res.getString("Password"), res.getString("EMail"));

                    Cookie name = new Cookie("name", res.getString("Name"));
                    Cookie gender = new Cookie("gender", res.getString("Gender"));
                    Cookie favcolor = new Cookie("favcolor", res.getString("FavColor"));

                    name.setMaxAge(60*60*1); // Cookie действуют час
                    gender.setMaxAge(60*60*1);
                    favcolor.setMaxAge(60*60*1);

                    resp.addCookie(name);
                    resp.addCookie(gender);
                    resp.addCookie(favcolor);

                    session.setAttribute("user", curruser);
                    out.println("<h3>Session data are set</h3>");

                    out.println("There is your account information: ");
                    out.println("<div>" + res.getString("ID") + " - " + res.getString("Name") + " - " + res.getString("Gender") + " - " + res.getString("EMail")+ "</div>");
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("There was an error reading data from the database, double-check the entered data and try again");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        out.println("</html>");
        out.close();
    }
}
