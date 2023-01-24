import tasklist.TaskList;
import storage.Storage;
import java.util.regex.*;
import tasktypes.*;
import ui.Ui;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();
        Storage storage = new Storage(ui);
        TaskList tasks = storage.load();

        String[] singleCommands = {"bye", "list"};
        String[] valueCommands = {"unmark ", "mark ", "delete "};
        String[] taskCommands = {"deadline ", "todo ", "event "};

        String input = ui.nextInput();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                ui.printTasks(tasks);
                input = ui.nextInput();
                continue;
            }

            String command = returnCommand(input, valueCommands);
            switch (command) {
                case "unmark ": {
                    String[] inputArr = input.split(" ");
                    int toUnmark = Integer.parseInt(inputArr[1]);
                    tasks.unmarkTask(toUnmark);
                    input = ui.nextInput();
                    continue;
                }

                case "mark ": {
                    String[] inputArr = input.split(" ");
                    int toMark = Integer.parseInt(inputArr[1]);
                    tasks.markTask(toMark);
                    input = ui.nextInput();
                    continue;
                }

                case "delete ": {
                    String[] inputArr = input.split(" ");
                    int toDelete = Integer.parseInt(inputArr[1]);
                    if (toDelete > tasks.getNumTasks()) {
                        ui.invalidDelete();
                        input = ui.nextInput();
                        continue;
                    }
                    tasks.deleteTask(toDelete);
                    input = ui.nextInput();
                    continue;
                }
            }


            try {
                validateTaskCommand(input, taskCommands);
            } catch (DukeException de) {
                ui.showError(de);
                input = ui.nextInput();
                continue;
            }

            String[] inputArr = input.split(" ", 2);
            String taskType = inputArr[0];

            switch (taskType) {
                case "todo":
                    ToDo todoTask = new ToDo(inputArr[1]);
                    tasks.addTask(todoTask);
                    break;
                case "deadline": {
                    String[] newInputArr = inputArr[1].split(" /by ", 2);
                    Deadline deadlineTask = new Deadline(newInputArr[0], newInputArr[1]);
                    tasks.addTask(deadlineTask);
                    break;
                }
                case "event": {
                    String[] newInputArr = inputArr[1].split(" /from ", 2);
                    String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                    Event eventTask = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                    tasks.addTask(eventTask);
                    break;
                }
            }

            input = ui.nextInput();
        }

        storage.save(tasks);
        ui.goodbye();
        ui.close();
    }

    public static String returnCommand(String input, String[] commands) {
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find();

            if (gotMatch && match.start() == 0) {
                return s;
            }
        }

        return input;
    }

    public static void validateTaskCommand(String input, String[] commands) throws DukeException {
        input = input.trim();
        for (String s : commands) {
            if (s.equals(input + " ")) {
                throw new DukeException("OOPS!!! The description of a " + input + " task cannot be empty!");
            }
        }

        boolean correct = false;
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find() && (match.start() == 0);
            correct = correct || gotMatch;
        }

        if (!correct) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means! :(");
        }
    }

}
