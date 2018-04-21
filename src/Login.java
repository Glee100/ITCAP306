public class Login{
	
    private String userName;
    private String password;
    
    //use files to retrieve customer's data
    public Login(String userName, String password){
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
    	this.userName = userName;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
    	this.password = password;
    }
    public boolean validate(Login l){
        // validate password
    	if(l.getUserName() == inputUsername )
    return true;   
    }
    
    public String toString()
    {
    	return "";
    }
}