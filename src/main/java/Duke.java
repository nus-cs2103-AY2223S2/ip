import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> dukeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                
        Scanner sc = new Scanner(System.in);
        String line = "init";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                if (line.equals("list")) {
                    displayMsg(outputList());
                } else {
                    dukeList.add(line);
                    displayMsg("added: " + line);
                }   
            }
            line = sc.nextLine();
        }
        sc.close();
        displayMsg("Bye. Hope to see you again soon!");
    }


    public static String outputList() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < dukeList.size(); index++) {
            result.append((index + 1) + ". " + dukeList.get(index) + "\n");
        }
        return result.toString();
    }

    public static void displayMsg(String msg) {
        System.out.println(wrapMessageBorder(msg));
    }

    public static String wrapMessageBorder(String msg) {
        String border = "____________________________________________________________";
        return border + "\n" + msg + "\n" + border;
    }
}
