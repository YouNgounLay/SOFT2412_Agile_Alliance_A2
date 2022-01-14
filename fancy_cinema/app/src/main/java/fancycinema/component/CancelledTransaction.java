package fancycinema.component;//cinema,movie,time

//import fancycinema.component.Movie;
//import fancycinema.component.Seats;

import java.time.LocalDateTime;

public class CancelledTransaction {

    private String username;
    private LocalDateTime time;
    private String reason;


    public CancelledTransaction(String username, LocalDateTime time, String reason) {
        this.username = username;
        this.time = time;
        this.reason = reason;

    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getUserName() {
        return this.username;
    }

    public String getReason() {
        return reason;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}