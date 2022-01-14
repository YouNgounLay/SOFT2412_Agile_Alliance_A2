package fancycinema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fancycinema.component.GiftCard;
import fancycinema.component.Movie;
import fancycinema.component.Session;
import fancycinema.component.Booking;
import fancycinema.component.CancelledTransaction;
import fancycinema.component.Card;
import fancycinema.component.Cinema;
import fancycinema.data.CancelledTransactionDataManager;
import fancycinema.data.CinemaDataManager;
import fancycinema.data.CustomerDataManager;
import fancycinema.data.EmployeeDataManager;
import fancycinema.data.GiftCardDataManager;
import fancycinema.data.ManagerDataManager;
import fancycinema.data.MovieDataManager;
import fancycinema.gui.SceneLoader;
import fancycinema.user.Customer;
import fancycinema.user.Employee;
import fancycinema.user.LoggedIn;
import fancycinema.user.Manager;
import fancycinema.user.NotLoggedIn;
import fancycinema.user.Staff;
import fancycinema.user.User;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Fancy {
    private SceneLoader sceneLoader;
    private Customer user; 
    private Employee staff;
    private Manager manager; 
    private CustomerDataManager customerDataManager; 
    private EmployeeDataManager employeeDataManager; 
    private ManagerDataManager managerDataManager;
    private MovieDataManager movieDataManager;
    private GiftCardDataManager giftCardDataManager;
    private CancelledTransactionDataManager cancelledTransactionDataManager;
    private CinemaDataManager cinemaDataManager;
    private List<User> users;
    
    
    public Fancy(Stage stage){
        this.initialization();
        this.sceneLoader = new SceneLoader(stage, this);
    }

    public void initialization() {
        // Initialize Customer Data Manager
        this.customerDataManager = new CustomerDataManager();    

        // Initialize employee Data Manager
        this.employeeDataManager = new EmployeeDataManager();   
        
        // Initialize manager Data Manager
        this.managerDataManager = new ManagerDataManager();
        
        // Initialize movie 
        this.movieDataManager = new MovieDataManager();
        
        // Initialize GiftCard
        this.giftCardDataManager = new GiftCardDataManager();
        
        // Initialize Cinema 
        this.cinemaDataManager = new CinemaDataManager(this.getMovies());
        
        // Initialize CancelledTransactionDataManager;
        this.cancelledTransactionDataManager = new CancelledTransactionDataManager();
        
        this.initialiseUser();
        // Initialise all users
        this.user = new NotLoggedIn();
        
    }
    
    public Scene getScene() {
        return this.sceneLoader.getScene();
    }
    
    public Customer getUser() { 
        return this.user;
    }
    
    public String getUserName() { 
        if (!user.getName().equals("Guest")) { 
            return user.getName();
        } else if (staff != null) { 
            return staff.getName();
        } 
        return manager.getName();
    }
    
    public void setUser(Customer user) {
        this.user = user;
        this.staff = null; 
        this.manager = null;
    }
    
    public void setStaff(Employee staff) {
        this.staff = staff;
        this.user = new NotLoggedIn();
        this.manager = null; 
    }
    
    public void setManager(Manager manager) { 
        this.user = new NotLoggedIn();
        this.staff = null;
        this.manager = manager;
    }
    
    public boolean hasUser() { 
        return !this.user.getName().equals("Guest") || this.staff != null || this.manager != null;
    }
    
    public List<Customer> getCustomers() {
        return customerDataManager.getCustomers();
    }
    
    public List<Employee> getEmployees() {
        return employeeDataManager.getEmployees();
    }
    
    public List<Movie> getMovies() { 
        return movieDataManager.getMovies();
    }
    public Manager loadManager() {
        return this.managerDataManager.loadManager();
    }
    
    public List<User> getUsers() { 
        return this.users;
    }

    private void initialiseUser() { 
        users = new ArrayList<>();   
        users.add(new NotLoggedIn());
        for (Customer customer : this.getCustomers())
            users.add(customer);
        
        for (Employee employee : this.getEmployees()) 
            users.add(employee);

        users.add(managerDataManager.loadManager());
    }
    
    public void createCustomer(String name, String password) {
        String id = Integer.toString(customerDataManager.getID()); 
        Customer currentUser = new LoggedIn(id, name, password);
        this.user = currentUser;
        this.customerDataManager.addCustomer(currentUser); 
        this.users.add(currentUser);
    }
    
    public Customer getCustomer(String name, String password) {
        return this.customerDataManager.getCustomer(name, password);
    }

    public void createEmployee(String name, String password) { 
        String id = Integer.toString(employeeDataManager.getID());
        Employee newEmployee = new Staff(id, name, password);
        this.employeeDataManager.addEmployee(newEmployee);
        this.users.add(newEmployee);
    }

    public Employee getEmployee(String name, String password) { 
        return this.employeeDataManager.getEmployee(name, password);
    }
    
    public void removeEmployee(Employee employee) {
        this.employeeDataManager.removeEmployee(employee);
    }
    
    public Manager getManager(String name, String password) {
        return this.managerDataManager.getManager(name, password);
    }
    
    public List<GiftCard> getGiftCards() { 
        return this.giftCardDataManager.getGiftCards();
    }
    
    public void removeGiftCard(GiftCard giftCard) {
        this.giftCardDataManager.removeGiftCard(giftCard);
    }

    public void addGiftCard(GiftCard giftCard) { 
        this.giftCardDataManager.addGiftCard(giftCard);
    }
    
    public void addCreditCard(Card card) {
        this.customerDataManager.createCard(this.user, card);
    }
    
    public List<Card> getCreditCards() { 
        return this.customerDataManager.getCreditCards();
    }
    
    public void linkCreditCard(Card card) { 
        this.customerDataManager.linkCard(user.getName(), card);
    }
    
    public void addMovie(Movie movie) { 
        this.movieDataManager.addMovie(movie);
    }
    
    public void removeMovie(Movie movie) {
        this.movieDataManager.removeMovie(movie);
        this.cinemaDataManager.removeMovie(movie);
    }
    
    public void update() {

        this.customerDataManager.update();   
        this.employeeDataManager.update(); 
        this.managerDataManager.update();
        this.movieDataManager.update();
        this.giftCardDataManager.update(); 
        this.cinemaDataManager.update();
    }

    
    public List<Booking> getBookings(String userName) { 
        return this.cinemaDataManager.getBookings(userName);
    }
    
    public void addBooking(int cinemaId, Booking booking) { 
        this.cinemaDataManager.addBooking(cinemaId, booking);
    }
    
    public List<CancelledTransaction> getCancelledTransactions() { 
        return this.cancelledTransactionDataManager.getCancelledTransactions();
    }
    
    public void cancelBooking(Booking booking) { 
        String username = booking.getCustomerName();
        LocalDateTime time = booking.getSession().getSessionTime();
        String reason = username + " cancelled booking at " + time.toString();
        CancelledTransaction cancelledTransaction = new CancelledTransaction(username, time, reason);
        this.cinemaDataManager.removeBooking(booking);
        this.cancelledTransactionDataManager.addCancelledTransaction(cancelledTransaction);
    }
    
    public Map<Integer, Cinema> getCinemas() { 
        return cinemaDataManager.getCinemas();
    }
    
    public void addSession(Cinema cinema, Session session) {
        this.cinemaDataManager.addSession(cinema, session);
    }
}

