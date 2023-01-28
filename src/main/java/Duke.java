import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static String projName = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    ArrayList<Task> taskList = new ArrayList<>();
    private final static String SAVED_PATH = "data/tasks.txt";

    private void addTask(Task task, String name) {
        taskList.add(task);
        System.out.println("Item added: " + name);
    }

    public boolean readInput(String input) throws DukeException, IOException {
        String firstInput = input.split(" ")[0];
        LocalDate now = LocalDate.now();

        try {
            switch (firstInput) {
            case "list":
                System.out.println("Here are the tasks you asked for!");
                for (int i = 0; i < taskList.size(); i += 1) {
                    int currItem = i + 1;
                    System.out.println(currItem + ": " + taskList.get(i));
                }
                System.out.println("You now have " + taskList.size() + " items in your list.");
                return true;

            case "bye":
                System.out.println("It was a pleasure to help, goodbye!");
                return false;

            case "mark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Mark? Mark what?");
                }
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task selectedTask = taskList.get(taskIndex);
                    System.out.println("Done! I've marked this task as done :D");
                    selectedTask.check();
                    System.out.println(selectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }

            case "unmark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Unmark? Unmark what?");
                }
                try {
                    int untaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task unselectedTask = taskList.get(untaskIndex);
                    System.out.println("This task is apparently not done huh D:");
                    unselectedTask.unCheck();
                    System.out.println(unselectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }

            case "todo":
                try {
                    String todoTaskName = input.substring(5);
                    TodoTask todoTask = new TodoTask(todoTaskName);
                    addTask(todoTask, todoTaskName);
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Oops, you can't enter an empty task!");
                }

            case "deadline":
                String deadlineDetails = input.substring(9);
                if (deadlineDetails.split(" /by ").length < 2) {
                    throw new DukeException("Wait a minute, you're missing something! Could be the name or date...");
                }
                String deadlineName = deadlineDetails.split(" /by ")[0];
                String deadlineDateStr = deadlineDetails.split(" /by ")[1];
                LocalDate deadlineDate = LocalDate.parse(deadlineDateStr);
                // make sure date not before curr date
                if (deadlineDate.isBefore(now)) {
                    throw new DukeException("Wait! Time travelling is not in my kit!");
                }
                DeadlineTask deadlineTask = new DeadlineTask(deadlineName, deadlineDate);
                addTask(deadlineTask, deadlineName);
                return true;

            case "event":
                String eventDetails = input.substring(6);
                if (eventDetails.split(" /from ").length < 2 || eventDetails.split(" /to ").length < 2) {
                    throw new DukeException("Hold up, you might be missing something here buddy!");
                }
                String eventName = eventDetails.split(" /from ")[0];
                String eventDate = eventDetails.split(" /from ")[1];
                String eventStartStr = eventDate.split(" /to ")[0];
                String eventEndStr = eventDate.split(" /to ")[1];
                LocalDate eventStart = LocalDate.parse(eventStartStr);
                LocalDate eventEnd = LocalDate.parse(eventEndStr);
                if (eventStart.isBefore(now) || eventEnd.isBefore(now)) {
                    throw new DukeException("Wait! Time travelling is not in my kit!");
                }
                if (eventStart.isAfter(eventEnd)) {
                    throw new DukeException("Ohhh I wasn't aware time travels backwards for you :O");
                }
                EventTask eventTask = new EventTask(eventName, eventStart, eventEnd);
                addTask(eventTask, eventName);
                return true;

            case "delete":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Delete? Delete what?");
                }
                try {
                    int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task deleteTask = taskList.get(deleteIndex);
                    System.out.println("Done! " + deleteTask + " has been deleted for good.");
                    taskList.remove(deleteIndex);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }
            case "save":
                File f = new File(SAVED_PATH);
                if (!f.getParentFile().exists()) { //check if directory exists, else make one
                    if (!f.getParentFile().mkdirs()) {
                        throw new DukeException("Directories can't be made? Or did something go wrong...");
                    }
                }
                if (!f.exists()) {
                    if (!f.createNewFile()) {
                        throw new DukeException("This file both doesn't exist and cannot be made D:");
                    }
                }
                FileWriter fileWriter = new FileWriter(f);
                for (Task task : taskList) {
                    fileWriter.write(task.toString());
                    fileWriter.write(System.lineSeparator());
                }
                fileWriter.close();
                return true;
            default:
                throw new DukeException("Oops I do not recognise this command...");
            }
        } catch (IOException io) {
            throw new DukeException("Something is up with your files it seems");
        } catch (DateTimeParseException dl) {
            throw new DukeException("Beep boop this robot can only understand dates in the form yyyy-mm-dd");
        }
    }

    public void load() {
        File f = new File(SAVED_PATH);
        try {
            if (!f.exists()) { //file didn't exist yet
                System.out.println("Oh boy a new user! What's up?");
            } else {
                System.out.println("Welcome back!");
                System.out.println("Here's the tasks you have:");
                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    Task task;
                    String currTask = scanner.nextLine();
                    String taskType = currTask.split(" ")[0];
                    String taskCompletion = currTask.substring(4,7);
                    Boolean complete = Objects.equals(taskCompletion, "[X]");
                    String taskDetails;
                    if (complete) {
                        taskDetails = currTask.split(" \\[X] ")[1];
                    } else {
                        taskDetails = currTask.split(" \\[ ] ")[1];
                    }
                    switch (taskType){
                    case "[T]":
                        task = new TodoTask(taskDetails, complete);
                        break;
                    case "[D]":
                        String deadlineName = taskDetails.split(" \\(by: ")[0];
                        String date = taskDetails.split(" \\(by: ")[1].split("\\)")[0];
                        LocalDate deadLine = LocalDate.parse(date);
                        task = new DeadlineTask(deadlineName, deadLine, complete);
                        break;
                    case "[E]":
                        String eventName = taskDetails.split(" \\(from:")[0];
                        String eventPeriod = taskDetails.split("\\(from: ")[1];
                        String startStr = eventPeriod.split(" to: ")[0];
                        String endStr = eventPeriod.split(" to: ")[1].split("\\)")[0];
                        LocalDate start = LocalDate.parse(startStr);
                        LocalDate end = LocalDate.parse(endStr);
                        task = new EventTask(eventName, start, end, complete);
                        break;
                    default:
                        task = new Task(taskDetails);
                    }
                    taskList.add(task);
                }
                for (int i = 0; i < taskList.size(); i += 1) {
                    int currItem = i + 1;
                    System.out.println(currItem + ": " + taskList.get(i));
                }
                System.out.println("How might I help you today?");
            }
        } catch (IOException io) {
            System.out.println("Haha noob");
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        System.out.println("Yo! The name is\n" + projName);

        Duke duke = new Duke();
        duke.load();
        Scanner scanner = new Scanner(System.in);

        boolean cont = true;

        while (cont) {
            String input = scanner.nextLine();
            try {
                cont = duke.readInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
