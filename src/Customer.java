public class Customer{

	private String fName;
	private String lName;
	private String address;
	private String phone;
	private String email;
	
	public final int MIN_NAME_LENGTH = 1;
	public final int PHONE_LENGTH = 10;
	
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
	   //if name is empty throw exception
	   if(address == null || address.isEmpty())
	   {
		   throw new IllegalArgumentException("Must enter an adress.");
	   }

	   //if the length of the name is greater than 0
	   if(address.length() >= MIN_NAME_LENGTH)
	   {
		   this.address = address;
	   }
	   else {
		   throw new IllegalArgumentException("Address must be at least one character.");
	   }

   }
   
   //set phone
   public void setPhone(String phone)
   {
	   //if name is empty throw exception
	   if(phone == null || phone.isEmpty())
	   {
		   throw new IllegalArgumentException("Must enter a phone number.");
	   }

	   //if the name contains a number, throw exception
	    for(char c : phone.toCharArray()){
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
	   
	   //if string contains '@' character
	    for(char c : phone.toCharArray()){
	        if(!Character.isDigit(c)){
	            throw new IllegalArgumentException("Phone number must only be a digit.");
	        } 
	    }

	   //if the length of the name is greater than 0
	   if(email.length() >= MIN_NAME_LENGTH)
	   {
		   this.email = email;
	   }
	   else {
		   throw new IllegalArgumentException("email must be at least one character.");
	   }

   }
   
   
   
   
}