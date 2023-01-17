import java.util.*;

public class Duke {

    static String line = "      -----------------------------------------------------------------";
    static ArrayList<String> list = new ArrayList<String>(100);
    public static void main(String[] args) {
        greet();
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        //echo

        while(true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("        byebye! Have an exquisite day, cutiepatootie");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                for(int i = 0; i < list.size(); i++) {
                    System.out.println("        " + (i + 1) + ". " + list.get(i));
                }
                System.out.println(line);
            } else {
                //echoes input
                System.out.println("\n" + line);
                echo(input);
                System.out.println(line);
            }
        }
        sc.close();
        System.out.println(line);
    }

    public static void echo(String name) {
        System.out.println();
        System.out.println("        added: " + name);
        list.add(name);

    }

    public static void addlist(String name) {
        System.out.println();
        System.out.println(name);

    }

    static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("\nHello! I'm Oli\n" + "What can I do for you?");
    }

}
