import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        System.out.println("Hello! I'm Duke. What can i do for you?");
        while (in.hasNext()) {
            String s = in.nextLine();
            String cmd = s.toLowerCase();
            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                for (int i=0; i<taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
            } else {
                taskList.add(cmd);
                System.out.println("added: " + cmd);

            }

        }
    }
}
