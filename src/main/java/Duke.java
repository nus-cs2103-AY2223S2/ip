import java.util.*;
public class Duke {
    public static final String dashes = "--------------------------------------";
    public String formatString(String input) {
        return dashes + "\n" + input + "\n" + dashes + "\n";
    }
    public void echo() {
        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(this.formatString(line));
        }
    }
    public String formatList(List userText) {
        int index = 1;
        String formattedList = "";
        while (!userText.isEmpty()) {
            formattedList += index + ". " + userText.remove(0) + "\n";
            index++;
        }
        return formattedList.trim();
    }
    public void addAndList() {
        List<String> userText = new ArrayList<>();

        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                break;
            } else if ("list".equalsIgnoreCase(line)) {
                System.out.println(this.formatString(this.formatList(userText)));
            } else {
                userText.add(line);
                System.out.println(this.formatString("added: " + line));
            }
        }

    }
    public static void main(String[] args) {
        Duke gerty = new Duke();
        gerty.addAndList();
    }
}
