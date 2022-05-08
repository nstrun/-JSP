package ru.javavision.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                    User curruser = new User(res.getString("ID"), res.getString("Login"), res.getString("Password"), res.getString("Name"), res.getString("EMail"), res.getString("Gender"), res.getString("FavColor"), res.getString("Description"), res.getString("Mailing"), res.getString("UsrAgrmnt"));
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
