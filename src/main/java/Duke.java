public class Duke {

    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList(this.ui);
    }

    // adds a task to the list
    // public static void addTaskToList(Task task, ArrayList<Task> list) {
    //     list.add(task);
    //     String s = String.format(
    //         "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
    //         task.toString(),
    //         list.size()
    //     );
    //     Ui.prettyPrint(s);
    // }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        // print welcome message
        this.ui.showWelcome();

        // parse user input
        String rawInput;
        String[] rawSplit;
        String[] arguments = {};
        String command;

        while (true) {
            try {
                // scan for user input
                rawInput = this.ui.readCommand();
                rawSplit = rawInput.split(" ", 2);
                command = rawSplit[0];
                if (rawSplit.length > 1) {
                    arguments = rawSplit[1].split("\\W\\/[a-zA-Z]+\\W");
                } else {
                    arguments = new String[0];
                }

                // parse commands with no arguments
                if (command.equals("bye")) {
                    Ui.prettyPrint("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    this.taskList.displayTasks();
                    this.ui.displayMessage();
                }
                // parse commands with arguments
                else if (command.equals("mark")) {
                    // possible errors for mark and unmark and delete
                    // 1. invalid # arguments (no mark target given)
                    // 2. mark target doesnt exist (out of range)
                    int index = Integer.parseInt(arguments[0]) - 1;
                    this.taskList.markTaskAsDone(index);
                    this.ui.displayMessage();
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(arguments[0]) - 1;
                    this.taskList.markTaskAsUndone(index);
                    this.ui.displayMessage();
                    // } else if (command.equals("delete")) {
                    //     int index = Integer.parseInt(arguments[0]) - 1;
                    //     Task currentTask = list.get(index);
                    //     list.remove(index);
                    //     String s = String.format(
                    //         "Noted. I've removed this task:\n %s",
                    //         currentTask.toString()
                    //     );
                    //     Ui.prettyPrint(s);
                    // } else if (command.equals("todo")) {
                    //     // implicitly handles todo commands with empty descriptions
                    //     // as those will have 0 arguments
                    //     if (arguments.length != 1) {
                    //         throw new InvalidArgumentException(
                    //             command,
                    //             arguments.length,
                    //             1
                    //         );
                    //     }
                    //     Task currentTask = new ToDo(arguments[0]);
                    //     Duke.addTaskToList(currentTask, list);
                    // } else if (command.equals("deadline")) {
                    //     // possible errors for deadline & event
                    //     // 1. arguments may not have the proper format.
                    //     //    code currently reads any string starting with '/' as
                    //     //    the start of an argument. not necessarily '/by'
                    //     //    or '/from' or '/to', as expected
                    //     if (arguments.length != 2) {
                    //         throw new InvalidArgumentException(
                    //             command,
                    //             arguments.length,
                    //             2
                    //         );
                    //     }
                    //     Task currentTask = new Deadline(arguments[0], arguments[1]);
                    //     Duke.addTaskToList(currentTask, list);
                    // } else if (command.equals("event")) {
                    //     if (arguments.length != 3) {
                    //         throw new InvalidArgumentException(
                    //             command,
                    //             arguments.length,
                    //             3
                    //         );
                    //     }
                    //     Task currentTask = new Event(
                    //         arguments[0],
                    //         arguments[1],
                    //         arguments[2]
                    //     );
                    //     Duke.addTaskToList(currentTask, list);
                    // }
                    // undefined commands
                } else {
                    throw new UnknownCommandException(rawInput);
                }
            } catch (DukeException e) {
                Ui.prettyPrint(e.toString());
            }
        }
    }
}
