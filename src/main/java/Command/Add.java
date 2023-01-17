package Command;

import LeoException.*;
import Storage.*;

public class Add {

    public Add(Storage s, String task) throws LeoException {
        try {
            if (task.contains("todo")) {
                s.addTask(new ToDo(task.substring(5)));
            } else if (task.contains("deadline")) {
                try {
                    String t = task.substring(9);
                    String[] taskAndDeadline = t.split("/");
                    String deadlineTask = taskAndDeadline[0].trim();
                    Task deadline = new Deadline(deadlineTask, taskAndDeadline[1]);
                    s.addTask(deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDeadlineException("Leo: Uh oh! There is no deadline indicated :-(");
                }
            } else if (task.contains("event")) {
                try {
                    String t = task.substring(6);
                    String[] eventAndDuration = t.split("/");
                    String eventTask = eventAndDuration[0].trim();
                    Task event = new Event(eventTask, eventAndDuration[1].trim(), eventAndDuration[2].trim());
                    s.addTask(event);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteDurationException("Leo: Uh oh! The duration of the event is incomplete :-(");
                }
            } else if (task.contains("hello") || task.contains("hi") || task.contains("hey")) {
                System.out.println("Leo: Well hello to you too! :-D");
            } else {
                throw new InvalidInputException("Leo: Ohno! I do not know what you mean...\n     Sorry! :-(((");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("Leo: Uh oh! Description of task is empty :-(");
        }
    }
}
