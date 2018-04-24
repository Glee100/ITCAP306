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
    public boolean validate(Login input, Login system ){
    	if(l.getUserName() == inputUsername )
    return true;   
    }
    
    public String toString()
    {
    	return "";
    }
}