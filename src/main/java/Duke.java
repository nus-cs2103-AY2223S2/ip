import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private static Task[] storage = new Task[100];
    private static int pointer = 0;
    public static void main(String[] args) {
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
        String input = sc.nextLine();

        while(true){
            if (input.equalsIgnoreCase("bye")) {
                bye();
            } else if (input.equalsIgnoreCase("list")) {
                list();
                input = sc.nextLine();
            } else if (input.toLowerCase().contains("deadline") &&
            input.toLowerCase().contains("/by")) {
                deadline(input);
                input = sc.nextLine();
            } else if (input.toLowerCase().contains("todo")) {
                todo(input);
                input = sc.nextLine();
            } else if (input.toLowerCase().contains("event") &&
            input.toLowerCase().contains("/from") &&
            input.toLowerCase().contains("/to")) {
                events(input);
                input = sc.nextLine();
            } else {
                if (Pattern.compile("\\D+.\\d+").matcher(input).find()){
                    String[] strings = input.split(" ");
                    int index = Integer.parseInt(strings[1]);
                    if (strings[0].equals("mark") && index < pointer){
                        storage[index - 1].markAsDone();
                        System.out.println(
                                "_____________________________________\n"
                                        + "Nice! I've marked this task as done\n"
                                        + " " + storage[index - 1].toString() +"\n"
                                        + "_____________________________________\n"
                        );
                    } else if (strings[0].equals("unmark") && index < pointer){
                        storage[index - 1].unMark();
                        System.out.println(
                                "_____________________________________\n"
                                        + "Ok, I've marked this task as not done yet\n"
                                        + " " + storage[index].toString() +"\n"
                                        + "_____________________________________\n"
                        );
                    }
                } else {
                    System.out.println(
                            "_____________________________________\n"
                                    + "added: " + input + "\n"
                                    + "_____________________________________\n"
                    );
                    storage[pointer] = new Task(input);
                    pointer++;
                }
                input = sc.nextLine();
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
        );
        pointer++;
    }

    private static String taskCount(){
        int newCount = pointer + 1;
        String task = newCount == 1 ? " task" : " tasks";
        return "Now you have " + newCount + task + " in the list.";
    }
}

