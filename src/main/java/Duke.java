import java.util.Scanner;
import java.util.ArrayList;;

public class Duke {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;
    private static boolean startDuke = true;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        
        while (startDuke) {

          
            String[] userInput = sc.nextLine().split(" ", 2);
            
            try {
                switch (userInput[0]) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        sc.close();
                        startDuke = false;
                        break;
    
                    case "list":
                        if (taskCount == 0) {
                            System.out.println("You have no tasks");
                        }
                        else {
                            System.out.println("Here are the tasks in your list");
                            for (int i = 0; i < taskCount; i++) {
                                System.out.printf("%d. %s \n", i + 1, taskList.get(i));
                            }
                        }
                        break;
                    
                    case "mark":
                        markTask(Integer.valueOf(userInput[1]) - 1);
                        break;
                    
                    case "unmark":
                        unmarkTask(Integer.valueOf(userInput[1]) - 1);
                        break;
    
                    case "todo":
                        if (userInput.length < 2) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            addTaskToList("todo", userInput[1]);
                        }
                        break;
    
                    case "deadline": 
                        if (userInput.length < 2) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            addTaskToList("deadline", userInput[1]);
                        }
                        break;
    
                    case "event":
                        if (userInput.length < 2) {
                            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                        } else {
                            addTaskToList("event", userInput[1]);
                        }
                        break;

                    case "delete":
                        if (userInput.length < 2) {
                            throw new DukeException("Missing taskID!");
                        } else {
                            deleteTask(Integer.valueOf(userInput[1]) - 1);
                        }
                        break;
                        
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } 
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public static void addTaskToList(String type, String userInput) throws DukeException {
        switch (type) {
            case "todo":
                Task newToDo = new Todo(userInput);
                taskCount++;
                taskList.add(newToDo);
                System.out.println("Got it. I've added this task:");
                System.out.println(newToDo);
                break;

            case "deadline":
                String[] deadlineFormatter = userInput.split(" /by ");
                if (deadlineFormatter.length < 2 ) {
                    throw new DukeException("Either the description or deadline of the task is missing");
                }
                else {
                    Task newDeadLineTask = new Deadline(deadlineFormatter[0], deadlineFormatter[1]);
                    taskCount++;
                    taskList.add(newDeadLineTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadLineTask);
                }
                break;

            case "event":
                String[] eventFormatter = userInput.split("/");
                if (eventFormatter.length < 3 ) {
                    throw new DukeException("Either the description or dates (from/to) of the task is missing");
                }
                else {
                    Task newEventTask = new Event(eventFormatter[0], eventFormatter[1], eventFormatter[2]);
                    taskCount++;
                    taskList.add(newEventTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEventTask);
                }
                break;
        }
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    public static void markTask(int taskID) {
        if (taskCount > taskID && taskCount > 0) {
            System.out.println("Nice! I've marked this task as done:");
            Task currentTask = taskList.get(taskID);
            currentTask.mark();
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid taskID entered!");
        }
    }

    public static void unmarkTask(int taskID) {
        if (taskCount > taskID && taskCount > 0) {
            System.out.println("OK, I've marked this task as not done yet:");
            Task currentTask = taskList.get(taskID);
            currentTask.unmark();
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid taskID entered!");
        }
    }

    public static void deleteTask(int taskID) {
        if (taskCount > taskID && taskCount > 0) {
            System.out.println("Noted. I've removed this task:");
            Task currentTask = taskList.get(taskID);
            taskList.remove(taskID);
            taskCount--;
            System.out.println(currentTask);
            System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println("Invalid taskID entered!");
        }      
    }

}
