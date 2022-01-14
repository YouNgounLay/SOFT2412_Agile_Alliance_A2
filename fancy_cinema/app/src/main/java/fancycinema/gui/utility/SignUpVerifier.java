package fancycinema.gui.utility;

import fancycinema.user.User;
import java.util.List;

public class SignUpVerifier { 
    private List<User> users; 
    
    public SignUpVerifier(List<User> users) {
        this.users = users;
    }
    
    public boolean verifyUserName(String name) {  
        if (name.length() == 0) { 
            return false;
        }

        for (User user : this.users) {
            if (user.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean verifyPassword(String pw1, String pw2) { 
        return pw1.equals(pw2);
    }
}
