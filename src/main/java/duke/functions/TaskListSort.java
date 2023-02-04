package duke.functions;

import duke.task.Task;
import duke.task.TimedTask;

import java.sql.Time;
import java.util.Comparator;

public class TaskListSort  {

    static class SortByDeadline implements Comparator<Task>{
        @Override
        public int compare(Task t1, Task t2) {
            if (t1 instanceof TimedTask && t2 instanceof TimedTask) {
                TimedTask tt1 = (TimedTask) t1;
                TimedTask tt2 = (TimedTask) t2;
                return tt1.getEnd().compareTo(tt2.getEnd());
            } else if (t1 instanceof TimedTask && !(t2 instanceof TimedTask)) {
                return -1;
            } else if (!(t1 instanceof TimedTask) && t2 instanceof TimedTask) {
                return 1;
            }
            return 0;
        }
    }

    static class SortByDefault implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getTaskNumber() - t2.getTaskNumber();
        }
    }


}
