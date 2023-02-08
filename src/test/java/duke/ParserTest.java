package duke;


import duke.duke.Parser;
import duke.exceptions.DukeException;
import duke.tasks.Events;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void Test() {
        System.out.println("Test1");
        Parser p = new Parser(System.getProperty("user.dir")
                + "/data/tasks.txt");
        String readLine =
                "event hello boss /from 2000-01-01 00:00 /to 2000-01-02 00:01";
        ArrayList<String> str = new ArrayList<>();
        try {
            str = p.queries(readLine.split(" "), List.<String>of("from", "to"));
            System.out.println(str);
        } catch (DukeException err) {
            System.out.println("Exception");
        }




    }

    @Test
    public void Test2() {
        System.out.println("Test2");
        Parser p = new Parser(System.getProperty("user.dir")
                + "/data/tasks.txt");
        String readLine = "deadline hello jiads /by";
        try {
            System.out.println(
                    p.queries(readLine.split(" "), List.<String>of("by")));
        } catch (DukeException err) {
            System.out.println("Exception");
        }
    }
}
