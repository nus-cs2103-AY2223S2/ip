import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";
    private static List<Task> list = new ArrayList<Task>();


    private static void greet() {
        System.out.println("Hello! I'm Mash \nWhat can I do for you?");
    }

    private static void getList() {
        int counter = 1;
        for (Object task : list) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
    }

    private static void createTask(String[] parts) throws EmptyDescriptionException {

        String taskType = parts[0];

        if (parts.length == 1) {
            throw new EmptyDescriptionException();
        }

        switch(taskType) {

            case "todo":
                String todoDescription = parts[1];
                Task t = new Todo(todoDescription);
                list.add(t);
                System.out.println("New todo added: " + todoDescription);
                break;

            case "deadline":
                //split into descrpition and time
                String part2[] = parts[1].split("/by:", 2);

                String deadlineDescription = part2[0];
                String time = part2[1].trim();

                //turn String date into LocalDate object date
                LocalDate date = LocalDate.parse(time);
                Task d = new Deadline(deadlineDescription, date);
                list.add(d);
                System.out.println("New deadline added: " + deadlineDescription);
                break;

            case "event":
                //split into descrpition and time
                String part3[] = parts[1].split("/from:", 2);

                String eventDescription = part3[0];

                String timearr[] = part3[1].split("/to:", 2);
                String from = timearr[0].trim();
                String to = timearr[1].trim();

                LocalDate fromdate = LocalDate.parse(from);
                LocalDate todate = LocalDate.parse(to);

                Task e  = new Event(eventDescription, fromdate, todate);
                list.add(e);
                System.out.println("New event added: " + eventDescription);
                break;

        }
    }

    private static void markTask(String[] parts) throws TaskDoesNotExistException {
        //converting the index from String to Int
        int indexInt = Integer.parseInt(parts[1]) - 1;

        //in case the Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > list.size() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        Task t = list.get(indexInt);
        t.markDone();
        System.out.println("Nice! I've marked this task as done:\n" + t.toString());
    }

    private static void unmarkTask(String[] parts) throws TaskDoesNotExistException {
        //converting the index from String to Int
        int indexInt = Integer.parseInt(parts[1]) - 1;

        //in case the Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > list.size() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        Task t = list.get(indexInt);
        t.markUndone();
        System.out.println("Okay.. I've unmarked this task:\n" + t.toString());
    }

    private static void deleteTask(String[] parts) {
        int indexInt = Integer.parseInt(parts[1]) - 1;
        //in case the Task index to mark exceeds current number of Tasks or neg number
            if (indexInt + 1 > list.size() || indexInt < 0) {
                System.out.println("Task does not exist");
            } else {
                String task = list.get(indexInt).toString();
                list.remove(indexInt);
                System.out.println("Noted. I've removed this task: \n"
                        + task
                        + "\nNow you have " + list.size() + " tasks in the list.");
            }
    }

    private static void getTaskType() {
        Scanner myObj = new Scanner(System.in);
        String input;
        input = myObj.nextLine();

        while (!input.equals("bye")) {
            try {
                //print List
                if (input.equals("list")) {
                    getList();
                    input = myObj.nextLine();
                } else {
                    //split into 2, first part is task type, second part is instruction
                    String[] parts = input.split(" ", 2);
                    String taskType = parts[0];

                    switch (taskType) {
                        case "mark":
                            Duke.markTask(parts);
                            break;
                        case "unmark":
                            Duke.unmarkTask(parts);
                            break;
                        case "delete":
                            Duke.deleteTask(parts);
                            break;
                        case "todo":
                        case "deadline":
                        case "event":
                            Duke.createTask(parts);
                            break;
                    }
                }
            } catch (TaskDoesNotExistException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            input = myObj.nextLine();
        }


        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {

        Duke.greet();
        Duke.getTaskType();

    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


