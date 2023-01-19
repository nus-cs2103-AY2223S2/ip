import java.util.Scanner;
import java.util.ArrayList;

public class Miki {
    private static void printDiv() {
        System.out.println("    ____________________________________________________________");
    }

    private static void print(String s) {
        System.out.println("     " + s);
    }

    private static void printAdded(Task t, int taskCount) {
        print("Added this thing! That makes " + taskCount + (taskCount == 1 ? " task" : " tasks") + ":");
        print("  " + t.toString());
    }

    public static void main(String[] args) {
        boolean ascii_only = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ascii-only")) ascii_only = true;
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        if (!ascii_only) {
            print("\uD83C\uDF80✨");
            print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        } else {
            print("01 f3 80 / 27 28");
            print("Hello " + username + " !! Konmiki! \\(^v^)/");
        }
        printDiv();

        boolean exit_cmd = false;
        while (!exit_cmd) {
            System.out.print(">");
            String cmd_line = sc.nextLine();
            String cmd = cmd_line.split(" ")[0].toLowerCase();
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
                case "todo": {
                    String objective = "";
                    for (int i = 0; i < cmd_args.length; i++) {
                        objective += (objective.isEmpty() ? "" : " ") + cmd_args[i];
                    }
                    Todo newTodo = new Todo(objective);
                    tasks.add(newTodo);
                    printDiv();
                    printAdded(newTodo, tasks.size());
                    printDiv();
                    break;
                }
                case "deadline": {
                    String objective = "";
                    String by = "";
                    boolean token_by = false;
                    for (int i = 0; i < cmd_args.length; i++) {
                        if (cmd_args[i].equals("/by")) {
                            token_by = true;
                            continue;
                        }
                        if (token_by) {
                            by += (by.isEmpty() ? "" : " ") + cmd_args[i];
                        } else {
                            objective += (objective.isEmpty() ? "" : " ") + cmd_args[i];
                        }
                    }
                    Deadline newDeadline = new Deadline(objective, by);
                    tasks.add(newDeadline);
                    printDiv();
                    printAdded(newDeadline, tasks.size());
                    printDiv();
                    break;
                }
                case "event": {
                    String objective = "";
                    String from = "";
                    String to = "";
                    boolean token_from = false;
                    boolean token_to = false;
                    for (int i = 0; i < cmd_args.length; i++) {
                        if (cmd_args[i].equals("/from")) {
                            token_from = true;
                            token_to = false;
                            continue;
                        }
                        if (cmd_args[i].equals("/to")) {
                            token_from = false;
                            token_to = true;
                            continue;
                        }
                        if (token_from) {
                            from += (from.isEmpty() ? "" : " ") + cmd_args[i];
                        } else if (token_to) {
                            to += (to.isEmpty() ? "" : " ") + cmd_args[i];
                        } else {
                            objective += (objective.isEmpty() ? "" : " ") + cmd_args[i];
                        }
                    }
                    Event newEvent = new Event(objective, from, to);
                    tasks.add(newEvent);
                    printDiv();
                    printAdded(newEvent, tasks.size());
                    printDiv();
                    break;
                }
                default:
                    Task newTask = new Task(cmd_line);
                    tasks.add(newTask);
                    printDiv();
                    printAdded(newTask, tasks.size());
                    printDiv();
            }
        }
        printDiv();
        print("Otsumiki!~ I'll see you later!");
        printDiv();
    }
}
