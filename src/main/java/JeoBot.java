import java.util.Scanner;

public class JeoBot {
    public static void greeting() {
        String name = "JeoBot";
        String divider = "-----------------------";
        System.out.println(divider + "\n" + "Greetings from " + name + "!\n" + "How may I help you?\n" + divider);
    }

    public static void echo() {
        String divider = "________________________________________________________________";
        boolean hasInput = true;
        Storage st = new Storage();
        while (hasInput) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            s = s.trim();
            String command = s.replaceAll("\\s", "").toLowerCase();
            // Check if first word of command is "mark" or "unmark", and if an integer follows after
            if (command.contains("unmark") && command.startsWith("unmark")
                    && command.substring(6).matches("-?\\d+")) {
                command = "unmark";
            } else if (command.contains("mark") && command.startsWith("mark")
                    && command.substring(4).matches("-?\\d+")) {
                command = "mark";
            }
            System.out.println(divider);
            switch (command) {
                case "bye":
                    hasInput = false;
                    System.out.println("Thank you for using JeoBot. Hope to see you again soon!");
                    break;
                case "list":
                    st.showTasks();
                    break;
                case "mark":
                    s = s.replaceAll("\\s", "");
                    // Check if input command given is just "mark" without an integer (no task number)
                    if (s.substring(4).equals("")) {
                        System.out.println("Please indicate task number!");
                        break;
                    }
                    int index = Integer.parseInt(s.substring(4)) - 1;
                    // Check if integer falls within the list of task numbers
                    if (index >= 0 && index < st.getNumberOfTasks()) {
                        st.markTask(index);
                    } else {
                        System.out.println("Invalid task number given!");
                    }
                    break;
                case "unmark":
                    s = s.replaceAll("\\s", "");
                    // Check if input command given is just "unmark" without an integer (no task number)
                    if (s.substring(6).equals("")) {
                        System.out.println("Please indicate task number!");
                        break;
                    }
                    index = Integer.parseInt(s.substring(6)) - 1;
                    // Check if integer falls within the list of task numbers
                    if (index >= 0 && index < st.getNumberOfTasks()) {
                        st.unmarkTask(index);
                    } else {
                        System.out.println("Invalid task number given!");
                    }
                    break;
                default:
                    Task task = new Task(s);
                    st.addTask(task);
                    System.out.println(divider);
            }
        }
    }

    public static void main(String[] args) {
        greeting();
        echo();
    }
}
