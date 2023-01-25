import java.util.*;
<<<<<<< HEAD
import java.time.LocalDate;
=======
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
>>>>>>> master
public class Duke {
    private static String fileDestination = "data/duke.txt";
    private static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) throws DukeException {
        Scanner userInput = new Scanner(System.in);
        File file = new File(fileDestination);
        readSavedFile(file); // loads saved strings in duke.txt to tasklist
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        String input;

        while (!(input = userInput.nextLine()).equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("____________________");
                    if (taskList.size() < 1) {
                        System.out.println("You currently have no task.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            Task currTask = taskList.get(i);
                            int taskIndex = i + 1;
                            System.out.println(taskIndex + ". " + currTask);
                        }
                    }
                    System.out.println("____________________");
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    int indexToDelete = Integer.parseInt(input.substring(7)) - 1;
                    if (indexToDelete < taskList.size()) {
                        taskRemovedMessage(taskList.get(indexToDelete),taskList.size() - 1);
                        taskList.remove(indexToDelete);
                        saveTaskListToStorage(file);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                    int indexToMark = Integer.parseInt(input.substring(5)) - 1;
                    if (indexToMark < taskList.size()) {
                        Task toMark = taskList.get(indexToMark);
                        toMark.markAsDone();
                        saveTaskListToStorage(file);
                        customMessage("Nice! I've marked this task as done:\n" + toMark);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                    int indexToUnmark = Integer.parseInt(input.substring(7)) - 1;
                    if (indexToUnmark < taskList.size()) {
                        Task toUnmark = taskList.get(indexToUnmark);
                        toUnmark.markAsUndone();
                        saveTaskListToStorage(file);
                        customMessage("OK, I've marked this task as not done yet:\n" + toUnmark);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else {
                    typeOfTask(input, taskList);
                    saveTaskListToStorage(file);
                }
            } catch (DukeException dukeException) {
                customMessage(dukeException.getMessage());
            }
        }
        customMessage("Bye. Hope to see you again soon!");
    }
    private static void customMessage(String message) {
        System.out.println("____________________");
        System.out.println(message);
        System.out.println("____________________");
    }

    private static void taskAddedMessage(Task task, int sizeOfList) {
        System.out.println("____________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + sizeOfList + " task" + (sizeOfList > 1 ? "s" : "") +" in the list.");
        System.out.println("____________________");
    }
    private static void taskRemovedMessage(Task task, int sizeOfList) {
        System.out.println("____________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + sizeOfList + " task" + (sizeOfList > 1 ? "s" : "") +" in the list.");
        System.out.println("____________________");
    }
    private static void typeOfTask(String input, List<Task> lst) throws DukeException {
        if (input.length() >= 4 && input.substring(0,4).equals("todo")) {
            String check = input.replaceAll("\\s", "");
            if (check.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task newTask = new ToDo(input.substring(5, input.length()));
            lst.add(newTask);
            taskAddedMessage(newTask, lst.size());
        } else if (input.length() >= 5 && input.substring(0,5).equals("event")) {
            String check = input.replaceAll("\\s", "");
            if (check.equals("event")) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            String[] str = input.substring(6).split("/");
            Task newTask = new Event(str[0].substring(0, str[0].length() - 1)
                    , LocalDate.parse(str[1].substring(5, str[1].length() - 1))
                    , LocalDate.parse(str[2].substring(3)));
            lst.add(newTask);
            taskAddedMessage(newTask, lst.size());
        } else if (input.length() >= 8 && input.substring(0,8).equals("deadline")) {
            String check = input.replaceAll("\\s", "");
            if (check.equals("deadline")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] str = input.substring(9).split("/");
            Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1)
                    , LocalDate.parse(str[1].substring(3)));
            lst.add(newTask);
            taskAddedMessage(newTask, lst.size());
        } else {
            throw new DukeException("I'm sorry, I don't know what that means!");
        }
    }

    private static void readSavedFile(File file) {
        try {
            Scanner myReader = new Scanner(file);
            String data;
            while (myReader.hasNextLine()) {
                taskList.add(parseStringToTask(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static Task parseStringToTask(String string) {
        String[] arr = string.split(",");

        if (arr[0].equals("T")) {
            Task t = new ToDo(arr[2], arr[1].equals("1"));
            return t;
        } else if (arr[0].equals("D")) {
            Task t = new Deadline(arr[2], arr[1].equals("1"), LocalDate.parse(arr[3]));
            return t;
        } else {
            Task t = new Event(arr[2], arr[1].equals("1"), LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
            return t;
        }
    }
    private static void saveTaskListToStorage(File file) {
        try {
            FileWriter myWriter = new FileWriter(file);
            // this truncates the duke.txt to size 0
            for (int i = 0; i < taskList.size(); i++) {
                myWriter.write(taskList.get(i).toStorableString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
