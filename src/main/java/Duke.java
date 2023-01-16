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
        ArrayList<String> list = new ArrayList<>();
        System.out.println(logo);
        Duke.greet();
        String instct = text.nextLine();
        while (!instct.equals("bye")){
            System.out.println((UNDERLINE));
                if (instct.equals("list") ) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("\t" + (i + 1) + "." + "\t" + list.get(i));
                    }
                } else {
                    System.out.println("\t" + "added: " + instct);
                    list.add(instct);
                }

            System.out.println((UNDERLINE));
            instct = text.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
