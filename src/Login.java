import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class Login{
	
    private String userName;
    private String password;
    
    //use files to retrieve customer's data
    public Login(String userName, String password){
    	//setUserName(userName);
    	//setPassword(password);
    	this.userName = userName;
    	this.password = password;
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

    //return matched Login
    public static Login validate(Login userInput, Map<Login, Vector<Account>> logins ){
    	
    	//create iterator of the logins Map
    	Iterator it = logins.entrySet().iterator();
    	
    	boolean isValid = false;
    	
    	//while there is a next pair
    	while(it.hasNext())
    	{
    		Map.Entry<Login, Vector<Object>> entry = (Map.Entry<Login, Vector<Object>>)it.next();
    		
    		//if the user input username and password does not match a login in the map, throw exception
    		
    		String entryUsername = entry.getKey().getUserName();
    		String entryPass = entry.getKey().getPassword();
    		
    		String inputUsername = userInput.getUserName();
    		String inputPass = userInput.getPassword();
    		
    		if(!entryUsername.equalsIgnoreCase(inputUsername) || !entryPass.equals(inputPass))
    		{
    			isValid = false;

    		}
    		//if user and password matches, return matched Login
    		else {
    			isValid = true;
        		return entry.getKey();
    		}
    	}
    	
    	if(!isValid)
    	{
    		throw new IllegalArgumentException("Error. Incorrect login credentials.");
    	}
    	
    	return null;
    }
    
    
    public String toString()
    {
    	return "Username: " + getUserName() + "   Password: " + getPassword();
    }
}