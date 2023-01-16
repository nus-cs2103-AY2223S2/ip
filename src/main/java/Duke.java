import java.util.*;
import java.io.*;

public class Duke {
    public static String HOR_BAR = "    ✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";
    public static ArrayList<String> listOfTasks = new ArrayList<String>();

    public static void initialise() {
        /* Greetings from application */
        System.out.println(HOR_BAR);
        String logo = "    ૮ ˶ᵔ ᵕ ᵔ˶ ა";
        System.out.println("    Hey there! I'm Berry the Bunny~\n" + logo + "\n"
                + "    What are you looking to plan today?");
        System.out.println(HOR_BAR);
    }

    /**
     * This method takes in a command and repeats it back.
     * @param commmand
     *          Command to be repeated back to interface.
     */
    private static void addText(String command) {
        listOfTasks.add(command);
        System.out.println(HOR_BAR + "\n    " + "added: " + command + "\n" + HOR_BAR);
    }

    private static void listTasks() {
        int counter = 1;
        System.out.println(HOR_BAR);
        for (String task : listOfTasks) {
            System.out.println("    " + counter++ + ". " + task);
        }
        System.out.println(HOR_BAR);
    }

    public static void main(String[] args) throws IOException {
        /* Initialise BufferedReader */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
           initialise();
           while (true) {
               String command = br.readLine();
               if (command.equals("bye")) {
                   System.out.println(HOR_BAR + "\n" + "    Bye! Please come back again ><!");
                   break;
               } else if  (command.equals("list")) {
                   listTasks();
               } else {
                   addText(command);
               }
           }
        } finally {
            br.close();
        }
    }
}

