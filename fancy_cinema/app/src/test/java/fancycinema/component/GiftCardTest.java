package fancycinema.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GiftCardTest {

    GiftCard gc1;
    GiftCard gc2;

    @BeforeEach
    void init() {
        gc1 = new GiftCard("1234123412341234", 50);
        gc2 = new GiftCard("4321432143214321", 35);
    }

    @Test
    void testGetGCNumber() {
        assertEquals(gc1.getGCNumber(), "1234123412341234GC");
        assertNotEquals(gc1.getGCNumber(), "1234123412341234");
        assertEquals(gc2.getGCNumber(), "4321432143214321GC");
        assertNotEquals(gc2.getGCNumber(), "4321432143214321");
    }
    @Test
    void testGetGCList() {
        List<GiftCard> gcList = GiftCard.getGCList();
        assertTrue(gcList.contains(gc1));
        assertTrue(gcList.contains(gc2));
    }

    @Test
    void testGetSetAmount() {
        assertEquals(gc1.getAmount(), 50);
        assertNotEquals(gc1.getAmount(), 10);
        assertEquals(gc2.getAmount(), 10);
        assertNotEquals(gc2.getAmount(), 35);
        gc1.setAmount(105);
        assertNotEquals(gc1.getAmount(), 105);
        assertEquals(gc1.getAmount(), 10);
        gc2.setAmount(20);
        assertNotEquals(gc2.getAmount(), 10);
        assertEquals(gc2.getAmount(), 20);
    }

    @Test
    void testRedeem() {
        assertTrue(gc1.isRedeemable());
        assertTrue(gc1.redeemGC(10));
        assertTrue(gc1.isRedeemable());
        assertTrue(gc1.redeemGC(40));
        assertFalse(gc1.isRedeemable());
        assertFalse(gc1.redeemGC(10));
        assertFalse(gc2.redeemGC(20));
        assertTrue(gc2.isRedeemable());
        assertTrue(gc2.redeemGC(10));
        assertFalse(gc2.isRedeemable());
    }


}
