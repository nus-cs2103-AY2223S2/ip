import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int numOfTasks = 0;
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        //open scanner to accept user input
        Scanner userInput = new Scanner(System.in);
        //greet user
        System.out.println(makeOutput(" Hello! I'm Duke\n" + " What can I do for you?"));

        while(true){
            String newInput = userInput.nextLine();
            String[] parsedInput = newInput.split(" ");
            String command = parsedInput[0];

            try {
                if (command.equalsIgnoreCase("bye")) {
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    showList();
                } else if (command.equalsIgnoreCase("mark")) {
                    markTask(Integer.parseInt(parsedInput[1]) - 1);
                } else if (command.equalsIgnoreCase("unmark")) {
                    unmarkTask(Integer.parseInt(parsedInput[1]) - 1);
                } else if (command.equalsIgnoreCase("todo")) {
                    addTodo(newInput.split("todo", 2)[1]);
                } else if (command.equalsIgnoreCase("deadline")) {
                    processDeadline(newInput);
                } else if (command.equalsIgnoreCase("event")) {
                    processEvent(newInput);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException d) {
                System.out.println(makeOutput("☹ OOPS!!! " +d.getMessage()));
            }
        }

        //exit protocol
        System.out.println(makeOutput("Bye. Hope to see you again soon!"));
        userInput.close();
    }
    public static String makeOutput(String in){
        return "____________________________________________________________\n" + in +"\n" + "____________________________________________________________";
    }

    public static void addTodo(String description) throws DukeException{
        if(description.equals("")){
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tasks[numOfTasks] = new Todo(description);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,tasks[numOfTasks], ++numOfTasks)));
    }
    public static void processDeadline(String input) throws DukeException{
        String raw = input.split("deadline", 2)[1];
        if(raw.equals("")){
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] parsed = raw.split("/by", 2);
        if(parsed.length < 2){
            throw new DukeException("When the deadline should be completed by should be specified using /by.");
        }
        addDeadline(parsed[0], parsed[1]);
    }

    public static void processEvent(String input) throws DukeException{
        String raw = input.split("event", 2)[1];
        if(raw.equals("")){
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] parsed1 = raw.split("/from", 2);
        if(parsed1.length < 2){
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        String[] parsed2 = parsed1[1].split("/to", 2);
        if(parsed2.length < 2){
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        addEvent(parsed1[0], parsed2[0], parsed2[1]);
    }

    public static void addDeadline(String description, String by) throws DukeException{
        tasks[numOfTasks] = new Deadline(description, by);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,tasks[numOfTasks], ++numOfTasks)));
    }

    public static void addEvent(String description, String from, String to) throws DukeException{
        tasks[numOfTasks] = new Event(description, from , to);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,tasks[numOfTasks], ++numOfTasks)));
    }

    public static void showList(){
        if(tasks.length == 0){
            return;
        }
        String result = "Here are the tasks in your list:\n";
        for(int i = 0; i < numOfTasks - 1; i ++){
            result += Integer.toString(i + 1) + "." + tasks[i] + "\n";
        }
        result += Integer.toString(numOfTasks) + "." + tasks[numOfTasks - 1];
        System.out.println(makeOutput(result));
    }

    public static void markTask(int taskNo){
        tasks[taskNo].mark();
        String result = "Nice! I've marked this task as done:\n";
        result += tasks[taskNo].getStatusIcon() + " " + tasks[taskNo];
        System.out.println(makeOutput(result));
    }

    public static void unmarkTask(int taskNo){
        tasks[taskNo].unmark();
        String result = "OK, I've marked this task as not done yet:\n";
        result += tasks[taskNo].getStatusIcon() + " " + tasks[taskNo];
        System.out.println(makeOutput(result));
    }

    public static void addTask(String description){
        tasks[numOfTasks] = new Task(description);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,tasks[numOfTasks], numOfTasks++)));
    }
}
