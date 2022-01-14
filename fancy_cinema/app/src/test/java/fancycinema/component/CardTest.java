package fancycinema.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    Card c1;
    Card c2;

    @BeforeEach
    void init() {
        c1 = new Card("Ray", "50049");
        c2 = new Card("Eleanor", "98360");
    }

    @Test
    void testGetSetCardName() {
        assertEquals(c1.getCardName(), "Ray");
        assertEquals(c2.getCardName(), "Eleanor");
        c1.setCardName("Yoshi");
        c2.setCardName("Jess");
        assertNotEquals(c1.getCardName(), "Ray");
        assertEquals(c1.getCardName(), "Yoshi");
        assertNotEquals(c2.getCardName(), "Eleanor");
        assertEquals(c2.getCardName(), "Jess");
    }

    @Test
    void testGetSetCardNumber() {
        assertEquals(c1.getCardNumber(), "50049");
        assertEquals(c2.getCardNumber(), "98360");
        c1.setCardNumber("42746");
        c2.setCardNumber("83610");
        assertNotEquals(c1.getCardNumber(), "50049");
        assertNotEquals(c2.getCardNumber(), "98360");
        assertEquals(c1.getCardNumber(), "42746");
        assertEquals(c2.getCardNumber(), "83610");
    }



}
