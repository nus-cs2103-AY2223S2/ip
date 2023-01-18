import java.util.Scanner;

public class Engine {
    private final Scanner scanner;
    private final TaskList taskList;

    private enum Command {
        EXIT,
        LIST,
        ADD,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,

        // errors
        ERROR
    }

    Engine() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    private String getOutput(Command command, String args) {
        switch (command) {
            case ERROR: {
                return args;
            }
            case EXIT: {
                return "Bye\n";
            }
            case LIST: {
                if (this.taskList.isEmpty()) {
                    return "You have no tasks.\n";
                }
                return "You have these tasks:" + this.taskList.toString();
            }
            case TODO: {
                String task = Util.cleanup(args);
                if (task.isEmpty()) {
                    return "Task input required.\n";
                }
                this.taskList.addTask(new Todo(Util.cleanup(args)));
                return "added-> " + this.taskList.getLast();
            }
            case DEADLINE: {
                String flag = "/by";
                int start = args.indexOf(flag);
                if (start == -1) {
                    return "/by flag required for deadlines.\n";
                }
                String desc = Util.cleanup(args.substring(0, start));
                String dl = Util.cleanup(args.substring(start + flag.length(), args.length()));
                
                if (desc.isEmpty() || dl.isEmpty()) {
                    return "Some inputs are blank.\n";
                }

                this.taskList.addTask(new Deadline(desc, dl));
                return "added-> " + this.taskList.getLast();
            }
            case EVENT: {
                String flag1 = "/from";
                String flag2 = "/to";
                int s1 = args.indexOf(flag1);
                int s2 = args.indexOf(flag2);
                if (s1 == -1 || s2 == -1) {
                    return "/from and /to flag required for events.\n";
                }
                String event = Util.cleanup(args.substring(0, s1));
                String from = Util.cleanup(args.substring(s1 + flag1.length(), s2));
                String to = Util.cleanup(args.substring(s2 + flag2.length(), args.length()));

                if (event.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    return "Some inputs are blank.\n";
                }

                this.taskList.addTask(new Event(event, from, to));
                return "added-> " + this.taskList.getLast();
            }
            case MARK: {
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "Task not in list.\n";
                    }
                    this.taskList.markTask(num);
                    return "Task marked completed:" +
                            this.taskList.get(num);
                } catch (NumberFormatException ex) {
                    return "Incorrect input.\n";
                }
            }
            case UNMARK: {
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "Task not in list.\n";
                    }
                    this.taskList.unmarkTask(num);
                    return "Task marked incomplete:" +
                            this.taskList.get(num);
                } catch (NumberFormatException ex) {
                    return "Incorrect input.\n";
                }
            }
            case DELETE: {
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "Task not in list.\n";
                    }
                    String out = "Task has been deleted:" + 
                        this.taskList.get(num) +
                        "\nTasks remaining: ";
                    this.taskList.deleteTask(num);
                    return out + this.taskList.toString();
                } catch (NumberFormatException ex) {
                    return "Incorrect input.\n";
                }

            }

            default:
                return "Case not accounted for, review code\n";
            // for debugging
        }
    }

    private Pair<Command, String> parseCommand(String line) {
        Pair<String, String> pr = Util.parseNextString(line);
        String command = pr.first().toLowerCase();
        String rest = pr.second();

        if (command.isEmpty()) {
            return new Pair<>(Command.ERROR, "No input detected.\n");
        }
        if (command.equals("bye")) {
            return new Pair<>(Command.EXIT, "");
        }
        if (command.equals("list")) {
            return new Pair<>(Command.LIST, "");
        }
        if (command.equals("mark")) {
            return new Pair<>(Command.MARK, rest);
        }
        if (command.equals("unmark")) {
            return new Pair<>(Command.UNMARK, rest);
        }
        if (command.equals("todo")) {
            return new Pair<>(Command.TODO, rest);
        }
        if (command.equals("event")) {
            return new Pair<>(Command.EVENT, rest);
        }
        if (command.equals("deadline")) {
            return new Pair<>(Command.DEADLINE, rest);
        }
        if (command.equals("delete")) {
            return new Pair<>(Command.DELETE, rest);
        }
        return new Pair<>(Command.ERROR, "Unknown command.\n");
    }

    public boolean run() {
        String line = this.scanner.nextLine();
        Pair<Command, String> pr = this.parseCommand(line);
        System.out.println("\nD: " + this.getOutput(pr.first(), pr.second()));
        if (pr.first().equals(Command.EXIT)) {
            return false;
        }
        return true;
    }
}
