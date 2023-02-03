package cbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

public class TaskTest {
    Task t1 = new Task("test 1");
    Task t2 = new Task("test 2", true);
    
    @Test
    public void testGetDesc() {
        assertEquals(t1.getDesc(), "test 1");
    }
    
    @Test
    public void testGetSymbol() {
        assertEquals(t1.getSymbol(), "T");
    }
    
    @Test
    public void testHasTime() {
        assertFalse(t1.hasTime());
    }
    
    @Test
    public void testGetStatus1() {
        assertEquals(t1.getStatus(), " ");
    }
    
    @Test
    public void testGetStatus2() {
        assertEquals(t2.getStatus(), "X");
    }
    
    @Test
    public void testGetTime() {
        assertEquals(t1.getTime(), LocalDateTime.MIN);
    }
    
    @Test
    public void testToString1() {
        assertEquals(t1.toString(),
                "[T][ ] test 1");
    }
    
    @Test
    public void testToString2() {
        assertEquals(t2.toString(),
                "[T][X] test 2");
    }
    
    @Test
    public void testMakeFileFriendly1() {
        assertEquals(t1.makeFileFriendly(),
                "T ;;   ;; test 1");
    }
    
    @Test
    public void testMakeFileFriendly2() {
        assertEquals(t2.makeFileFriendly(),
                "T ;; X ;; test 2");
    }
    
    @Test
    public void testYesDoTrue1() {
        Task t = new Task("test", false);
        assertTrue(t.mark());
    }
    
    @Test
    public void testYesDoTrue2() {
        Task t = new Task("test", true);
        t.unmark();
        assertTrue(t.mark());
    }
    
    @Test
    public void testYesDoFalse1() {
        Task t = new Task("test", true);
        assertFalse(t.mark());
    }
    
    @Test
    public void testYesDoFalse2() {
        Task t = new Task("test", false);
        t.mark();
        assertFalse(t.mark());
    }
    
    @Test
    public void testNoDoTrue1() {
        Task t = new Task("test", true);
        assertTrue(t.unmark());
    }
    
    @Test
    public void testNoDoTrue2() {
        Task t = new Task("test", false);
        t.mark();
        assertTrue(t.unmark());
    }
    
    @Test
    public void testNoDoFalse1() {
        Task t = new Task("test", false);
        assertFalse(t.unmark());
    }
    
    @Test
    public void testNoDoFalse2() {
        Task t = new Task("test", true);
        t.unmark();
        assertFalse(t.unmark());
    }
    
    @Test
    public void testCompareTo() {
        assertTrue(t1.compareTo(t2) < 0);
    }
}