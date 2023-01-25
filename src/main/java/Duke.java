import java.io.IOException;
import java.time.LocalDate;

public class Duke {
    public enum ParseFunctions {
        SPLIT_ALL, TODO, DEADLINE, EVENT
    }
    private static Task getTaskForMarking(String[] parsed, TaskList tempTaskList) {
        int completedIndex = Integer.parseInt(parsed[1]) - 1; // index of the task completed
        Task completedTask = tempTaskList.getTaskAtIndex(completedIndex); // actual task
        return completedTask;
    }
    public static void main(String[] args) throws EmptyDescriptionException, IOException {
        TaskList mainTaskList = new TaskList();
        Storage mainStorage = new Storage(mainTaskList);
        mainStorage.loadFromFile();

        Ui mainUi = new Ui();

        mainUi.greetUser();

        label:
        while (true) {
            String command = mainUi.getNextTask();

            String[] toFindFirstWord = Parser.parse(command, ParseFunctions.SPLIT_ALL); // take a comment

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
                    break;
                }
                case "unmark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord, mainTaskList);
                    completedTask.setCompletion();
                    mainUi.printReply("unmark", completedTask);
                    break;
                }
                case "delete": {
                    Task toDelete = getTaskForMarking(toFindFirstWord, mainTaskList);

                    String[] parsed = Parser.parse(command, ParseFunctions.TODO);
                    mainStorage.deleteTask(Integer.parseInt(parsed[1]));

                    mainUi.printReply("delete", toDelete);
                    break;
                }
                case "deadline": {
                    String[] parsed = Parser.parse(command, ParseFunctions.DEADLINE);
                    Task newDeadline = new Deadline(parsed[1], LocalDate.parse(parsed[2]));
                    mainStorage.addTask(newDeadline);
                    mainUi.printReply("deadline", newDeadline);
                    break;
                }
                case "event": {
                    String[] parsed = Parser.parse(command, ParseFunctions.DEADLINE);
                    Task newEvent = new Event(parsed[1], LocalDate.parse(parsed[2]), LocalDate.parse(parsed[3]));
                    mainStorage.addTask(newEvent);
                    mainUi.printReply("event", newEvent);
                    break;
                }
                case "todo": {
                    try {
                        String[] parsed = Parser.parse(command, ParseFunctions.TODO);
                        ToDo newToDo = new ToDo(parsed[1]);
                        mainStorage.addTask(newToDo);
                        mainUi.printReply("todo", newToDo);
                        break;
                    }
                    catch (EmptyDescriptionException e) {
                        System.out.println("  Add an argument");
                    }
                }
                case "list":
                    for (int i = 0; i < mainTaskList.countTasks(); i++) {
                        System.out.println("  " + String.valueOf(i + 1) + ". " + mainTaskList.getTaskAtIndex(i));
                    }
                    break;
                default:
                    mainUi.printReply(first);
                    break;
            }
        }
    }
}
