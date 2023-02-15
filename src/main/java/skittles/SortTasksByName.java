package skittles;


import java.util.Comparator;


/**
 * Sorts task in alphabetical order.
 */
public class SortTasksByName implements Comparator<Task> {

    public int compare(Task a, Task b)
    {

        return a.toString().compareTo(b.toString());
    }
}
