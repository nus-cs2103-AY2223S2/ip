package duke.task;

import duke.storage.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList = new ArrayList<Task>();
    private Storage storage = new Storage();

    public String listTaskList() {
        int taskNumber = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (Task t : tasksList) {
            stringBuilder.append(Integer.toString(taskNumber) + ". " + t + "\n");
            taskNumber++;
        }

        return stringBuilder.toString();
    }

    public String markTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        String returnString = tasksList.get(taskNumber).markAsDone();
        storage.saveTodoList(tasksList);

        return returnString;
    }

    public String unmarkTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        String returnString = tasksList.get(taskNumber).markAsNotDone();
        storage.saveTodoList(tasksList);

        return returnString;
    }

    public String addToDo(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String inputWithoutCommand = input.substring(5);

            ToDo toDo = new ToDo(inputWithoutCommand);
            tasksList.add(toDo);

            stringBuilder.append("Got it. I've added this task:\n");
            stringBuilder.append(toDo + "\n");
            stringBuilder.append("Now you have " + tasksList.size() + " tasks in the list." + "\n");

            storage.saveTodoList(tasksList);
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            stringBuilder.append("☹ OOPS!!! The description of a todo cannot be empty." + "\n");
        }

        return stringBuilder.toString();
    }

    public String addDeadline(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        int indexOfByString = input.indexOf("/by ");
        int indexOfDate = indexOfByString + 4;

        String inputWithoutCommand = input.substring(9, indexOfByString - 1);

        String date = input.substring(indexOfDate, input.length() - 1);

        Deadline deadline = new Deadline(input.substring(9, indexOfByString), input.substring(indexOfDate));
        tasksList.add(deadline);

        stringBuilder.append("Got it. I've added this task:\n");
        stringBuilder.append(deadline + "\n");
        stringBuilder.append("Now you have ").append(tasksList.size()).append(" tasks in the list.\n");

        storage.saveTodoList(tasksList);

        return stringBuilder.toString();
    }

    public String addEvent(String input) {
        String inputWithoutCommand = input.substring(6);

        int indexOfFromSubstring = inputWithoutCommand.indexOf("/from ");
        int indexOfToSubstring = inputWithoutCommand.indexOf("/to ");

        String description = inputWithoutCommand.substring(0, indexOfFromSubstring);
        String startTime = inputWithoutCommand.substring(indexOfFromSubstring + 6, indexOfToSubstring - 1);
        String endTime = inputWithoutCommand.substring(indexOfToSubstring + 4);

        Event event = new Event(description, startTime, endTime);
        tasksList.add(event);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n");
        stringBuilder.append(event + "\n");
        stringBuilder.append("Now you have ").append(tasksList.size()).append(" tasks in the list.\n");

        storage.saveTodoList(tasksList);

        return stringBuilder.toString();
    }

    public String deleteTask(String input) {
        int indexToBeRemoved = Integer.parseInt(input.split(" ")[1]);

        Task removedTask = tasksList.remove(indexToBeRemoved - 1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task:\n");
        stringBuilder.append(removedTask + "\n");
        stringBuilder.append("Now you have ").append(tasksList.size()).append(" tasks in the list.\n");

        storage.saveTodoList(tasksList);

        return stringBuilder.toString();
    }

    public String findEvent(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        String findString = input.substring(5);

        int taskNumber = 1;

        stringBuilder.append("Here are the matching tasks in your list:\n");

        for (Task t : tasksList) {
            if (t.toString().contains(findString)) {
                stringBuilder.append(Integer.toString(taskNumber) + ". " + t + "\n");
                taskNumber++;
            }
        }

        return stringBuilder.toString();
    }
}
