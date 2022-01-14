package fancycinema.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CancelledTransactionTest {

    LocalDateTime timeOne;
    LocalDateTime timeTwo;
    LocalDateTime timeThree;
    CancelledTransaction ct1;
    CancelledTransaction ct2;
    CancelledTransaction ct3;

    @BeforeEach
    void init() {
        timeOne = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        timeTwo = LocalDateTime.of(2022, Month.JANUARY, 1, 13,00);
        timeThree = LocalDateTime.of(2022, Month.JANUARY, 2, 17,40);
        ct1 = new CancelledTransaction("Ray", timeOne, "Don't want to watch this movie anymore.");
        ct2 = new CancelledTransaction("Eleanor", timeTwo, "I'm broke");
        ct3 = new CancelledTransaction("Jess", timeThree, "Unknown");
    }

    @Test
    void testGetSetTime() {
        assertEquals(ct1.getTime(), timeOne);
        ct1.setTime(timeTwo);
        assertNotEquals(ct1.getTime(), timeOne);
        assertEquals(ct1.getTime(), timeTwo);
        ct2.setTime(timeOne);
        assertEquals(ct2.getTime(), timeOne);
    }

    @Test
    void testGetSetUsername() {
        assertEquals(ct1.getUserName(), "Ray");
        ct1.setUserName("Jimmy");
        assertNotEquals(ct1.getUserName(), "Ray");
        assertEquals(ct1.getUserName(), "Jimmy");
        ct2.setUserName("Bill");
        assertEquals(ct2.getUserName(), "Bill");
    }

    @Test
    void testGetSetReason() {
        assertEquals(ct3.getReason(), "Unknown");
        ct3.setReason("My friend cancelled");
        assertNotEquals(ct3.getReason(), "Unknown");
        assertEquals(ct3.getReason(), "My friend cancelled");
        ct2.setReason("Unknown");
        assertEquals(ct2.getReason(), "Unknown");
    }

}
