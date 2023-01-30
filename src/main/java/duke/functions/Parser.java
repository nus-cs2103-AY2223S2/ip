package duke.functions;

import duke.tasks.TaskList;

public class Parser {
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     *  Handles the logic flow direction from user input.
     *  1. When a '/' is present, there are 3 arguments to the input.
     *  The first loop section handles by splitting them into command, task name and deadline,
     *  and passes them down to handleThreeInputs(command, name, time, d);
     *  2. Else, it is a double argument input, split by first empty space " ",
     *  with split[1] being task name if command is todo, else split[1] is index for CRUD commands.
     *  3. Otherwise, handle single inputs with the function.
     * @param input User input from terminal.
     * @param d Initialised TaskList data structure
     *
     */
    public void handleInput(String input, TaskList d) {
        if (input.contains("/")) {
            String[] split = input.split("/", 2);
            String[] secondSplit = split[0].split(" ", 2);
            String command = secondSplit[0];
            String name = secondSplit[1];
            String time = split[1];
            handleThreeInputs(command, name, time, d);
        } else {
            String[] split = input.split(" ", 2);
            String command = split[0];
            if ("todo".equalsIgnoreCase(command)) {
                d.insertToDo(split[1]);
            } else if (split.length > 1) {
                Integer index = Integer.parseInt(split[1]);
                handleTwoInputs(command, index, d);
            } else {
                handleSingleInput(command, d);
            }
        }
    }

    public void handleSingleInput(String command, TaskList d) {
        switch (command) {
            case "bye":
                exit();
                break;
            case "list":
                System.out.println(d.toString());
                break;
            default:
                d.insert(command);
        }
    }

    public void handleTwoInputs(String command, Integer index, TaskList d) {
        switch (command) {
            case "mark":
                d.mark(index);
                break;
            case "unmark":
                d.unMark(index);
                break;
            case "delete":
                d.deleteTask(index);
                break;
        }
    }

    public void handleThreeInputs(String command, String name, String time, TaskList d) {
        switch (command) {
            case "deadline":
                d.insertDeadline(name, time);
                break;
            case "event":
                d.insertEvent(name, time);
                break;
            case "todo":
                d.insertToDo(name);
                break;
        }
    }

    public static void exit(){
        String exitMsg = Ui.format("Bye. Come back soon!");
        System.out.println(exitMsg);
        System.exit(1);
    }
}
