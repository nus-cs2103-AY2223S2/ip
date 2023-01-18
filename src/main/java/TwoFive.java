import java.util.ArrayList;
import java.util.Scanner;

public class TwoFive {
    //Returns string to be printed when new task is added
    public static String taskAdded(Task task) {
        return "Got it. I've added this task:\n " + task + "\n";
    }

    //Returns string containing number of tasks added
    public static String numTasksString(int numTasks) {
        return "Now you have " + numTasks + " tasks in the list";
    }

    //Ensure valid task number is provided
    public static int validateTaskNum(String input, int numTasks) throws EmptyTasknumException, InvalidTaskException, NumberFormatException {
        String[] taskNumSplit = input.split(" ");
        if (taskNumSplit.length <= 1 || taskNumSplit[1].trim().equals("")) {
            throw new EmptyTasknumException();
        } else {
            int taskNum = Integer.parseInt(taskNumSplit[1]) - 1;
            if (taskNum < 0 || taskNum >= numTasks) {
                throw new InvalidTaskException();
            } else {
                return taskNum;
            }
        }
    }

    //Ensure a valid description is provided when adding a new task
    public static String validateDescription(String input, String command) throws EmptyDescriptionException {
        String[] descriptionSplit = input.split(command);
        if (descriptionSplit.length <= 1 || descriptionSplit[1].trim().equals("")) {
            //If task description is empty
            throw new EmptyDescriptionException(command);
        }
        return descriptionSplit[1].trim();
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = "  _______            ______ _\n"
                + " |__   __|          |  ____(_)\n"
                + "    | |_      _____ | |__   ___   _____\n"
                + "    | \\ \\ /\\ / / _ \\|  __| | \\ \\ / / _ \\\n"
                + "    | |\\ V  V / (_) | |    | |\\ V /  __/\n"
                + "    |_| \\_/\\_/ \\___/|_|    |_| \\_/ \\___|\n";

        System.out.println("Hello from\n" + logo);

        String divider = "____________________________________________________________";

        //Greets users
        System.out.println(divider);
        System.out.println("In case you're still not clear, I'm TwoFive!");
        System.out.println("I'm your personal assistant chatbot");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        //Reads input from user
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();

        //Exits when user types bye
        while (!input.equals("bye")) {
            //Echos input from user
            System.out.println(divider);

            try {
                if (input.equals("list")) {
                    //List all tasks added by the user
                    System.out.println("Here are the tasks in your list:");
                    int taskIndex = 1;
                    for (Task task: tasks) {
                        System.out.println(taskIndex + ". " + task);
                        taskIndex++;
                    }
                } else if (input.contains("delete")) {
                    //Delete selected task
                    int taskNum = validateTaskNum(input, tasks.size());
                    //Ensure task number given is valid
                    Task currentTask = tasks.get(taskNum);
                    boolean deleteSuccess = tasks.remove(currentTask);
                    if (deleteSuccess) {
                        System.out.println("Noted. I've removed this task:\n " + currentTask + "\n" + numTasksString(tasks.size()));
                    }
                } else if (input.contains("unmark")) {
                    //Marks selected task as undone
                    int taskNum = validateTaskNum(input, tasks.size());
                    Task currentTask = tasks.get(taskNum);
                    boolean unmarkSuccess = currentTask.markAsUndone();
                    if (unmarkSuccess) {
                        System.out.println("OK, I've marked this task as not done yet:\n " + currentTask);
                    } else {
                        throw new TaskUndoneException();
                    }
                } else if (input.contains("mark")) {
                    //Marks selected task as done
                    int taskNum = validateTaskNum(input, tasks.size());
                    Task currentTask = tasks.get(taskNum);
                    boolean markSuccess = currentTask.markAsDone();
                    if (markSuccess) {
                        System.out.println("Nice! Congrats for completing this task:\n " + currentTask);
                    } else {
                        throw new TaskDoneException();
                    }
                } else if (input.contains("todo")){
                    //Adds a new ToDo task
                    String taskDescription = validateDescription(input, "todo");
                    ToDo newToDo = new ToDo(taskDescription);
                    //Adds new task to list of tasks
                    tasks.add(newToDo);
                    System.out.println(taskAdded(newToDo) + numTasksString(tasks.size()));
                } else if (input.contains("deadline")){
                    //Adds a new Deadline task
                    String descriptionSplit = validateDescription(input, "deadline");
                    if (!descriptionSplit.contains(("/by"))) {
                        //If /by argument not used
                        throw new MissingArgumentException("/by");
                    } else {
                        String[] deadlineSplit = descriptionSplit.split("/by");
                        if (deadlineSplit.length <= 1 || deadlineSplit[1].trim().equals("")) {
                            //If deadline is not given
                            throw new EmptyDeadlineException();
                        } else {
                            String taskDescription = deadlineSplit[0].trim();
                            String deadline = deadlineSplit[1].trim();
                            Deadline newDeadline = new Deadline(taskDescription, deadline);
                            //Adds new task to list of tasks
                            tasks.add(newDeadline);
                            System.out.println(taskAdded(newDeadline) + numTasksString(tasks.size()));
                        }
                    }
                } else if (input.contains("event")){
                    //Adds a new Event task
                    String descriptionSplit = validateDescription(input, "event");
                    if (!descriptionSplit.contains(("/from"))) {
                        //If /from argument not used
                        throw new MissingArgumentException("/from");
                    } else if (!descriptionSplit.contains(("/to"))) {
                        //If /to argument not used
                        throw new MissingArgumentException("/to");
                    } else {
                        String[] startTimeSplit = descriptionSplit.split("/from");
                        if (startTimeSplit.length <= 1 || startTimeSplit[1].trim().equals("")) {
                            //If start time is not given
                            throw new EmptyStartTimeException();
                        } else {
                            String[] endTimeSplit = startTimeSplit[1].split("/to");
                            if (endTimeSplit[0].trim().equals("")) {
                                throw new EmptyStartTimeException();
                            } else if (endTimeSplit.length <= 1 || endTimeSplit[1].trim().equals("")) {
                                //If end time is not given
                                throw new EmptyEndTimeException();
                            } else {
                                String taskDescription = startTimeSplit[0].trim();
                                String startTime = endTimeSplit[0].trim();
                                String endTime = endTimeSplit[1].trim();
                                Event newEvent = new Event(taskDescription, startTime, endTime);
                                //Adds new task to list of tasks
                                tasks.add(newEvent);
                                System.out.println(taskAdded(newEvent) + numTasksString(tasks.size()));
                            }
                        }
                    }
                } else {
                    throw new InvalidCommandException();
                }
            } catch (TwoFiveException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(":( OOPS!!! The task number provided must be a number.");
            }

            System.out.println(divider);
            input = inputScanner.nextLine();
        }

        System.out.println(divider);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
