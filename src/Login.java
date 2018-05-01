import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class Login{
	
    private String userName;
    private String password;
    
     @Override
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Login) {
            Login l = (Login) obj;
            return (l.getUserName().equals(this.getUserName()) && l.getPassword() == this.getPassword());
        } else {
            return false;
        }
    }
    
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
    /*
    public boolean validate(Login input, Login system ){
    	if(l.getUserName() == inputUsername )
    		return true;   
    }*/
    
    /*
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
    }*/
    
    //return matched Login
    public static Login validate(Login userInput, Map<Login, Vector<Account>> logins ){
    	
    	//create iterator of the logins Map
    	Iterator it = logins.entrySet().iterator();
    	
    	boolean isValid = false;
    	
    	//while there is a next pair
    	while(it.hasNext())
    	{
    		Map.Entry<Login, Vector<Object>> entry = (Map.Entry<Login, Vector<Object>>)it.next();
    		
    		//System.out.println("ENTRY KEY USERNAME " + entry.getKey().getUserName() + "    ENTRY KEY PASSWORD " + entry.getKey().getPassword());
    		
    		//System.out.println("USER INPUT USERNAME: " + userInput.getUserName() + "    USER INPUT PASSWORD: :" + userInput.getPassword());
    		
    		
    		//if the user input username and password does not match a login in the map, throw exception
    		
    		String entryUsername = entry.getKey().getUserName();
    		String entryPass = entry.getKey().getPassword();
    		
    		String inputUsername = userInput.getUserName();
    		String inputPass = userInput.getPassword();
    		
    		//System.out.println("\nUSR is equal: " + entryUsername.equals(inputUsername));
    		//System.out.println("PASS is equal: " + entryPass.equals(inputPass));
    		
    		//System.out.println("INPUT USERNAME: " + inputUsername);
    		//System.out.println("ENTRY USERNAME: " + entryUsername);
    		
    		if(!entryUsername.equalsIgnoreCase(inputUsername) || !entryPass.equals(inputPass))
    		{
        		//System.out.println("VALIDATE" + entry.getKey().getUserName().equals(userInput.getUserName()) );
        		//System.out.println(entry.getKey().getPassword().equals(userInput.getPassword()));
    			isValid = false;

    			//
        		
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
    
    
    
    /*
    public static boolean validate(Login userInput, Map<Login, Vector<Account>> logins ){
    	
    	//create iterator of the logins Map
    	Iterator it = logins.entrySet().iterator();
    	
    	boolean isValid = false;
    	
    	//while there is a next pair
    	while(it.hasNext())
    	{
    		Map.Entry<Login, Vector<Object>> entry = (Map.Entry<Login, Vector<Object>>)it.next();
    		
    		//System.out.println("ENTRY KEY USERNAME " + entry.getKey().getUserName() + "    ENTRY KEY PASSWORD " + entry.getKey().getPassword());
    		
    		//System.out.println("USER INPUT USERNAME: " + userInput.getUserName() + "    USER INPUT PASSWORD: :" + userInput.getPassword());
    		
    		
    		//if the user input username and password does not match a login in the map, throw exception
    		
    		String entryUsername = entry.getKey().getUserName();
    		String entryPass = entry.getKey().getPassword();
    		
    		String inputUsername = userInput.getUserName();
    		String inputPass = userInput.getPassword();
    		
    		System.out.println("\nUSR is equal: " + entryUsername.equals(inputUsername));
    		System.out.println("PASS is equal: " + entryPass.equals(inputPass));
    		
    		System.out.println("INPUT USERNAME: " + inputUsername);
    		System.out.println("ENTRY USERNAME: " + entryUsername);
    		
    		if(!entryUsername.equalsIgnoreCase(inputUsername) || !entryPass.equals(inputPass))
    		{
        		System.out.println("VALIDATE" + entry.getKey().getUserName().equals(userInput.getUserName()) );
        		System.out.println(entry.getKey().getPassword().equals(userInput.getPassword()));
    			
    			//
        		
    		}
    		else {
    			isValid = true;
    		}
    	}
    	
    	if(!isValid)
    	{
    		throw new IllegalArgumentException("Error. Incorrect login credentials.");
    	}
    	
    	return isValid;

    }*/
    
    public String toString()
    {
    	return "Username: " + getUserName() + "   Password: " + getPassword();
    }
}