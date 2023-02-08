package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;





/**
 * The type To do test.
 */
class ToDoTest {

    /**
     * Test 1:Add function.
     */
    @Test
    void add() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.add();
        assertEquals("Got it. I've added this task:\n"
                + " [T][ ] borrow book", tdo.getMessageAdd());
    }

    /**
     * Test2: Display function.
     */
    @Test
    void display() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.display();
        assertEquals("[T][ ]borrow book", tdo.getMessageDisplay());
    }

    /**
     * Test3: Delete function.
     */
    @Test
    void delete() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.delete();
        assertEquals("Noted. I've removed this task:\n"
                + " [T][ ] borrow book", tdo.getMessageDelete());
    }

    /**
     * Test 4: Marked function.
     */
    @Test
    void marked() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.marked();
        assertEquals("Nice! I've marked this task as done:\n"
                + "  [T][X] borrow book", tdo.getMessageMarked());
    }

    /**
     * Test 5: Unmarked function.
     */
    @Test
    void unmarked() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.unmarked();
        assertEquals("OK, I've marked this task as not done yet:\n"
                + " [T][ ] borrow book", tdo.getMessageUnmarked());
    }
}
