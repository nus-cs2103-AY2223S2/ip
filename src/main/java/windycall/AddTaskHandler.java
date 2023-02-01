package windycall;

import java.util.List;

public abstract class AddTaskHandler extends OperationHandler {

    public static void addTask(String message, List<Task> tasks, Storage storage) throws WindyCallException {
        Ui.space();
        String[] parts = message.split(" ");

        Task newTask;
        if (parts[0].equals("todo")) {
            if (message.length() == 4 || message.substring(4).trim().isEmpty()) {
                throw new WindyCallException("☹" +
                        "" +
                        " OOPS!!! The description of a todo cannot be empty!");
            }
            String description = message.substring(5);
            System.out.println("Got it. I've added this Todo task:");
            newTask = new Todo(description, false);
        }
        else if (parts[0].equals("deadline")) {
            int idx = message.indexOf("/by");
            if (message.length() == 8 || message.substring(8).trim().isEmpty()
                    || (idx != -1 && message.substring(8, idx).trim().isEmpty())) {
                throw new WindyCallException("☹ OOPS!!! The description of a deadline cannot be empty!");
            }
            if (idx == -1) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify the deadline for the Task");
            }
            if (message.length() == idx + 3 || message.substring(idx + 3).trim().isEmpty()) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify the deadline for the Task");
            }
            String description = message.substring(9, idx - 1);
            System.out.println("Got it. I've added this Deadline task:");
            String deadline = message.substring(idx + 4);
            newTask = new Deadline(description, false, deadline);
        }
        else if (parts[0].equals("event")) {
            int idxFrom = message.indexOf("/from");
            int idxTo = message.indexOf("/to");
            if (message.length() == 5 || message.substring(5).trim().isEmpty()
                    || (idxFrom != -1 && message.substring(5, idxFrom).trim().isEmpty())) {
                throw new WindyCallException("☹ OOPS!!! The description of an event cannot be empty!");
            }
            if (idxFrom == -1) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify start time of the event!");
            }
            if (message.length() == idxFrom + 5 || message.substring(idxFrom + 5).trim().isEmpty()
                    || (idxTo != -1 && message.substring(idxFrom, idxTo).trim().isEmpty())) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify start time of the event!");
            }
            if (idxTo == -1) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify end time of the event!");
            }
            if (message.length() == idxTo + 3 || message.substring(idxTo + 3).trim().isEmpty()) {
                throw new WindyCallException("☹ OOPS!!! You haven't specify end time of the event!");
            }
            String description = message.substring(6, idxFrom - 1);
            String from = message.substring(idxFrom + 6, idxTo - 1);
            String to = message.substring(idxTo + 4);
            System.out.println("Got it. I've added this Event task:");
            newTask = new Event(description, false, from, to);
        } else {
            throw new WindyCallException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Ui.space();
        System.out.println(newTask);
        tasks.add(newTask);
        storage.handleTaskChange(tasks);
        Ui.space();
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

}
