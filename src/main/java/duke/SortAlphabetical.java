package duke;

import duke.task.Task;

import java.util.Comparator;

public class SortAlphabetical implements Comparator<Task> {
    public int compare(Task taskOne, Task taskTwo) {
        return taskOne.getDescription().compareTo(taskTwo.getDescription());
    }
}