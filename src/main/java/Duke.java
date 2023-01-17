import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String UNDERLINE = "_________________________________";


    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private static void greet() {

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println((UNDERLINE));
    }


    public static void main(String[] args) {
        Scanner text = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println(logo);
        Duke.greet();
        String instct = text.nextLine();
        // parse the command
        System.out.println(instct.split(" a")[0]);
        while (!instct.equals("bye")){
            System.out.println((UNDERLINE));
            if (instct.equals("list") ) {
                System.out.println("\t" + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
                }
            }
            else if (instct.split(" ")[0].equals("mark")) {

                int numbering = Integer.parseInt(instct.split(" ")[1]) - 1;
                System.out.println("\t"+ "Nice! I've marked this task as done:");
                list.get(numbering).markDone();
                System.out.println("\t" +  list.get(numbering).toString());
            }
            else if (instct.split(" ")[0].equals("unmark")){
                int numbering = Integer.parseInt(instct.split(" ")[1]) - 1;
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                list.get(numbering).markNotDone();
                System.out.println("\t" + list.get(numbering).toString());
            }

            else {
                System.out.println("\t" + "added: " + instct);
                list.add(new Task(instct));
            }

            System.out.println((UNDERLINE));
            instct = text.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
