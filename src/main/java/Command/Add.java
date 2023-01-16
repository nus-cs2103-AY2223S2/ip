package Command;

import Storage.*;

public class Add {

    public Add(Storage s, String task) {
        if (task.contains("todo")) {
            s.addTask(new ToDo(task.substring(5)));
        } else if (task.contains("deadline")) {
            String t = task.substring(9);
            String[] taskAndDeadline = t.split("/");
            String deadlineTask = taskAndDeadline[0].trim();
            Task deadline = new Deadline(deadlineTask, taskAndDeadline[1]);
            s.addTask(deadline);
        } else if (task.contains("event")) {
            String t = task.substring(6);
            String[] eventAndDuration = t.split("/");
            String eventTask = eventAndDuration[0].trim();
            Task event = new Event(eventTask, eventAndDuration[1].trim(), eventAndDuration[2].trim());
            s.addTask(event);
        } else {
            System.out.println("Leo: " + task);
        }
    }
}
