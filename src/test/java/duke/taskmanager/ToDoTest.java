package duke.taskmanager;

import duke.bot.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void ToDoTest1() {
        try {
            Tasks td = new ToDo("borrow book");
            String actual = td.icon() + " " + td.getDesc();
            String expected = "[T] borrow book" ;
            assertEquals(actual, expected);

        } catch(Exception ignored) {

        }

    }

    @Test
    public void ToDoTest2(){
        try {
            Tasks td = new ToDo("borrow book");
            td.mark();
            String expected = "[X]" ;
            String actual = td.completed();
            assertEquals(actual, expected);
        } catch(Exception ignored) {

        }

    }
}