package roody;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import roody.commands.Command;
import roody.commands.MakeTodoCommand;
import roody.exceptions.RoodyException;
import roody.tasks.Task;

import java.util.ArrayList;

public class ParserTest {
    @Test
    public void testParse_singleInput() {
        try {
            Command c = Parser.parse("todo dinner");
            ArrayList<Task> testList = new ArrayList<>();
            c.execute(testList, new Ui(), new Storage("test"));
            assertTrue(testList.size() == 1);
        } catch (RoodyException e) {
            System.out.println(e.getMessage());
        }
    }

    // To be achived in later implementations
    /*
    @Test
    public void testParse_multipleInputs() {
        String[] commands = Parser.parse("todo dinner tonight");
        assertEquals("todo", commands[0]);
        assertEquals("dinner tonight", commands[1]);
    }
     */
}
