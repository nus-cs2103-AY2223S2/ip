package donkeychat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToDoTest {
    @Test
    public void serializeTest(){
        assertEquals("T | 0 | test", new ToDo("test").serialize());
    }
}