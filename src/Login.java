import java.util.Iterator;
import java.util.Map;

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
    /*
    public boolean validate(Login input, Login system ){
    	if(l.getUserName() == inputUsername )
    		return true;   
    }*/
    
    public boolean validate(Login userInput, Map<Login, Customer> logins ){
    	
    	//create iterator of the logins Map
    	Iterator it = logins.entrySet().iterator();
    	
    	//while there is a next pair
    	while(it.hasNext())
    	{
    		Map.Entry<Login, Customer> entry = (Map.Entry<Login, Customer>)it.next();
    		
    		//if the user input username and password matches a login in the map, return true
    		if(entry.getKey().getUserName() == userInput.getUserName() && entry.getKey().getPassword() == userInput.getPassword())
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public String toString()
    {
    	return "";
    }
}