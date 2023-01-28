package duke;


import duke.duke.Parser;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParserTest {

    @Test
    public void Test() {
        System.out.println("Test1");
        Parser p = new Parser();
        String readLine =
                "event hello /from 2000-01-01 00:00 /to 2000-01-02 00:01";
        try {
            System.out.println(p.queries(readLine.split(" "), List.<String>of("from", "to")));
        } catch (DukeException err) {
            System.out.println("Exception");
        }


    }

    @Test
    public void Test2() {
        System.out.println("Test2");
        Parser p = new Parser();
        String readLine = "deadline hello jiads /by";
        try {
            System.out.println(
                    p.queries(readLine.split(" "), List.<String>of("by")));
        } catch (DukeException err) {
            System.out.println("Exception");
        }
    }
}
