package duke;

import duke.task.Deadline;
import duke.task.Task;

public class Test {
    public static void main(String[] args) {
        Task foo = new Deadline("bread", "2222-11-22");
        System.out.println(foo);
        System.out.println(foo.toLog());
    }
}
