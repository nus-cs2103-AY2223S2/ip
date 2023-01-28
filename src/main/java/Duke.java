import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        boolean isOnline = true;
        ArrayList<Task> taskList = new ArrayList<>();

        File savedTasks = new File("saved_tasks_list.txt");
        try {
            if (!savedTasks.createNewFile()) {
                // Load the exist file
                Scanner fileScanner = new Scanner(savedTasks);
                while (fileScanner.hasNextLine()) {
                    String record = fileScanner.nextLine();
                    String[] fields = record.split("\\|");
                    Task currTask;
                    if (fields[0].equals("T")) {
                        currTask = new Todo(fields[2]);
                    } else if (fields[0].equals("D")) {
                        currTask = new Deadline(fields[2], fields[3]);
                    } else if (fields[0].equals("E")) {
                        currTask = new Event(fields[2], fields[3], fields[4]);
                    } else {
                        currTask = new Task("");
                    }

                    if (fields[1].equals("1")) {
                        currTask.setDone();
                    } else {
                        currTask.setNotDone();
                    }
                    taskList.add(currTask);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isOnline) {
            try {
                isOnline = takeRequest(taskList);
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            FileWriter writer = new FileWriter("saved_tasks_list.txt");
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).toRecord() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean takeRequest(ArrayList<Task> taskList) throws DukeException {
        Scanner keyboard = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = keyboard.next();
            System.out.println("--------------------------------");
            switch (userInput) {
                case "bye": {
                    System.out.println("Bye. Have a nice Day~");
                    return false;
                }
                case "list": {
                    userInput = keyboard.nextLine().trim();
                    if (userInput.isEmpty()) {
                        if (taskList.size() == 0) {
                            System.out.println("The task list is empty.");
                        } else {
                            System.out.println("Here are the current tasks:");
                            for (int i = 0; i < taskList.size(); i++) {
                                System.out.print((i + 1) + ".");
                                System.out.println(taskList.get(i).toString());
                            }
                        }
                    } else {
                        System.out.println("Please key in [list] to check the task list.");
                    }
                    break;
                }
                case "todo": {
                    String name = keyboard.nextLine();
                    if (name.trim().isEmpty()){
                        System.out.println("Adding new todo failed");
                        System.out.println("The task name cannot be empty");
                    } else {
                        System.out.println("This task had been added!");
                        Todo t = new Todo(name);
                        System.out.println("  " + t);
                        taskList.add(t);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list");
                    }
                    break;

                }
                case "event": {
                    userInput = keyboard.nextLine();
                    if (userInput.trim().isEmpty()){
                        System.out.println("The task name cannot be empty");
                        System.out.println("Please enter the task with the format [name /ddMMyyyy HHmm /ddMMyyyy HMmm]");
                    } else {
                        try {
                            int first = userInput.indexOf('/');
                            int last = userInput.lastIndexOf('/');
                            String eventName = userInput.substring(0, first);
                            String from = userInput.substring(first + 1, last-1);
                            String to = userInput.substring(last + 1);
                            try {
                                DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
                                LocalDateTime fromDate = LocalDateTime.parse(from, dateformatter);
                                LocalDateTime toDate = LocalDateTime.parse(to, dateformatter);

                                System.out.println("This event had been added! Hope you will enjoy it :D");

                                Event t = new Event(eventName, fromDate, toDate);
                                System.out.println("  " + t);

                                taskList.add(t);
                                System.out.println("Now you have " + taskList.size() + " tasks in the list");
                            } catch (DateTimeParseException e) {
                                System.out.println("Adding new event failed");
                                System.out.println("Please enter the task with the format [name /ddMMyyyy HHmm /ddMMyyyy HMmm]");
                            }
                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println("Adding new event failed");
                            System.out.println("Please enter the event with the format [name /ddMMyyyy HHmm /ddMMyyyy HMmm]");
                        }
                    }
                    break;

                }
                case "deadline": {
                    userInput = keyboard.nextLine();
                    if (userInput.trim().isEmpty()){
                        System.out.println("The task name cannot be empty");
                        System.out.println("Please enter the task with format [name /ddmmyyyy time]");
                    } else {
                        try {
                            int dateID = userInput.indexOf('/');
                            String eventName = userInput.substring(0, dateID);
                            String details = userInput.substring(dateID + 1);

                            try {
                                DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
                                LocalDateTime by = LocalDateTime.parse(details, dateformatter);
                                System.out.println("This deadline had been added! Try to finish it early 0v0");
                                Deadline t = new Deadline(eventName, by);
                                System.out.println("  " + t);

                                taskList.add(t);
                                System.out.println("Now you have " + taskList.size() + " tasks in the list");
                            } catch (DateTimeParseException e) {
                                System.out.println("Adding new deadline failed");
                                System.out.println("Please enter the task with format [name /ddmmyyyy time]");
                            }

                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println("Adding new deadline failed");
                            System.out.println("Please enter the task with format [name /ddmmyyyy time]");
                        }
                    }
                    break;

                }
                case "mark": {
                    try {
                        int itemID = Integer.parseInt(keyboard.nextLine().trim());
                        if ((itemID) > taskList.size()) {
                            System.out.println("I cannot find task " + (itemID) + " as it exceeds the total tasks number");
                        } else {
                            System.out.println("Nice! Great job for completing this task:");
                            taskList.get(itemID - 1).setDone();
                            System.out.println((taskList.get(itemID - 1).toString()));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the task number");
                    }
                    break;

                }
                case "unmark": {
                    try {
                        int itemID = Integer.parseInt(keyboard.nextLine().trim()) - 1;
                        if ((itemID + 1) > taskList.size()) {
                            System.out.println("I cannot find task " + (itemID + 1) + " as it exceeds the total tasks number");
                        } else {
                            System.out.println("This item is marked as not done yet");
                            taskList.get(itemID).setNotDone();
                            System.out.println((taskList.get(itemID).toString()));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the task number");
                    }
                    break;
                }
                case "delete": {
                    try {
                        int itemID = Integer.parseInt(keyboard.nextLine().trim()) - 1;
                        if ((itemID + 1) > taskList.size()) {
                            System.out.println("I cannot find task " + (itemID + 1) + " as it exceeds the total tasks number");
                        } else {
                            System.out.println("This task is deleted from the list:");
                            System.out.println("  " + (taskList.get(itemID).toString()));
                            taskList.remove(itemID);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the task number");
                    }
                    break;
                }
                default: {
                    throw new DukeException("May I know what type of task this is?");
                }
            }
            System.out.println("--------------------------------");
        }
    }
}