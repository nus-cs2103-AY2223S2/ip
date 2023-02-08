public class Parser {
    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }

    public static String getSwitch(String str) {
        if (str.equals("list")) {
            return "list";
        }

        if (str.startsWith("mark ")) {
            return "mark";
        }
        if (str.startsWith("unmark ")) {
            return "unmark";
        }
        if (str.startsWith("todo ")) {
            return "todo";
        }
        if (str.startsWith("deadline ")) {
            return "deadline";
        }
        if (str.startsWith("event ")) {
            return "event";
        }
        if (str.startsWith("delete ")) {
            return "delete";
        }
        if (str.equals("bye")) {
            ui.bye();
            return "do nothing";
        }
        return "na";
    }

    public static void parse(String str, TaskList taskList) {
        switch (getSwitch(str)) {
        case "list":
            taskList.recite();
            break;

        case "mark":
            String num = str.split(" ", 2)[1];
            int n = Integer.parseInt(num) - 1;
            taskList.mark(n, true);
            break;
        case "unmark":
            String num_1 = str.split(" ", 2)[1];
            int n_1 = Integer.parseInt(num_1) - 1;
            taskList.mark(n_1, false);
            break;
        case "todo":
            Task a;
            try {
                a = new Todo(str.replace("todo", ""));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            taskList.addTask(a);
            break;
        case "deadline":
            Task b = null;
            try {
                String[] descriptionBy = str.replace("deadline", "").split(" /by ");
                b = new Deadline(descriptionBy[0], descriptionBy[1]);
                b.isDate();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            taskList.addTask(b);
            break;
        case "event":
            int index_1 = str.indexOf("/from");
            int index_2 = str.indexOf("/to");
            Task c = null;
            try {
                String[] descriptionFromTo = str.replace("event", "").split(" /from ");
                String[] fromTo = descriptionFromTo[1].split(" /to ");
                c = new Event(descriptionFromTo[0], fromTo[0], fromTo[1]);
                c.isDate();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            taskList.addTask(c);
            break;
        case "delete":
            String num_D = str.split(" ", 2)[1];
            int nD = Integer.parseInt(num_D) - 1;
            taskList.delete(nD);
            break;

        case "na":
            ui.unknownCommand();
            break;

        case "do nothing":
            break;
        }
    }
}
