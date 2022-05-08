package ru.javavision.servlet;

public class User{
    String Id;
    String Login, Password, EMail;
    User(String Id, String Login, String Password, String EMail){
        this.Id = Id;
        this.Login = Login;
        this.Password = Password;
        this.EMail = EMail;
    }
    public String getId() { return Id;}
    public String getLogin() { return Login;}
    public String getPassword() { return Password;}
    public String getEMail() { return EMail;}
}
