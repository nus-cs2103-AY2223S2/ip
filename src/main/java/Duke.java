import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static utils.FormatHelper.INPUTFORMAT;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String currentInput;
    private static TaskList taskList;
    private static Storage memory = new Storage("/duke.txt");
    private static Ui ui = new Ui();

    public static void main(String[] args) {

        taskList = new TaskList(memory.load());
        //Introduction
        ui.greet();
        currentInput = sc.nextLine();
        while (!currentInput.equalsIgnoreCase("bye")) {
            try {
                //when there is no input
                if (currentInput.equals("")) {
                    ui.reply("Please input a command");
                } else if (currentInput.equalsIgnoreCase("list")) {
                    ui.reply(taskList.toString());
                } else if (currentInput.matches("mark \\d+")) {
                    int index = Integer.parseInt(currentInput.substring(5)) - 1;
                    ui.reply(taskList.mark(index));
                    memory.saveState(taskList);
                } else if (currentInput.matches("unmark \\d+")) {
                    int index = Integer.parseInt(currentInput.substring(7)) - 1;
                    ui.reply(taskList.unmark(index));
                    memory.saveState(taskList);
                } else if (currentInput.matches("^(todo|deadline|event) .*")) {
                    if (currentInput.matches("^todo .*")) {
                        String description = currentInput.substring(5);
                        ui.reply(taskList.addTodo(description));
                    } else if (currentInput.matches("^deadline .*")) {
                        int byPos = currentInput.indexOf(" /by ");
                        if (byPos == -1) {
                            throw new DukeException("Deadline not specified with /by");
                        }
                        String description = currentInput.substring(9, byPos);
                        String by = currentInput.substring(byPos + 5);
                        LocalDateTime convertedBy = LocalDateTime.parse(by, INPUTFORMAT);
                        ui.reply(taskList.addDeadline(description, convertedBy));
                    } else if (currentInput.matches("^event .*")) {
                        int fromPos = currentInput.indexOf(" /from ");
                        int toPos = currentInput.indexOf(" /to ");
                        if (fromPos == -1 || toPos == -1 || toPos > currentInput.length() + 4) {
                            throw new DukeException("Please include both /from and /to");
                        }
                        if (fromPos > toPos) {
                            throw new DukeException("Please add the from date first followed by to date");
                        }
                        if (fromPos == 5) {
                            throw new DukeException("Please include a description of the task");
                        }
                        String description = currentInput.substring(6, fromPos);
                        String from = currentInput.substring(fromPos + 7, toPos);
                        String to = currentInput.substring(toPos + 5);
                        LocalDateTime convertedFrom = LocalDateTime.parse(from, INPUTFORMAT);
                        LocalDateTime convertedTo = LocalDateTime.parse(to, INPUTFORMAT);
                        ui.reply(taskList.addEvent(description, convertedFrom, convertedTo));
                    }
                    memory.saveState(taskList);
                } else if (currentInput.matches("^delete \\d+")) {
                    ui.reply(taskList.deleteTask(currentInput));
                    memory.saveState(taskList);
                } else {
                    ui.reply("Unknown command, please try again");
                }
            } catch (DukeException e) {
                ui.reply(e.toString());
            }

            currentInput = sc.nextLine();
        }
        //Signing off
        ui.signOff();
    }
}
