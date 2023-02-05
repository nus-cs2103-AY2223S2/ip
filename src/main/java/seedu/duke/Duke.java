/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

import seedu.duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the main program of Duke.
 */
public class Duke {

    private static boolean isFind = false;
    private static boolean isDisplayList = false;
    private static TaskList tempTasks;
    private static TaskList tasks;

    private Ui ui;
    private Storage storage;

    /**
     * Represents the set of commands by the user.
     */
    protected enum Commands {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete,
        find,
        originalList
    }

    /**
     * Constructor for the Duke class.
     *
     * @param filepath The file path for the data stored in the txt file.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        if (!isFind) {
            try {
                tasks = new TaskList(storage.load());
            } catch (DukeException e) {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        }
    }

    /**
     * The start of the program.
     */
    public String run(String input) {
        // Description of the task.
        String description = "";
        // Return of the final String to add.
        String dukeText = "";

        description = "";
        Parser userParse = new Parser(input);
        try {
            Commands userCommand = userParse.checkCommand(userParse.command);
            switch(userCommand) {
            case bye:
                dukeText += ui.bye();
                storage.write(tasks);
                break;
            case mark:
                if (!isDisplayList) {
                    isDisplayList = true;
                    ui.noListError(tasks);
                }
                int userIndex = Integer.parseInt(userParse.inputArr[1]) - 1;
                if (isFind) {
                    TaskList marked = tempTasks.mark(userParse);
                    dukeText += ui.markDisplay(marked, userParse);
                    for (int i = 0; i < tasks.tasksCounter; i++) {
                        if (marked.tasksList.get(userIndex)
                                == tasks.tasksList.get(i)) {
                            tasks.tasksList.get(i).mark();
                            System.out.println("test");
                        }
                    }
                } else {
                    dukeText += ui.markDisplay(tasks.mark(userParse), userParse);
                }
                storage.write(tasks);
                break;
            case unmark:
                if (!isDisplayList) {
                    isDisplayList = true;
                    ui.noListError(tasks);
                }
                userIndex = Integer.parseInt(userParse.inputArr[1]) - 1;
                if (isFind) {
                    TaskList unmarked = tempTasks.unmark(userParse);
                    dukeText += ui.unmarkDisplay(unmarked, userParse);
                    for (int i = 0; i < tasks.tasksCounter; i++) {
                        if (unmarked.tasksList.get(userIndex)
                                == tasks.tasksList.get(i)) {
                            tasks.tasksList.get(i).unmark();
                        }
                    }
                } else {
                    dukeText += ui.unmarkDisplay(tasks.unmark(userParse), userParse);
                }
                storage.write(tasks);
                break;
            case list:
                isDisplayList = true;
                if (isFind) {
                    dukeText += "Here are the list of found tasks:\n";
                    dukeText += ui.list(tempTasks);
                } else {
                    dukeText += "Here are the list of tasks:\n";
                    dukeText += ui.list(tasks);
                }
                break;
            case todo:
                if (isFind) {
                    dukeText += "Exiting out of \"found\" list\n";
                    isFind = false;
                    tempTasks = new TaskList(tasks);
                }
                tasks.addTodo(description, userParse);
                dukeText += ui.addTodoMessage(tasks);
                storage.write(tasks);
                break;
            case deadline:
                if (isFind) {
                    dukeText += "Exiting out of \"found\" list\n";
                    isFind = false;
                    tempTasks = new TaskList(tasks);
                }
                tasks.addDeadline(description, userParse);
                dukeText += ui.addDeadlineMessage(tasks);
                storage.write(tasks);
                break;
            case event:
                if (isFind) {
                    dukeText += "Exiting out of \"found\" list\n";
                    isFind = false;
                    tempTasks = new TaskList(tasks);
                }
                tasks.addEvent(description, userParse);
                dukeText += ui.addEventMessage(tasks);
                storage.write(tasks);
                break;
            case delete:
                if (!isDisplayList) {
                    isDisplayList = true;
                    ui.noListError(tasks);
                }
                if (isFind) {
                    Task deleted = tempTasks.delete(userParse);
                    dukeText += ui.deleteMessage(tempTasks, deleted);
                    for (int i = 0; i < tasks.tasksCounter; i++) {
                        if (deleted == tasks.tasksList.get(i)) {
                            tasks.tasksList.remove(i);
                            tasks.tasksCounter--;
                        }
                    }
                } else {
                    dukeText += ui.deleteMessage(tasks, tasks.delete(userParse));
                }
                storage.write(tasks);
                break;
            case find:
                isDisplayList = true;
                isFind = true;
                tempTasks = tasks.find(userParse);
                dukeText += ui.findMessage(tempTasks);
                break;
            case originalList:
                isFind = false;
                tempTasks = new TaskList(tasks);
                dukeText += "Here are the list of tasks:\n";
                dukeText += ui.list(tasks);
                storage.write(tasks);
                break;
            default:
                break;
            }
        } catch (DukeException e) {
            dukeText += e.getMessage();
        }
        return dukeText;
    }

    /**
     * Returns the duke response.
     *
     * @param input The input from the user.
     * @return The response of Duke.
     */
    protected String getResponse(String input) {
        Duke currDuke = new Duke("data/duke.txt");
        return "Duke: \n" + currDuke.run(input);
    }

    /**
     * Returns the list of Commands.
     *
     * @return A String of List of commands.
     */
    public static String getCommands() {
        String commandsMessage = "";
        List<String> commandList = new ArrayList<String>();
        
        for (Commands curr : Commands.values()) {
            commandList.add(curr.name() + "\n");
        }
        commandsMessage = commandList.stream().collect(Collectors.joining());
        return commandsMessage;
    }
}
