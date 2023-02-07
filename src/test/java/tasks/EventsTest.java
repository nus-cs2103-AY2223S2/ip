package tasks;
import duke.duke.Parser;
import duke.tasks.Events;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;



public class EventsTest {
    @Test
    public void Test() {
        String line = "event to eat stuff /from 2000-00-01 00:00 /to 2001-00-01 10:10";
        Parser ps = new Parser(System.getProperty("user.dir")
                + "/data/tasks.txt");
        try {
            ArrayList<String> queries = ps.queries(line.split(" "), List.<String>of("from, to"));
            Events event = new Events(queries);
            System.out.println(event);
        } catch (Exception err) {
            System.out.println("Exception");
        }

    }
}
