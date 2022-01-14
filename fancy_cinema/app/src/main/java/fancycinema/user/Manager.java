package fancycinema.user;
//inherits staff with aditional abilities\
import fancycinema.component.Movie; 


public class Manager extends Employee{

    public Manager(String name){
        super(name);
    }
    
    public Manager(String id, String userName, String password) {
        super(id, userName, password);
    }

    @Override
    public String getMovieDetails(Movie m) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "Manager []";
    }

    //public void addStaffMember(Staff Member)(){}

    //public void delStaffMember(Staff Member)(){}

    //public void getReportCancelledTransactions(){
    //  #DATETIME of cancel: xx
    //      #USER: name/anonym
    //      #REASON: xx
    // }

}

