package duke;

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

    /**
     * Marks a task as done.
     * @param input User input.
     * @return Display of task marked if it exists.
     */
    public String markAsDone(String input) {
        int index = Parser.findIndex(input);
        int listIndex = index + 1;
        if (! content.isEmpty()) {
            content.get(index).setDone();
            return "Nice! I've marked this task as done: " + "\n" + listIndex + ". " + content.get(index).toString();
        } else {
            return "No such task.";
        }
    }
    /**
     * Marks a task as undone.
     * @param input User input.
     * @return Display of task marked if it exists.
     */
    public String markAsUndone(String input) {
        int index = Parser.findIndex(input);
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

    /**
     * Creates an event task.
     * @param input User input.
     * @return Display of event task created.
     * @throws EmptyContentException
     */
    public String eventHandler(String input) throws EmptyContentException {
        if (input.length() < 7) {
            throw new EmptyContentException("event");
        }
        String[] segments = Parser.extractArgsFromInput(input);
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
    /**
     * Creates a todo task.
     * @param input User input.
     * @return Display of event task created.
     * @throws EmptyContentException
     */
    public String todoHandler(String input) throws EmptyContentException {
        if (input.length() < 5) {
            throw new EmptyContentException("todo");
        }
        String[] segments = Parser.extractArgsFromInput(input);
        String item = segments[1];

        if (item.isEmpty()) {
            throw new EmptyContentException("todo");
        }

        Todo newTodo = new Todo(item);
        content.add(newTodo);
        return HEADER + "\n" + newTodo + "\n" + String.format("Now you have %d tasks in the list.", content.size()) + "\n";

    }
    /**
     * Creates a deadline task.
     * @param input User input.
     * @return Display of deadline task created.
     * @throws EmptyContentException
     */
    public String deadlineHandler(String input) throws EmptyContentException {
        if (input.length() < 9) {
            throw new EmptyContentException("deadline");
        }
        String[] segments = Parser.extractArgsFromInput(input);
        String item = segments[0];
        String deadline = segments[1];

        if (item.isEmpty()) {
            throw new EmptyContentException("deadline");
        }

        Deadline newDeadline = new Deadline(item, deadline);
        content.add(newDeadline);
        return HEADER + "\n" + newDeadline + "\n" + String.format("Now you have %d tasks in the list.", content.size()) + "\n";

    }

    /**
     * Deletes a task from the list.
     * @param input User input.
     * @return Confirmation of task deleted if it exists.
     * @throws EmptyContentException
     * @throws InvalidTaskAccessException
     */
    public String deleteHandler(String input) throws EmptyContentException, InvalidTaskAccessException {
        if (input.length() < 7) {
            throw new EmptyContentException("delete");
        }
        int index = Parser.findIndex(input);
        if (index > content.size() || index < 0) {
            throw new InvalidTaskAccessException();
        } else {
            String taskContent = content.get(index).toString();
            content.remove(index);
            return "Noted. I've removed this task:" + "\n" + taskContent + "\n" +
                    String.format("Now you have %d tasks in the list.", content.size()) + "\n";
        }
    }

    /**
     * Finds all tasks containing a keyword
     * @param input input of the user
     * @return string with a list of all tasks containing a given keyword
     * @throws EmptyContentException
     */
    public String findHandler(String input) throws EmptyContentException {
        String[] segments = Parser.extractArgsFromInput(input);
        String keyword = segments[1];
        TaskList.findKeyword(keyword);
        return UIText.printFind(TaskList.getWordList());

    }
    public String remindHandler() {
        TaskList.findReminders();
        List<Task> reminders = TaskList.getRemindList();
        String allElements = "";
        for (int i = 0; i < reminders.size(); i++) {
            if (! reminders.isEmpty()) {
                allElements = allElements + (i + 1) + ". " + reminders.get(i).toString() + "\n";
            } else {
                break;
            }
        }
        if (allElements == "") {
            return "No upcoming tasks!";
        } else {
            return "Here are your tasks for the next 7 days:" + "\n" + allElements;
        }
    }
}
