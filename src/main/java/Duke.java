import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String separator = "____________________________________________________________";

        String openingMessage = separator + "\nHello! I'm Duke\n" + "What can I do for you?\n" + separator;
        System.out.println(openingMessage);

        while(s.hasNext()) {
            String command = s.nextLine();
            if(command.equals("bye")) {
                String closingMessage = separator + "\nBye. Hope to see you again soon!\n" + separator;
                System.out.println(closingMessage);
                break;
            } else {
                String message = separator + "\n" + command + "\n" + separator;
                System.out.println(message);
            }
        }
        s.close();
    }
}