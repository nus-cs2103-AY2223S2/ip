import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Exceptions.*;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    public static int counter = 0;
    public static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        loadTaskData();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();

            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (message.equals("list")){
                listTask();
            } else if (message.startsWith("mark") || message.startsWith("unmark")) {
                try {
                    markTask(message);
                } catch (TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Unable to save data, please restart Duke to try again");
                    System.exit(1);
                }
            } else if (message.startsWith("todo") || message.startsWith("deadline") || message.startsWith("event")) {
                try {
                    addNewTask(message);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Unable to save data, please restart Duke to try again");
                    System.exit(1);
                }
            } else if (message.startsWith("delete")) {
                try {
                    deleteTask(message);
                } catch(TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Unable to save data, please restart Duke to try again");
                    System.exit(1);
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void addNewTask(String message)
            throws TaskNoDescriptionException, NotEnoughArgumentsException, IOException {
        Task task;
        if (message.startsWith("todo")) {
            String info = message.substring(4).trim();
            if (info.isEmpty()) {
                throw (new TaskNoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty."));
            }
            task = new Todo(info);
        } else if (message.startsWith("deadline")) {
            String info = message.substring(8).trim();
            if (info.isEmpty()) {
                throw(new TaskNoDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty."));
            }

            try {
                String[] infoParts = info.split("/", 2);
                String description = infoParts[0], by = infoParts[1].substring(2).trim();
                task = new Deadline(description, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw(new NotEnoughArgumentsException("☹ OOPS!!! Deadline requires a date after the description."));
            }
        } else if (message.startsWith("event")) {
            String info = message.substring(5).trim();
            if (info.isEmpty()) {
                throw (new TaskNoDescriptionException("☹ OOPS!!! The description of an event cannot be empty."));
            }

            try {
                String[] infoParts = info.split("/", 3);
                String description = infoParts[0],
                       from = infoParts[1].substring(4).trim(),
                       to = infoParts[2].substring(2).trim();

                task = new Event(description, from , to);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw(new NotEnoughArgumentsException("☹ OOPS!!! Event requires a start time and an end time."));
            }
        } else {
            // this should only happen when addNewTask is being called in the wrong place
            throw(new TaskNoDescriptionException("There is an error in the code. This message does not belong here"));
        }
        list.add(task);
        saveTaskData();
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + counter + " task in the list");
    }

    public static void markTask(String message) throws TaskNotFoundException, IOException{
        int taskNumber = Integer.parseInt(message.split(" ")[1]);
        if (taskNumber > counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }

        Task currTask = list.get(taskNumber - 1);
        if (message.startsWith("mark")) {
            currTask.setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + currTask);
        } else {
            currTask.setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("    " + currTask);
        }
        saveTaskData();
    }

    public static void deleteTask(String message) throws TaskNotFoundException, IOException{
        int taskNumber = Integer.parseInt(message.split(" ")[1]);
        if (taskNumber > counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }

        Task removedTask = list.remove(taskNumber - 1);
        counter--;
        saveTaskData();
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + removedTask);
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public static void listTask() {
        for (int i = 0; i < counter; i++) {
            int label = i + 1;
            System.out.println("    " + label + ". " + list.get(i).toString());
        }
    }

    public static void loadTaskData() {
        System.out.println("________________________________");
        System.out.println("Loading data in progress...");
        try {
            File targetFile = new File(FILE_PATH);
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
            Scanner scanner = new Scanner(targetFile);
            while (scanner.hasNext()) {
                String taskData = scanner.nextLine();
                readTaskData(taskData);
            }
            System.out.println("Data loaded successfully");
        } catch (IOException e) {
            System.out.println("An error occurred in loading existing data");
        } finally {
            System.out.println("________________________________");
        }
    }

    public static void readTaskData(String taskData) {
        String[] dataSegments = taskData.split(" / ");
        String taskType = dataSegments[0];
        String marked = dataSegments[1];
        String taskDescription = dataSegments[2];
        if (taskType.equals("T")){
            list.add(new Todo(taskDescription));
        } else if (taskType.equals("D")) {
            String[] taskInfo = taskDescription.split(" - ");
            list.add(new Deadline(taskInfo[0], taskInfo[1]));
        } else if (taskType.equals("E")) {
            String[] taskInfo = taskDescription.split("-");
            list.add(new Event(taskInfo[0], taskInfo[1], taskInfo[2]));
        }

        if (marked.equals("1")) {
            list.get(counter).setDone(true);
        }
        counter++;
    }

    public static void saveTaskData() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : list) {
            fw.write(task.toDataFormatString() + "\n");
        }
        fw.close();
    }
}
