package fancycinema.user;

public abstract class Employee extends User{
    protected String id; 
    protected String password;

    public Employee(String name){
        super(name);
    }
    
    public Employee(String id, String name, String password) { 
        super(name); 
        this.id = id; 
        this.password = password;
    }
    
    public String getId() { 
        return this.id;
    }

    public void setId(String id) { 
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }
}
