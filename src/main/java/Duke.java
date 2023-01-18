import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private static Task[] storage = new Task[100];
    private static int pointer = 0;
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
                    } else if (!input.toLowerCase().contains("/by")) {
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
                } else {
                    if (Pattern.compile("\\D+.\\d+").matcher(input).find()) {
                        int index = Integer.parseInt(inputs[1]);
                        if (inputs[0].equals("mark") && index < pointer) {
                            storage[index - 1].markAsDone();
                            System.out.println(
                                    "_____________________________________\n"
                                            + "Nice! I've marked this task as done\n"
                                            + " " + storage[index - 1].toString() + "\n"
                                            + "_____________________________________\n"
                            );
                        } else if (inputs[0].equals("unmark") && index < pointer) {
                            storage[index - 1].unMark();
                            System.out.println(
                                    "_____________________________________\n"
                                            + "Ok, I've marked this task as not done yet\n"
                                            + " " + storage[index].toString() + "\n"
                                            + "_____________________________________\n"
                            );
                        } else {
                            throw new DukeException("Incorrect index or incorrect command given");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
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
        for (int i = 0; i < pointer; i++){
            int index = i + 1;
            System.out.println(
                    index + ". " + storage[i].toString() + "\n"
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

        storage[pointer] = new Deadline(desc, by);
        System.out.println("_____________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + storage[pointer].toString() +"\n"
                + taskCount() + "\n"
                + "_____________________________________\n"
        );
        pointer++;
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

        storage[pointer] = new Events(desc, from, to);
        System.out.println("_____________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + storage[pointer].toString() +"\n"
                + taskCount() + "\n"
                + "_____________________________________\n"
        );
        pointer++;
    }

    private static void todo(String rawInput){
        String desc = rawInput.substring(
                rawInput.indexOf("todo") + "todo ".length()
        );

        storage[pointer] = new ToDo(desc);
        System.out.println("_____________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + storage[pointer].toString() +"\n"
                + taskCount() + "\n"
                + "_____________________________________\n"
        );
        pointer++;
    }

    private static String taskCount(){
        int newCount = pointer + 1;
        String task = newCount == 1 ? " task" : " tasks";
        return "Now you have " + newCount + task + " in the list.";
    }
}

