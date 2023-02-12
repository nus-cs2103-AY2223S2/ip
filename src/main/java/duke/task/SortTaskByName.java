package duke.task;

import java.util.Comparator;

public class SortTaskByName implements Comparator<Task> {

    public int compare(Task a, Task b)
    {

        return a.toString().compareTo(b.toString());
    }
}