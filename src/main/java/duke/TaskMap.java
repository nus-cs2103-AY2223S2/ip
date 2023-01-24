package duke;

import java.util.Map;

public class TaskMap {
    static Map<String, Task> tm = Map.of(
            "T", new ToDos(),
            "E", new Events(),
            "D", new Deadlines()
    );
}
