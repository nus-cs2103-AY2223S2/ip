package duke;

public class Parser {

    public Parser(String input) {
        String trigger = input.split(" ")[0];
        int tid = 1;
        Duke.Task task;
        String content = "", ddl = "", from = "", to = "";
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void parse(String input, TaskList todos) throws Duke.DukeCommandNotFoundException, Duke.DukeEmptyTaskException {
        String trigger = input.split(" ")[0];
        int tid = 1;
        Duke.Task task;
        String content = "", ddl = "", from = "", to = "";
        switch (trigger) {
            case "bye":
                print("Bye. Hope to see you again soon!");
                System.exit(0);
            case "list":
                if (todos.isEmpty()) {
                    print("No items yet.");
                } else {
                    int i = 1;
                    for (Duke.Task t : todos) {
                        print(i + "." + t.toString());
                        i++;
                    }
                }
                break;
            case "mark":
                try {
                    tid = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                task = todos.get(tid - 1);
                task.markAsDone();
                print("Nice! I've marked this task as done:");
                print("\t" + task);
                break;
            case "unmark":
                try {
                    tid = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                task = todos.get(tid - 1);
                task.markAsNotDone();
                print("OK, I've marked this task as not done yet:");
                print("\t" + task);
                break;
            case "deadline":
                try {
                    input = input.split(trigger)[1];
                    content = input.split("/by")[0].strip();
                    ddl = input.split("/by")[1].strip();
                    ddl = parse_date(ddl);
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                task = new Duke.Deadline(content, ddl);
                todos.add(task);
                print("Got it. I've added this task:");
                print("\t" + task);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "event":
                if (input.split(trigger).length == 1) {
                    throw new Duke.DukeEmptyTaskException("☹ OOPS!!! The description of a " + trigger + " cannot be empty.");
                } else {
                    input = input.split(trigger)[1].strip();
                }
                try {
                    content = input.split("/from")[0].strip();
                    from = input.split("/from")[1].split("/to")[0].strip();
                    to = input.split("/from")[1].split("/to")[1].strip();
                    from = parse_date(from);
                    to = parse_date(to);
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                task = new Duke.Event(content, from, to);
                todos.add(task);
                print("Got it. I've added this task:");
                print("\t" + task);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "todo":
                if (input.split(trigger).length == 0) {
                    throw new Duke.DukeEmptyTaskException("☹ OOPS!!! The description of a " + trigger + " cannot be empty.");
                } else {
                    input = input.split(trigger)[1].strip();
                }
                task = new Duke.Todo(input);
                todos.add(task);
                print("Got it. I've added this task:");
                print("\t" + task);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "delete":
                if (input.split(trigger).length == 1) {
                    throw new Duke.DukeEmptyTaskException("☹ OOPS!!! The description of a " + trigger + " cannot be empty.");
                }
                try {
                    tid = Integer.parseInt(input.split(trigger)[1].strip());
                    task = todos.get(tid - 1);
                    todos.remove(task);
                    print("Noted. I've removed this task:");
                    print("\t" + task);
                    print("Now you have " + todos.size() + " tasks in the list.");
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                break;
            default:
                throw new Duke.DukeCommandNotFoundException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
