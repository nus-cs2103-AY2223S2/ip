import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private static final int cap = 100;
    private static ArrayList<Task> storage = new ArrayList<>(cap);
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "_____________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "_____________________________________\n";
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        String input;

        while(true){
            try {
                input = sc.nextLine();
                String lcInput = input.toLowerCase();
                String[] inputs = input.split(" ");
                if (lcInput.contains("bye")) {
                    if (inputs.length != 1) {
                        throw new DukeException("Command does not take in extra arguments!");
                    } else {
                        bye();
                    }
                } else if (lcInput.contains("list")) {
                    if (inputs.length != 1) {
                        throw new DukeException("Command does not take in extra arguments!");
                    } else {
                        list();
                    }
                } else if (lcInput.contains("deadline")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("What is the deadline task????");
                    } else if (!lcInput.contains("/by")) {
                        throw new DukeException("Put in the deadline of your task Please!");
                    } else {
                        deadline(input);
                    }
                } else if (lcInput.contains("todo")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("What is the todo task????");
                    } else {
                        todo(input);
                    }
                } else if (lcInput.contains("event")) {
                    if (!input.contains("/from") && !input.contains("/to")){
                        throw new DukeException("Period not specified!");
                    } else if (inputs.length <= 1){
                        throw new DukeException("What is the event task????");
                    }
                    events(input);
                } else if (Pattern.compile("\\D+.\\d+").matcher(input).find()){
                    int index = Integer.parseInt(inputs[1]);
                    if (inputs[0].equals("mark")) {
                        mark(inputs, index);
                    } else if (inputs[0].equals("unmark")) {
                        unmark(index);
                    } else if (inputs[0].equals("delete")) {
                        delete(index - 1);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                System.out.println(
                        "_____________________________________\n"
                        + e.errorMessage + "\n"
                        + "_____________________________________\n"
                );
                continue;
            }

        }
    }

    private static void bye(){
        System.out.println(
                "_____________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "_____________________________________\n"
        );
        System.exit(0);
    }

    private static void list(){
        System.out.println("_____________________________________\n");
        for (Task task: storage){
            System.out.println(
                    storage.indexOf(task) + ". " + task.toString() + "\n"
            );
        }
        System.out.println("_____________________________________\n");
    }

    private static void deadline(String rawInput){
        String desc = rawInput.substring(
                rawInput.indexOf("deadline") + "deadline ".length(),
                rawInput.indexOf("/by")
        );

        String by = rawInput.substring(
                rawInput.indexOf("/by") + "/by ".length()
        );

        Task taskAdd = new Deadline(desc, by);
        storage.add(taskAdd);
        System.out.println("_____________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + taskAdd.toString() +"\n"
                + taskCount() + "\n"
                + "_____________________________________\n"
        );
    }

    private static void events(String rawInput){
        String desc = rawInput.substring(
                rawInput.indexOf("events") + "events ".length(),
                rawInput.indexOf("/from")
        );

        String from = rawInput.substring(
                rawInput.indexOf("/from") + "/from ".length(),
                rawInput.indexOf("/to")
        );

        String to = rawInput.substring(
                rawInput.indexOf("/to") + "/to ".length()
        );

        Task taskAdd = new Events(desc, from, to);
        storage.add(taskAdd);
        System.out.println("_____________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + taskAdd.toString() +"\n"
                + taskCount() + "\n"
                + "_____________________________________\n"
        );
    }

    private static void todo(String rawInput){
        String desc = rawInput.substring(
                rawInput.indexOf("todo") + "todo ".length()
        );

        Task taskAdd = new ToDo(desc);
        storage.add(taskAdd);
        System.out.println("_____________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + taskAdd.toString() +"\n"
                + taskCount() + "\n"
                + "_____________________________________\n"
        );
    }

    private static String taskCount(){
        int newCount = storage.size();
        String task = newCount == 1 ? " task" : " tasks";
        return "Now you have " + newCount + task + " in the list.";
    }

    private static void delete(int index) throws DukeException{
        try {
            Task taskRemoved = storage.remove(index);
            System.out.println(
                    "_____________________________________\n"
                            +  "Noted. I've removed this task:\n"
                            +  " " + taskRemoved.toString() + "\n"
                            + taskCount() + "\n"
                            + "_____________________________________\n"
            );
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid index given!");
        }

    }

    public static void mark(String[] args, int index) throws DukeException{
        if (args[0].equals("mark")) {
            try {
                storage.get(index - 1).markAsDone();
                System.out.println(
                        "_____________________________________\n"
                                + "Nice! I've marked this task as done\n"
                                + " " + storage.get(index - 1).toString() + "\n"
                                + "_____________________________________\n"
                );
            } catch (IndexOutOfBoundsException err) {
                throw new DukeException("Invalid index given!");
            }
        }
    }

    public static void unmark(int index) throws DukeException{
        try {
            storage.get(index - 1).unMark();
            System.out.println(
                    "_____________________________________\n"
                            + "Ok, I've marked this task as not done yet\n"
                            + " " + storage.get(index - 1).toString() + "\n"
                            + "_____________________________________\n"
            );
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Invalid index given!");
        }
    }
}

