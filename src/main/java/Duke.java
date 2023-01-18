import java.util.Scanner;

public class Duke {
    static String greetings() {
        String greetingsMessage = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        return String.format("%s%s%s", horizontalLine(), greetingsMessage, horizontalLine());
    }

    static String goodbye() {
        String goodbyeMessage = "\tBye. Hope to see you soon!\n";
        return String.format("%s%s%s", horizontalLine(), goodbyeMessage, horizontalLine());
    }

    static String displayList(String[] tasks, int task_num) {
        StringBuilder strList = new StringBuilder();
        for (int i = 0; i < task_num; i++) {
            strList.append(String.format("\t%d. %s\n", i + 1, tasks[i]));
        }
        return strList.toString();
    }

    static String respond(String command, String[] tasks, int task_num) {
        String response;
        if (command.equals("list")) {
            response =  displayList(tasks, task_num);
        } else {
            tasks[task_num] = command;
            response = String.format("\t%s%s\n", "added: ", command);
        }
        return String.format("%s%s%s", horizontalLine(), response, horizontalLine());
    }
    static String horizontalLine() {
        return "\t____________________________________\n";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greetings());
        String command;
        String[] tasks = new String[100]; int task_num = 0;
        while (!(command = sc.nextLine()).equals("bye")) {
            System.out.println(respond(command, tasks, (command.equals("list")) ? task_num : task_num++));
        }
        System.out.println(goodbye());
    }
}
