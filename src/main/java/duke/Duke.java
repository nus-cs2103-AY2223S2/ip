package duke;
import java.io.IOException;


/**
 * The Duke program implements an application that takes in an input and places it in a list.
 * If the words are not of any keywords, an error message will be printed.
 * @author yanlings
 *
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke when there is no filePath required.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("text-ui-test/saved-tasks.txt");
        try {
            taskList = new TaskList();
            storage.handleLoad();
        } catch (IOException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }
    /**
     * Constructor for Duke such that we will create a file.
     * @param filePath
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.handleLoad();

        } catch (IOException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }


    /**
     * Returns a string representation based on the task typed by the user
     * Error will be returned if the input does not include deadline,todo,
     * mark,unmark,event,tag,list,delete,find or bye
     * @param input String input from users
     * @return String representation based on the action
     * @throws IOException
     */
    String getResponse(String input) throws IOException {
            if (input.startsWith("deadline")) {
                input = input.replaceAll("deadline ", "");
                String[] replies = Parser.splitForDeadline(input);
                Deadline deadline = new Deadline(replies[0],replies[1]);
                taskList.add(deadline);
                deadline.handleDeadline();
                storage.saveTasks();
                return ui.addTask(deadline);
            }else if (input.startsWith("todo")) {
                input = input.replaceAll("todo", "");
                InvalidArgsHandler checked = new InvalidArgsHandler(input);
                checked.checkForToDo(checked.reply);
                ToDo toDo = new ToDo(input);
                taskList.add(toDo);
                toDo.handleToDo();
                storage.saveTasks();
                return ui.addTask(toDo);
            } else if (input.startsWith("event")) {
                input = input.replaceAll("event", "");
                String[] replies = Parser.splitForEvent(input);
                Event event = new Event(replies[0],replies[1]);
                event.handleEvent();
                taskList.add(event);
                storage.saveTasks();
                return ui.addTask(event);
            } else if (input.startsWith("tag")){
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task.tasks.get(value).handleTag(input);
                storage.saveTasks();
                return ui.stringTag(Task.tasks.get(value));
             }
            else if (input.startsWith("unmark")) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task.tasks.get(value).unmark();
                storage.saveTasks();
                return ui.stringMark(Task.tasks.get(value));
            } else if (input.startsWith("mark")) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task.tasks.get(value).mark();
                storage.saveTasks();
                return ui.stringMark(Task.tasks.get(value));
            }
            else if (input.startsWith("list")) {
                return ui.showList();
            } else if (input.startsWith("delete")) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task deleted = Task.tasks.get(value);
                Task.delete(deleted);
                storage.saveTasks();
                return ui.stringDelete(deleted);
            } else if (input.startsWith("find")) {
                int counter = 0;
                String desc = input.replaceAll("find ", "");
                TaskList.find(desc);
                return TaskList.printFind();
            } else if (input.startsWith("bye")){
                System.out.println("Bye, Hope to see you again soon!");
                return "Bye, Hope to see you again soon!";
            } else {
                InvalidArgsHandler checked = new InvalidArgsHandler(input);
                checked.checkForRandomWords(checked.reply);
            }
            storage.saveTasks();
        return "Duke heard: " + input;
    }
}

