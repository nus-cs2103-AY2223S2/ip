
import java.util.Scanner;

public class Duke {
    private static String[] storage = new String[100];
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
            } else if (input.equalsIgnoreCase("list")){
                list();
                input = sc.nextLine();
            } else {
                System.out.println(
                        "_____________________________________\n"
                        + "added: " + input + "\n"
                        + "_____________________________________\n"
                );
                storage[pointer] = input;
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
                    index + ". " + storage[i] + "\n"
            );
        }
        System.out.println("_____________________________________\n");
    }
}
