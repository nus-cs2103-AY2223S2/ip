import exceptions.EmptyContentException;
import exceptions.InvalidTaskAccessException;

import java.util.List;

public class TaskHandler {
    private List<Task> content;
    public TaskHandler(TaskList taskList) {
        this.content = taskList.getContent();
    }
    private static final String HEADER = "Got it. I've added this task:";

    public String display() {
        String allElements = "";
        for (int i = 0; i < content.size(); i++) {
            if (! content.isEmpty()) {
                allElements = allElements + (i + 1) + ". " + content.get(i).toString() + "\n";
            } else {
                break;
            }
        }
        if (allElements == "") {
            return "No items in list!";
        } else {
            return "Here are the tasks in your list:" + "\n" + allElements;
        }
    }

    public String markAsDone(String input) {
        String num = input.split(" ")[1];
        int index = Integer.parseInt(num) - 1;
        int listIndex = index + 1;
        if (! content.isEmpty()) {
            content.get(index).setDone();
            return "Nice! I've marked this task as done: " + "\n" + listIndex + ". " + content.get(index).toString();
        } else {
            return "No such task.";
        }
    }

    public String markAsUndone(String input) {
        String num = input.split(" ")[1];
        int index = Integer.parseInt(num) - 1;
        int listIndex = index + 1;
        if (content.isEmpty()) {
            return "No such task.";
        } else if (! content.isEmpty() && content.get(index).isDone) {
            content.get(index).setUndone();
            return "Nice! I've marked this task as undone: " + "\n" + (listIndex) + ". " + content.get(index).toString();
        } else if(!content.get(index).isDone) {
            return "This task is already marked undone. ";
        } else {
            return "No such task.";
        }
    }

    public String eventHandler(String input) throws EmptyContentException {
        if (input.length() < 7) {
            throw new EmptyContentException("event");
        }
        String[] splitCommand = input.split(" ", 2);
        String[] segments = splitCommand[1].split("/");
        String item = segments[0];
        String startTime = segments[1];
        String endTime = segments[2];

        if (item.isEmpty()) {
            throw new EmptyContentException("event");
        }
        Event newEvent = new Event(item, startTime, endTime);
        content.add(newEvent);
        return HEADER + "\n" + newEvent + "\n" + String.format("Now you have %d tasks in the list.", content.size()) + "\n";
    }

    public String todoHandler(String input) throws EmptyContentException {
        if (input.length() < 5) {
            throw new EmptyContentException("todo");
        }
        String[] splitCommand = input.split(" ", 2);
        String item = splitCommand[1];

        if (item.isEmpty()) {
            throw new EmptyContentException("todo");
        }

        Todo newTodo = new Todo(item);
        content.add(newTodo);
        return HEADER + "\n" + newTodo + "\n" + String.format("Now you have %d tasks in the list.", content.size()) + "\n";

    }
    public String deadlineHandler(String input) throws EmptyContentException {
        if (input.length() < 9) {
            throw new EmptyContentException("deadline");
        }
        String[] splitCommand = input.split(" ", 2);
        String[] segments = splitCommand[1].split("/");
        String item = segments[0];
        String deadline = segments[1];

        if (item.isEmpty()) {
            throw new EmptyContentException("deadline");
        }

        Deadline newDeadline = new Deadline(item, deadline);
        content.add(newDeadline);
        return HEADER + "\n" + newDeadline + "\n" + String.format("Now you have %d tasks in the list.", content.size()) + "\n";

    }

    public String deleteHandler(String input) throws EmptyContentException, InvalidTaskAccessException {
        if (input.length() < 7) {
            throw new EmptyContentException("delete");
        }
        String[] splitCommand = input.split(" ", 2);
        int index = Integer.parseInt(splitCommand[1]) - 1;
        if (index >= content.size() || index < 1) {
            throw new InvalidTaskAccessException();
        } else {
            String taskContent = content.get(index).toString();
            content.remove(index);
            return "Noted. I've removed this task:" + "\n" + taskContent + "\n" +
                    String.format("Now you have %d tasks in the list.", content.size()) + "\n";
        }



    }


}
