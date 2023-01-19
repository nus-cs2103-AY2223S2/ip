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
        System.out.println("    ____________________________________________________________");
        System.out.println("    Howdy! I'm Cluck!\n" +
                "    What can I cluck-a-doodle-do for you?\n");
        System.out.println("    ____________________________________________________________");

        List<String> toDoList = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Buh-cluck, see ya!");
                System.out.println("    ____________________________________________________________");

                break;
            }
            else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < toDoList.size(); i++) {

                    System.out.println("    " + (i+1) + ": " + toDoList.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else {
                toDoList.add(input);
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");

            }
        }
    }
}
