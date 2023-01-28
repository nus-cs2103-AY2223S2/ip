import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws DukeException {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<Task> tasksList = new ArrayList<Task>();

        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();
                String command = input.split(" ")[0];

                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    saveTodoList(tasksList);
                    break;
                } else if (command.equals("list")) {
                    int taskNumber = 1;
                    System.out.println("Here are the tasks in your list:");
                    // this is the code to copy for Level-7 Save
                    for (Task t : tasksList) {
                        System.out.println(Integer.toString(taskNumber) + ". " + t);
                        taskNumber++;
                    }
                } else if (command.equals("mark")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasksList.get(taskNumber).markAsDone();
                    saveTodoList(tasksList);
                } else if (command.equals("unmark")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasksList.get(taskNumber).markAsNotDone();
                    saveTodoList(tasksList);
                } else if (command.equals("todo")) {
                    try {
                        String inputWithoutCommand = input.substring(5);

                        ToDo toDo = new ToDo(inputWithoutCommand);
                        tasksList.add(toDo);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(toDo);
                        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                        saveTodoList(tasksList);
                    } catch (StringIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (command.equals("deadline")) {
                    int indexOfByString = input.indexOf("/by ");
                    int indexOfDate = indexOfByString + 4;

                    String inputWithoutCommand = input.substring(9, indexOfByString - 1);

                    String date = input.substring(indexOfDate, input.length() - 1);

                    Deadline deadline = new Deadline(input.substring(9, indexOfByString), input.substring(indexOfDate));
                    tasksList.add(deadline);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                    saveTodoList(tasksList);
                } else if (command.equals("event")) {
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
                    saveTodoList(tasksList);
                } else if (command.equals("delete")) {
                    int indexToBeRemoved = Integer.parseInt(input.split(" ")[1]);

                    Task removedTask = tasksList.remove(indexToBeRemoved - 1);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                    saveTodoList(tasksList);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                // if you try and catch the exception, you can still continue to run the program! WOW!
                e.printStackTrace();
            }
        }
    }
    // saves TodoList whenever there is a change
    static void saveTodoList(ArrayList<Task> tasksList) {
        int taskNumber = 1;
        StringBuilder stringBuilder = new StringBuilder();

        // this is how to get the path to duke.txt
        Path path = Paths.get("data", "duke.txt");

        for (Task t : tasksList) {
            stringBuilder.append(Integer.toString(taskNumber)).append(". ").append(t).append("\n");
            taskNumber++;
        }

        // tries to create a file
        try {
            File newFile = new File(path.toUri());
            if (newFile.createNewFile()) {
                // System.out.println("File created: " + newFile.getName());
            } else {
                // System.out.println("duke.txt already exists.");
            }
        } catch (IOException e) {
            // System.out.println("An error occurred");
            e.printStackTrace();
        }

        // write into the file
        try {
            FileWriter fileWriter = new FileWriter(path.toFile());
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
            // System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            // System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
