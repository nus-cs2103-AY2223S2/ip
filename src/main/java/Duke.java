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
            } else {
                Pattern pattern = Pattern.compile("\\D{4,7}.\\d+");
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()){
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
                    input = sc.nextLine();
                }
                System.out.println(
                        "_____________________________________\n"
                        + "added: " + input + "\n"
                        + "_____________________________________\n"
                );
                storage[pointer] = new Task(input);
                pointer++;
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
}

