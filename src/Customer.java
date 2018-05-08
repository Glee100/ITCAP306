public class Customer{

	private String fName;
	private String lName;
	private String address;
	private String phone;
	private String email;
	
	public final int MIN_NAME_LENGTH = 1;
	public final int PHONE_LENGTH = 10;
	
	public Customer(){};
	
   //constructor  
   public Customer(String fName,String lName, String address, String phone, String email){
	   this.fName = fName;
	   this.lName = lName;
	   this.address = address;
	   this.phone = phone;
	   this.email = email;
    }
   
   //getters
   
   //get first name
   public String getFirstName()
   {
	   return this.fName;
   }
   
   //get last name
   public String getLastName()
   {
	   return this.lName;
   }
   
   //get address
   public String getAddress()
   {
	   return this.address;
   }
   
   //get phone number
   public String getPhone()
   {
	   return this.phone;
   }
   
   //get email
   public String getEmail()
   {
	   return this.email;
   }
   
   //set first name
   public void setFirstName(String name)
   {
	   //if name is empty throw exception
	   if(name == null || name.isEmpty())
	   {
		   throw new IllegalArgumentException("Must enter a name.");
	   }

	   //if the name contains a number, throw exception
	    for(char c : name.toCharArray()){
	        if(Character.isDigit(c)){
	            throw new IllegalArgumentException("Name must not contain a number.");
	        } 
	    }

	   //if the length of the name is greater than 0
	   if(name.length() >= MIN_NAME_LENGTH)
	   {
		   this.fName = name;
	   }
	   else {
		   throw new IllegalArgumentException("First name must be at least one character.");
	   }

   }
   
   //set last name
   public void setLastName(String name)
   {
	   name = name.trim();
	   //if name is empty throw exception
	   if(name == null || name.isEmpty())
	   {
		   throw new IllegalArgumentException("Must enter a name.");
	   }

	   //if the name contains a number, throw exception
	    for(char c : name.toCharArray()){
	        if(Character.isDigit(c)){
	            throw new IllegalArgumentException("Name must not contain a number.");
	        } 
	    }

	   //if the length of the name is greater than 0
	   if(name.length() >= MIN_NAME_LENGTH)
	   {
		   this.lName = name;
	   }
	   else {
		   throw new IllegalArgumentException("Last name must be at least one character.");
	   }

   }
   
   //set address
   public void setAddress(String address)
   {
       address = address.trim(); 
  	   final int MAX_NUM_ADD = 5;
  	   final int MAX_NUM_ST = 15;
  	   final int MAX_NUM_LAST = 8;
   //if name is empty throw exception
   if(address == null || address.isEmpty())
   {
	   throw new IllegalArgumentException("Must enter a address");
   }

   //if the length of the name is greater than 6 : 1 a st
   if(address.length() >= 6)
   {
	   address = address;
   }
   else {		   
	   throw new IllegalArgumentException("Must enter a number , name and type \n Example : 123 fake st");
   }
   if((!(Character.isDigit(address.charAt(0))))){
	   throw new IllegalArgumentException("Error! An address must start with a digit");
   }

   
   int num =0;
   String firstPart = address.substring(0,MAX_NUM_ADD);
   for(int x = 0; x < firstPart.length(); x++){
       	   if(((Character.isDigit(firstPart.charAt(x))))){
       	   num++;
       }
   }
   int st = 0;
   int last = 0;
  
        String secondPart = address.substring(num, address.length());
        System.out.println(address.substring(num, address.length()));
        
       if(!(secondPart.charAt(0) == ' ')){
    	   throw new IllegalArgumentException("Error! incorrect format needs blank spaces or exceeded number of digits");
       }else{
           
           for(int x = 1; x < secondPart.length(); x++){
                if((!(Character.isDigit(secondPart.charAt(x))))){
                    if(secondPart.charAt(x)== ' '){
                         last = st +1;
                    }
                   st++;
       	       
       	       }  
       	       if(Character.isDigit(secondPart.charAt(x))){
       	    	throw new IllegalArgumentException("Error !Street's name cannot have numbers!");
               }
         }
       }
       if(last > MAX_NUM_ST){
    	   throw new IllegalArgumentException("Error! Street name is too long!");
       }
    
        String thirdPart = address.substring((num+last), address.length());
         if(secondPart.equals(thirdPart)){
        	 throw new IllegalArgumentException("Error! Must enter a number , name and type \n Example : 123 fake st");
           }
          if(!(thirdPart.charAt(0) == ' ')){
        	  throw new IllegalArgumentException("Error! Must enter a number , name and type \n Example : 123 fake st");
          }else{
             for(int x = 1; x < thirdPart.length(); x++){
                if((!(Character.isDigit(thirdPart.charAt(x))))){
       	       }  
       	       if(Character.isDigit(thirdPart.charAt(x))){
       	    	throw new IllegalArgumentException("Error, you cannot have more digits");
               }
         }
        
       
}
 if(thirdPart.length() > MAX_NUM_LAST){
	 throw new IllegalArgumentException("Error! Street type is too long!");
       }
 System.out.println("Valid address has been entered");
}
   //set phone
   public void setPhone(String phone)
   {
	   //if name is empty throw exception
	   if(phone == null || phone.isEmpty())
	   {
		   throw new IllegalArgumentException("Must enter a phone number.");
	   }

	   //if phone number contains letters, throw exception
	   /* for(char c : phone.toCharArray()){
	        if(!Character.isDigit(c)){
	            throw new IllegalArgumentException("Phone number must only be a digit.");
	        } 
	    }

	   //if the length of the phone number must be 10
	   if(phone.length() == PHONE_LENGTH)
	   {
		   this.phone = phone;
	   }
	   else {
		   throw new IllegalArgumentException("Phone number must be 10 digits.");
	   }*/
	   
	   String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$" ;
	   if (phone.matches(regexStr)) {
	    System.out.println("Correct phone number format has been entered");
	    
	   }else {
	       throw new IllegalArgumentException("You must enter a correct format!");
	   }

   }
   
   //set email
   public void setEmail(String email)
   {
	   //if name is empty throw exception
	   if(email == null || email.isEmpty())
	   {
		   throw new IllegalArgumentException("Must enter an email address.");
	   }
	   //if the length of the name is greater than 0
	   if(email.length() >= MIN_NAME_LENGTH)
	   {
		   this.email = email;
	   }
	   else {
		   throw new IllegalArgumentException("email must be at least one character.");
	   }
       int counterAt =0;
       int counterDot = 0;
       //trimming email to eliminate blank spaces
       email = email.trim();
      for (int i =0; i <email.length(); i++){
          
          if(email.charAt(i) == '@'){
           counterAt ++;
           }
          if(email.charAt(i) == '.'){
           counterDot++;
           }
          if(counterAt >= 2 || counterDot >= 2){
        	  throw new IllegalArgumentException("Error! You must enter a correct email format");
           }
          if (email.indexOf("@") >= 1 && (email.indexOf(".") >= (email.indexOf("@")+2))){
                      System.out.print("Correct email  format has been entered");
          }else{
        	  throw new IllegalArgumentException("Error! You must enter a correct email format");
      }
   }

   }
   
   
   
   
}