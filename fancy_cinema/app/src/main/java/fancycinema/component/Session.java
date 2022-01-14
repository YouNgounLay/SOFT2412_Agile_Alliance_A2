package fancycinema.component;

import java.time.LocalDateTime;

public class Session {

    private Movie movie;
    private LocalDateTime sessionTime;
    private int limit = 25;
    private int frontCapacity = 25;
    private int middleCapacity = 25;
    private int rearCapacity = 25;

    public Session(Movie movie, LocalDateTime sessionTime) {
        this.movie = movie;
        this.sessionTime = sessionTime;
    }

    public Movie getMovie() {return this.movie;}

    public void setMovie(Movie movie) {this.movie = movie;}

    public LocalDateTime getSessionTime() {return this.sessionTime;}

    public void setSessionTime(LocalDateTime sessionTime) { this.sessionTime = sessionTime; }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getFrontCapacity() {
        return frontCapacity;
    }

    public int getMiddleCapacity() {
        return middleCapacity;
    }

    public int getRearCapacity() {
        return rearCapacity;
    }

    public boolean addFrontCapacity(int capacity) {
        if (!(this.frontCapacity + capacity > limit)) {
            this.frontCapacity += capacity;
            return true;
        }
        return false;
    }

    public boolean rmFrontCapacity(int capacity) {
        if (!(this.frontCapacity - capacity < 0)) {
            this.frontCapacity -= capacity;
            return true;
        }
        return false;

    }

    public boolean addMiddleCapacity(int capacity) {
        if (!(this.middleCapacity + capacity > limit)) {
            this.middleCapacity += capacity;
            return true;
        }
        return false;
    }

    public boolean rmMiddleCapacity(int capacity) {
        if (!(this.middleCapacity - capacity < 0)) {
            this.middleCapacity -= capacity;
            return true;
        }
        return false;

    }

    public boolean addRearCapacity(int capacity) {
        if (!(this.rearCapacity + capacity > limit)) {
            this.rearCapacity += capacity;
            return true;
        }
        return false;
    }

    public boolean rmRearCapacity(int capacity) {
        if (!(this.rearCapacity - capacity < 0)) {
            this.rearCapacity -= capacity;
            return true;
        }
        return false;

    }
    
    public boolean isFull() {
        return frontCapacity == 0 && middleCapacity == 0 && rearCapacity == 0;
    }

    public int getVacant(){
        return this.frontCapacity + this.middleCapacity + this.rearCapacity;
    }

    public int getUnavailable(){
        int total = 3*this.limit - this.frontCapacity - this.rearCapacity - this.middleCapacity;
        return total;
    }
}
