import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    static ArrayList<Task> taskArray = new ArrayList<>();

    private static void updateLog() {
        try {
            FileWriter taskLog = new FileWriter("data/Duke.txt");
            for (Task i : taskArray) {
                taskLog.write(i.toLog());
            }
            taskLog.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DukeException {

        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
                System.out.println("Folder created: " + dataFolder.getName());
            }
            File taskLog = new File("data/Duke.txt");
            if (!taskLog.exists()) {
                taskLog.createNewFile();
                System.out.println("File created: " + taskLog.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        /*
        String logo2 = "    ___     _____    __     ___  \n"
                     + "   /   \\   |  ___|  |  |   /   \\ \n"
                     + "  /  |  \\  |  |__   |  |  |  |  | \n"
                     + "  |     |  |  ___|  |  |  |  |  | \n"
                     + "  |__|__|  |__|     |__|   \\__/\\_\\";
         */
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello I'm Duke\n"
                + "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String instruction = input.split(" ")[0];

            switch (instruction) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 0; i < taskArray.size(); i++) {
                        int listNumber = i + 1;
                        System.out.println(listNumber + ". " + taskArray.get(i).toString());
                    }
                    System.out.println("");
                    break;
                case "mark":
                    int toMark = input.charAt(5) - 48;
                    Task toMarkTask = taskArray.get(toMark - 1);
                    toMarkTask.markTask();

                    System.out.println("Nice! I've marked this task as done:\n   "
                            + toMarkTask + "\n");
                    updateLog();
                    break;
                case "unmark":
                    int toUnMark = input.charAt(7) - 48;
                    Task toUnMarkTask = taskArray.get(toUnMark - 1);
                    toUnMarkTask.unmarkTask();

                    System.out.println("OK, I've marked this task as not done yet:\n   "
                            + toUnMarkTask + "\n");
                    updateLog();
                    break;
                case "todo":
                    try {
                        ToDo toDoTask = new ToDo(input.substring(5));
                        taskArray.add(toDoTask);

                        System.out.println("Got it. I've added this task:\n   "
                                + toDoTask
                                + "\nNow you have " + taskArray.size() + " tasks in your list\n");
                        updateLog();
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    String[] dSegments = input.split(" /");
                    String deadlineName = dSegments[0].substring(9);
                    String deadline = dSegments[1].substring(3);

                    DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime formattedDeadline = LocalDateTime.parse(deadline, deadlineFormatter);

                    Deadline deadlineTask = new Deadline(deadlineName, formattedDeadline);
                    taskArray.add(deadlineTask);

                    System.out.println("Got it. I've added this task:\n   "
                            + deadlineTask
                            + "\nNow you have " + taskArray.size() + " tasks in your list\n");
                    updateLog();
                    break;
                case "event":
                    String[] eSegments = input.split(" /");
                    String eventName = eSegments[0].substring(6);
                    String startTime = eSegments[1].substring(5);
                    String endTime = eSegments[2].substring(3);

                    DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime formattedEventStartTime = LocalDateTime.parse(startTime, eventFormatter);
                    LocalDateTime formattedEventEndTime = LocalDateTime.parse(endTime, eventFormatter);

                    Event eventTask = new Event(eventName, formattedEventStartTime, formattedEventEndTime);
                    taskArray.add(eventTask);

                    System.out.println("Got it. I've added this task:\n   "
                            + eventTask
                            + "\nNow you have " + taskArray.size() + " tasks in your list\n");
                    updateLog();
                    break;
                case "delete":
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    Task toDelete = taskArray.get(taskNumber - 1);
                    taskArray.remove(taskNumber - 1);

                    System.out.println("Got it. I've removed this task:\n   "
                            + toDelete
                            + "\nNow you have " + taskArray.size() + " tasks in your list\n");
                    updateLog();
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
