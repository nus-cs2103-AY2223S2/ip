import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = " _____  _               _____   _\n"
                + "|  ___|| |     _    _  |  ___| | |  _\n"
                + "| |    | |    | |  | | | |     | |/ /\n"
                + "| |___ | |___ | |__| | | |___  |    \\\n"
                + "|_____||_____||______| |_____| |_| \\_\\\n";
        System.out.println(logo);
        System.out.println("Howdy! I'm Cluck!\n" +
                "What can I cluck-a-doodle-do for you?\n");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Buh-cluck, see ya!");
                break;
            }
            System.out.println(input);
        }
    }
}
