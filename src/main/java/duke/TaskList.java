package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList = new ArrayList<Task>();
    private Storage storage = new Storage();

    public void listTaskList() {
        int taskNumber = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasksList) {
            System.out.println(Integer.toString(taskNumber) + ". " + t);
            taskNumber++;
        }
    }

    public void markTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        tasksList.get(taskNumber).markAsDone();
        storage.saveTodoList(tasksList);
    }

    public void unmarkTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        tasksList.get(taskNumber).markAsNotDone();
        storage.saveTodoList(tasksList);
    }

    public void addToDo(String input) {
        try {
            String inputWithoutCommand = input.substring(5);

            ToDo toDo = new ToDo(inputWithoutCommand);
            tasksList.add(toDo);

            System.out.println("Got it. I've added this task:");
            System.out.println(toDo);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            storage.saveTodoList(tasksList);
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadline(String input) {
        int indexOfByString = input.indexOf("/by ");
        int indexOfDate = indexOfByString + 4;

        String inputWithoutCommand = input.substring(9, indexOfByString - 1);

        String date = input.substring(indexOfDate, input.length() - 1);

        Deadline deadline = new Deadline(input.substring(9, indexOfByString), input.substring(indexOfDate));
        tasksList.add(deadline);

        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        storage.saveTodoList(tasksList);
    }

    public void addEvent(String input) {
        String inputWithoutCommand = input.substring(6);

        int indexOfFromSubstring = inputWithoutCommand.indexOf("/from ");
        int indexOfToSubstring = inputWithoutCommand.indexOf("/to ");

        String description = inputWithoutCommand.substring(0, indexOfFromSubstring);
        String startTime = inputWithoutCommand.substring(indexOfFromSubstring + 6, indexOfToSubstring - 1);
        String endTime = inputWithoutCommand.substring(indexOfToSubstring + 4);

        Event event = new Event(description, startTime, endTime);
        tasksList.add(event);

        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        storage.saveTodoList(tasksList);
    }

    public void deleteTask(String input) {
        int indexToBeRemoved = Integer.parseInt(input.split(" ")[1]);

        Task removedTask = tasksList.remove(indexToBeRemoved - 1);

        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        storage.saveTodoList(tasksList);
    }

    public void findEvent(String input) {
        String findString = input.substring(5);

        int taskNumber = 1;

        System.out.println("Here are the matching tasks in your list:");

        for (Task t : tasksList) {
            if (t.toString().contains(findString)) {
                System.out.println(Integer.toString(taskNumber) + ". " + t);
                taskNumber++;
            }
        }
    }
}
