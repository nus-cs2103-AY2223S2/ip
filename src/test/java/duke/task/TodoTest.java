package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        Todo t = new Todo("do work");
        assertEquals("[T][ ] do work", t.toString());
    }
}