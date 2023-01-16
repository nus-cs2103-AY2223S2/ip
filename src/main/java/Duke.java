import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<Task>();
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
                    markTask(processMarkUnmarkDel(newInput));
                } else if (command.equalsIgnoreCase("unmark")) {
                    unmarkTask(processMarkUnmarkDel(newInput));
                } else if (command.equalsIgnoreCase("todo")) {
                    addTodo(newInput.split("todo", 2)[1]);
                } else if (command.equalsIgnoreCase("deadline")) {
                    processDeadline(newInput);
                } else if (command.equalsIgnoreCase("event")) {
                    processEvent(newInput);
                } else if (command.equalsIgnoreCase("delete")) {
                    delete(processMarkUnmarkDel(newInput));
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
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newTodo, tasks.size())));
    }

    public static int processMarkUnmarkDel(String input) throws DukeException{
        String[] parsedInput = input.split(" ");
        if(parsedInput.length != 2) {
            throw new DukeException("index of task to delete is missing");
        }
        try{
            int index = Integer.parseInt(parsedInput[1]);
            if(index > tasks.size()) {
                throw new DukeException("invalid task index");
            }
            return index - 1;
        } catch(NumberFormatException n) {
            throw new DukeException(n.getMessage());
        }
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

    public static void addDeadline(String description, String by){
        Deadline newDd = new Deadline(description, by);
        tasks.add(newDd);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newDd, tasks.size())));
    }

    public static void addEvent(String description, String from, String to) {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newEvent, tasks.size())));
    }

    public static void showList(){
        int len = tasks.size();
        if(len == 0){
            System.out.println(makeOutput(""));
            return;
        }
        String result = "Here are the tasks in your list:\n";
        for(int i = 0; i < len - 1; i ++){
            result += (i + 1) + "." + tasks.get(i) + "\n";
        }
        result += len + "." + tasks.get(len - 1);
        System.out.println(makeOutput(result));
    }

    public static void markTask(int taskNo){
        tasks.get(taskNo).mark();
        String result = "Nice! I've marked this task as done:\n";
        result += tasks.get(taskNo);
        System.out.println(makeOutput(result));
    }

    public static void unmarkTask(int taskNo){
        tasks.get(taskNo).unmark();
        String result = "OK, I've marked this task as not done yet:\n";
        result += tasks.get(taskNo);
        System.out.println(makeOutput(result));
    }

    public static void delete(int taskNo){
        Task deleted = tasks.remove(taskNo);
        System.out.println(makeOutput(String.format("Noted. I've removed this task:\n%s\n" +
                "Now you have %d tasks in the list.", deleted, tasks.size())));
    }
}
