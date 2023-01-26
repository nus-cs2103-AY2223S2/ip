import duke.*;
import duke.packages.*;

import java.io.IOException;
import java.time.LocalDate;

public class Duke {
    /**
     * Searches for a task to be marked for completion.
     * @param parsed Parsed command
     * @param tempTaskList List of tasks
     * @return Task that will be marked or unmarked
     */
    private static Task getTaskForMarking(String[] parsed, TaskList tempTaskList) {
        int completedIndex = Integer.parseInt(parsed[1]) - 1; // index of the task completed
        Task completedTask = tempTaskList.getTaskAtIndex(completedIndex); // actual task
        return completedTask;
    }
    public static void main(String[] args) throws EmptyDescriptionException, IOException {
        TaskList mainTaskList = new TaskList();
        Storage mainStorage = new Storage(mainTaskList); // will load from file

        Ui mainUi = new Ui(mainTaskList);

        mainUi.greetUser();

        label:
        while (true) {
            String command = mainUi.getNextTask();

            String[] toFindFirstWord = Parser.parse(command, Parser.ParseFunctions.SPLIT_ALL); // take a comment

            String first = toFindFirstWord[0];

            switch (first) {
                case "bye": {
                    mainUi.printReply("bye");
                    break label;
                }
                case "mark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord, mainTaskList);
                    completedTask.setCompletion();
                    mainUi.printReply("mark", completedTask);

                    String[] parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
                    mainStorage.changeTaskCompletion(Integer.parseInt(parsed[1]));
                    break;
                }
                case "unmark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord, mainTaskList);
                    completedTask.setCompletion();
                    mainUi.printReply("unmark", completedTask);

                    String[] parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
                    mainStorage.changeTaskCompletion(Integer.parseInt(parsed[1]));
                    break;
                }
                case "delete": {
                    Task toDelete = getTaskForMarking(toFindFirstWord, mainTaskList);

                    String[] parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
                    mainStorage.deleteTask(Integer.parseInt(parsed[1]));

                    mainUi.printReply("delete", toDelete);
                    break;
                }
                case "deadline": {
                    String[] parsed = Parser.parse(command, Parser.ParseFunctions.DEADLINE);
                    Task newDeadline = new Deadline(parsed[1], LocalDate.parse(parsed[2]));
                    mainStorage.addTask(newDeadline);
                    mainUi.printReply("deadline", newDeadline);
                    break;
                }
                case "event": {
                    String[] parsed = Parser.parse(command, Parser.ParseFunctions.EVENT);
                    Task newEvent = new Event(parsed[1], LocalDate.parse(parsed[2]), LocalDate.parse(parsed[3]));
                    mainStorage.addTask(newEvent);
                    mainUi.printReply("event", newEvent);
                    break;
                }
                case "todo": {
                    try {
                        String[] parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
                        ToDo newToDo = new ToDo(parsed[1]);
                        mainStorage.addTask(newToDo);
                        mainUi.printReply("todo", newToDo);
                        break;
                    }
                    catch (EmptyDescriptionException e) {
                        System.out.println("  Add an argument");
                    }
                }
                default:
                    mainUi.printReply(first);
                    break;
            }
        }
    }
}
