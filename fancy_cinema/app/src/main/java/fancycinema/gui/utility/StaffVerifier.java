package fancycinema.gui.utility;

import java.util.List;

import fancycinema.user.User;

public class StaffVerifier {
    private List<User> users; 

    public StaffVerifier(List<User> users) { 
        this.users = users;
    }
    
    public boolean isValidStaff(String name) { 
        for (User user : users) { 
            if (user.getName().equals(name)) { 
                return false;
            }
        }
        return true;
    }
}
