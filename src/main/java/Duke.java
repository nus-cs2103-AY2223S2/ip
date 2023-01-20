import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String separator = "____________________________________________________________";
        ArrayList<String> todoList = new ArrayList<>();

        String openingMessage = separator + "\nHello! I'm Duke\n" + "What can I do for you?\n" + separator;
        System.out.println(openingMessage);

        while(s.hasNext()) {
            String input = s.nextLine();
            if(input.equals("bye")) {
                String closingMessage = separator + "\nBye. Hope to see you again soon!\n" + separator;
                System.out.println(closingMessage);
                break;
            } else if(input.equals("list")) {
                System.out.println(separator);
                for(int i = 0; i < todoList.size(); i++) {
                    String number = Integer.toString(i + 1);
                    System.out.println(number + ". " + todoList.get(i));
                }
                System.out.println(separator);
            } else {
                String message = separator + "\nadded: " + input + "\n" + separator;
                todoList.add(input);
                System.out.println(message);
            }
        }
        s.close();
    }
}