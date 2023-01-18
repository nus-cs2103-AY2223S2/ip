import java.util.Scanner;
import java.util.ArrayList;

public class Miki {
    private static void printDiv() {
        System.out.println("    ____________________________________________________________");
    }

    private static void print(String s) {
        System.out.println("     " + s);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        printDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        print("\uD83C\uDF80✨");
        print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        printDiv();
        boolean exit_cmd = false;
        while (!exit_cmd) {
            System.out.print(">");
            String cmd_line = sc.nextLine();
            String cmd = cmd_line.split(" ")[0];
            String[] cmd_args = {};
            if (cmd_line.contains(" ")) {
                cmd_args = cmd_line.substring(cmd.length() + 1).split(" ");
            }
            switch (cmd) {
                case "bye":
                    exit_cmd = true;
                    break;
                case "list":
                    printDiv();
                    print("caught in 4k:");
                    for (int i = 0; i < tasks.size(); i++) {
                        print(Integer.toString(i + 1) + ". " + tasks.get(i));
                    }
                    printDiv();
                    break;
                case "mark": {
                    int idx = Integer.parseInt(cmd_args[0]) - 1;
                    tasks.get(idx).mark();
                    printDiv();
                    print("Yay!! Task marked as done:");
                    print("  " + tasks.get(idx));
                    printDiv();
                    break;
                }
                case "unmark": {
                    int idx = Integer.parseInt(cmd_args[0]) - 1;
                    tasks.get(idx).unmark();
                    printDiv();
                    print("okay...! task unmarked as undone:");
                    print("  " + tasks.get(idx));
                    printDiv();
                    break;
                }
                default:
                    Task newTask = new Task(cmd_line);
                    tasks.add(newTask);
                    printDiv();
                    print("Added this! :");
                    print("  " + newTask.toString());
                    printDiv();
            }
        }
        printDiv();
        print("Otsumiki!~ I'll see you later!");
        printDiv();
    }
}
