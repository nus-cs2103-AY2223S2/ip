import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    protected static final String FILE_DIRECTORY = "../../../data";
    protected static final String FILE_PATH = "../../../data/duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(FILE_DIRECTORY, FILE_PATH);
        TaskList taskList = new TaskList();
        UI ui = new UI();
        
        ui.printWelcomeMessage();

        try {
            storage.loadTasks(taskList);
        } catch (FileNotFoundException e) {
            ui.printMessage("No save data found!");
        } catch (IOException | DukeException e) {
            ui.printMessage("Error loading save data");
        }

        while (true) {
            try {
                String[] command = sc.nextLine().split(" ", 2);
                if (command[0].equals("bye")) {
                    storage.saveTasks(taskList);
                    ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (command[0].equals("list")) {
                    ui.printMessage(taskList.listTasks());
                } else if (command[0].equals("mark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    taskList.markTask(taskNum);
                    ui.printSuccessMessage("Nice! I've marked this task as done:", taskList.getTasks().get(taskNum));
                } else if (command[0].equals("unmark")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    taskList.unmarkTask(taskNum);
                    ui.printSuccessMessage("OK, I've marked this task as not done yet:",
                            taskList.getTasks().get(taskNum));
                } else if (command[0].equals("delete")) {
                    if (command.length < 2) {
                        throw new DukeException("Task number required");
                    }
                    int taskNum = Integer.parseInt(command[1]) - 1;
                    Task removedTask = taskList.deleteTask(taskNum);
                    ui.printTaskMessage("Noted. I've removed this task:", removedTask, taskList.getTasks().size());
                } else {
                    if (command.length < 2) {
                        throw new DukeException("Invalid input");
                    }
                    switch (command[0]) {
                        case "todo":
                            taskList.getTasks().add(new Todo(command[1]));
                            break;
                        case "deadline": {
                            String[] arguments = command[1].split(" /by ");
                            if (arguments.length < 2) {
                                throw new DukeException("Deadline needs a \"by date\"");
                            }
                            taskList.getTasks().add(new Deadline(arguments[0], arguments[1]));
                            break;
                        }
                        case "event": {
                            String[] arguments = command[1].split(" /from ");
                            if (arguments.length < 2) {
                                throw new DukeException("invalid format");
                            }
                            String[] timings = arguments[1].split(" /to ");
                            if (timings.length < 2) {
                                throw new DukeException("invalid format");
                            }
                            taskList.getTasks().add(new Event(arguments[0], timings[0], timings[1]));
                            break;
                        }
                        default:
                            throw new DukeException("I do not understand");
                    }
                    ui.printTaskMessage("Got it. I've added this task:",
                            taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
                }
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printMessage("Valid task required");
            } catch (IOException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }





}
