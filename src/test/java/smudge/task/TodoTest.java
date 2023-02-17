package smudge.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TodoTest {
    @Test
    void todoTest(){
        Todo test = new Todo("test");
        assertEquals("[T][ ] test", test.toString(), "toString() method works");

        test.markDone();
        assertEquals("[T][X] test", test.toString(), "markDone() method works");
    }
}
