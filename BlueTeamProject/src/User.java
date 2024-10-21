public abstract class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private String employeeID;

    public void logIn(){}

    public void signOut(){}
    
    //Accessors

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return emailAddress;
    }

    public String getID(){
        return employeeID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setFirstName(String name){}
    public void setLastName(String name){}
    public void setId(String id){}
    public void setEmail(String email){}
    public String toString(){
        return "";
    }

}
