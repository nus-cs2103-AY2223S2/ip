package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodosTest {
    
    @Test 
    public void testMarkDone() {
        assertEquals("X", new Todos("return book").markAsDone().getStatusIcon());
    }
    
    @Test 
    public void testMarkUndone() {
        assertEquals(" ", new Todos("return book").getStatusIcon());
    }

    @Test 
    public void testToString() {
        assertEquals("[T][ ] return book", new Todos("return book").toString());
    }
  
}
