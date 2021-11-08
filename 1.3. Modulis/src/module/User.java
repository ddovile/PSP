package module;

public class User 
{
    String id;
	String name;
	String surname;
	String email;
	String phoneNumber;
	String address;
	String password;

    public User()
    {
    	
    }
    public User(String id, String name, String surname, String phoneNumber, String email, String address, String password)
    {
    	this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
   
}
