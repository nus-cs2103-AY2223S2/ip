package duke;

public class TaskMap {
    /**
     * Assists in loading from file
     */

    static public Task get(String s) {
        switch (s) {
        case "T":
            return new ToDos();
        case "E":
            return new Events();
        case "D":
            return new Deadlines();
        }
        return new Task();
    }
}
