import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    // For PixlBot printing
    private static void PixlPrint(String text) {
        System.out.println(Values.COLOR_PURPLE + "PixlBot: " + Values.COLOR_RESET + text);
//        System.out.println("Pixlbot: " + text);
        System.out.println(Values.HLINE);
    }

    // Specify color overload
    private static void PixlPrint(String text, String textColor) {
        System.out.println(Values.COLOR_PURPLE + "PixlBot: " + textColor + text + Values.COLOR_RESET);
        System.out.println(Values.HLINE);
    }

    // Create appropriate string format for a task
    private static String formatTask(Task task) {
        return String.format("[%s][%s] %s", task.getTaskType(), task.getStatusIcon(), task.getDescription());
    }

    // Helper method to get index of an item in array.
    private static int indexOf(String[] arr, String item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Save current task list to hard disk.
    private static void saveData() {
        try {
            File file = new File("src/data/duke");
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException fe) {
            PixlPrint("Error while saving data:" + fe.getMessage(), Values.COLOR_RED);
        }
    }

    // Loads data from "src/data/duke" if exists
    private static void loadData() {
        try {
            FileInputStream fis = new FileInputStream("src/data/duke");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<Task>) ois.readObject();
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            // Ignore.
        }
    }

    private static void listCommand() {
        StringBuilder output = new StringBuilder("Your current task list:\n");
        for (int i = 0; i < list.size(); i++) {
            output.append("\t").append(i + 1).append(". ").append(formatTask(list.get(i))).append("\n");
        }
        PixlPrint(output.toString());
    }

    private static void todoCommand(String command) throws DukeException{
        String[] parts = command.split("\\s+");
        if (parts.length == 1) {
            throw new DukeException("ToDo description cannot be empty.");
        }

        // Get task name.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }

        Task task = new ToDo(taskName.toString());
        list.add(task);
        PixlPrint("Added new todo!\n" +
                "\t" + formatTask(task) +
                "\nYou now have " + list.size() + " task(s) in the list.");
    }

    private static void deadlineCommand(String command) throws DukeException{
        String[] parts = command.split("\\s+");
        int byIndex = indexOf(parts, "/by");

        // Get name of task.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        // Get due date.
        StringBuilder dueDate = new StringBuilder();
        for (int i = byIndex+1; i < parts.length; i++) {
            dueDate.append(i == byIndex+1 ? "" : Values.SPACE);
            dueDate.append(parts[i]);
        }

        if (taskName.length() == 0 || dueDate.length() == 0) {
            throw new DukeException("Please provide both a deadline description and a due date.\n" +
                    "\tFormat: deadline <description> /by <due_date>");
        }

        Task task = new Deadline(taskName.toString(), dueDate.toString());
        list.add(task);
        PixlPrint("Added new deadline!\n" +
                "\t" + formatTask(task) +
                "\nYou now have " + list.size() + " task(s) in the list.");
    }

    private static void eventCommand(String command) throws DukeException {
        String[] parts = command.split("\\s+");
        int fromIndex = indexOf(parts, "/from");
        int toIndex = indexOf(parts, "/to");

        // Get task name.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < fromIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        // Get start date.
        StringBuilder startDate = new StringBuilder();
        for (int i = fromIndex+1; i < toIndex; i++) {
            startDate.append(i == fromIndex+1 ? "" : Values.SPACE);
            startDate.append(parts[i]);
        }
        // Get end date.
        StringBuilder endDate = new StringBuilder();
        for (int i = toIndex+1; i < parts.length; i++) {
            endDate.append(i == toIndex+1 ? "" : Values.SPACE);
            endDate.append(parts[i]);
        }

        if (taskName.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
            throw new DukeException("Please provide a description, start date, and end date.\n" +
                    "\tFormat: event <description> /from <start_date> /to <end_date>");
        }

        Task task = new Event(taskName.toString(), startDate.toString(), endDate.toString());
        list.add(task);
        PixlPrint("Added new event!\n" +
                "\t" + formatTask(task) +
                "\nYou now have " + list.size() + " task(s) in the list.");
    }

    private static void markCommand(String command) throws DukeException{
        try {
            Task task = list.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
            task.complete();
            PixlPrint("You completed a task!\n" +
                    "\t" + formatTask(task));
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to mark.");
        }
    }

    private static void unmarkCommand(String command) throws DukeException {
        try {
            Task task = list.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
            task.uncomplete();
            PixlPrint("Un-doing the task...\n" +
                    "\t" + formatTask(task));
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to unmark.");
        }
    }

    private static void deleteCommand(String command) throws DukeException {
        try {
            Task task = list.remove(Integer.parseInt(command.split("\\s+")[1]) - 1);
            PixlPrint("Removed the task:\n" +
                    "\t" + formatTask(task) +
                    "\nYou now have " + list.size() + " task(s) in the list.");
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to delete.");
        }
    }

    private static void chatCycle(String command) throws DukeException{
        switch (command.split("\\s+")[0]) {
            case "list":
                listCommand();
                break;
            case "mark":
                markCommand(command);
                break;
            case "unmark":
                unmarkCommand(command);
                break;
            case "todo":
                todoCommand(command);
                break;
            case "deadline":
                deadlineCommand(command);
                break;
            case "event":
                eventCommand(command);
                break;
            case "delete":
                deleteCommand(command);
                break;
            default:
                throw new DukeException("I don't know that command.");
        }

    }

    public static void main(String[] args) {
        // Print the logo and welcome user.
        System.out.println("Welcome to\n" + Values.LOGO);
        System.out.println("Enter a command to start.\n");

        loadData();
        if (list.size() != 0) {
            listCommand();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("You: ");
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                chatCycle(command);
            } catch (Exception e) {
                PixlPrint("Uh oh! " + e.getMessage(), Values.COLOR_RED);
            }

            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // End program
        saveData();
        PixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
    }
}
