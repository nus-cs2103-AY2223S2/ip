package yj;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class YJ {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public YJ(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new YJ("tasks.txt").run();
    }

    public void run() {
        ui.print("Hello! I'm YJ. What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            Command command = Parser.parseCommand(input);
            switch (command) {
                case LIST:
                    tasks.forEachTask((task, i) -> ui.print((i + 1) + "." + task.toString()));
                    break;
                case DELETE:
                    try {
                        Integer taskNumber = Parser.parseDeleteCommand(input);
                        Task task = tasks.deleteTask(taskNumber);
                        ui.print("I've removed this task as u lazily requested:");
                        ui.print(task.toString());
                        ui.print("Now you have like this many tasks left: " + tasks.getNumberofTasks());
                    } catch (IndexOutOfBoundsException e) {
                        ui.print("Crapadoodle! You need to specify a task number or a valid task to delete.");
                    }
                    break;
                case MARK: {
                    Integer taskNumber = Parser.parseMarkCommand(input);
                    if (taskNumber != null && tasks.getTask(taskNumber - 1) != null) {
                        Task task = tasks.getTask(taskNumber - 1);
                        task.markAsDone();
                        ui.print("Niceoooo you're done wit this: ");
                        ui.print(task.toString());
                    }
                    break;
                }
                case UNMARK: {
                    Integer taskNumber = Parser.parseUnMarkCommand(input);
                    if (taskNumber != null && tasks.getTask(taskNumber - 1) != null) {
                        Task task = tasks.getTask(taskNumber - 1);
                        task.markAsNotDone();
                        ui.print("Ok lah you haven't finish this yet");
                        ui.print(task.toString());
                    }
                    break;
                }
                case TODO:
                    try {
                        String description = Parser.parseToDoCommand(input);
                        ToDo newToDo = new ToDo(description);
                        tasks.addTask(newToDo);
                        ui.print("Ok! I've added this todo! " + newToDo.toString());
                        ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                    } catch (IllegalArgumentException e) {
                        ui.print(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        Map<String, String> result = Parser.parseDeadlineCommand(input);
                        Deadline newDeadline = new Deadline(result.get("description"), result.get("by"));
                        tasks.addTask(newDeadline);
                        ui.print("Ok! I've added this deadline!" + newDeadline.toString());
                        ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.print("Crapadoodle! You need to specify a deadline in the correct format!");
                    }
                    break;
                case EVENT:
                    try {
                        Map<String, String> result = Parser.parseEventCommand(input);
                        Event newEvent = new Event(result.get("description"), result.get("from"), result.get("to"));
                        tasks.addTask(newEvent);
                        ui.print("Ok! I've added this event!" + newEvent.toString());
                        ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.print("Crapadoodle! You need to specify an event in the correct format!");
                    }
                    break;
                case FIND:
                    // Search for task descriptions for the keyword
                    String keyword = Parser.parseFindCommand(input);
                    List<Task> foundTasks = tasks.findKeywordInTasks(keyword);
                    if (foundTasks.size() == 0) {
                        ui.print("Crapadoodle! I couldn't find any tasks with that keyword!");
                    } else {
                        ui.print("Here are the matching tasks in your list:");
                        foundTasks.forEach(task -> ui.print(task.toString()));
                    }
                    break;
                case BYE:
                    // Write tasks to file
                    storage.save(tasks.getTasks());
                    ui.print("Byebye, YJ will miss you :(");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    ui.print("Crapdoodledy, I don't know what that means man");

            }
            input = sc.nextLine();
        }
    }
}

