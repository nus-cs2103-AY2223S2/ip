import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private final static String FILE_PATH = "src/data/tasks.txt";
    private final static String DIRECTORY_PATH = "src/data";
    private Ui ui;
    private Storage storage;
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH, DIRECTORY_PATH);

    }
    public void run() throws IOException {
        ui.printWelcomeMsg();
        ui.printLine();
        String str = ui.readCommand();
        TaskList taskList = storage.readData();
        while (!str.equals("bye")) {
            String[] arr = str.split(" ", 2);
            try {
                switch (arr[0]) {
                    case "list":
                        taskList.listTask(arr);
                        break;
                    case "mark":
                        taskList.markTask(true, arr, ui);
                        storage.writeData(taskList);
                        break;
                    case "unmark":
                        taskList.markTask(false, arr, ui);
                        storage.writeData(taskList);
                        break;
                    case "todo":
                        ToDo toDo = ToDo.generate(arr);
                        taskList.addTask(toDo);
                        storage.writeData(taskList);
                        ui.printAddTaskMsg(taskList, toDo);
                        break;
                    case "deadline":
                        DeadLine deadLine = DeadLine.generate(arr);
                        taskList.addTask(deadLine);
                        storage.writeData(taskList);
                        ui.printAddTaskMsg(taskList, deadLine);
                        break;
                    case "event":
                        Event event = Event.generate(arr);
                        taskList.addTask(event);
                        storage.writeData(taskList);
                        ui.printAddTaskMsg(taskList, event);
                        break;
                    case "delete":
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format, please give numbers");
                        }
                        taskList.deleteTask(arr[1], ui);
                        storage.writeData(taskList);

                        break;
                    default:
                        throw new DukeException("Unknown format");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please provide numbers");
            } catch (Exception e) {
                System.out.println("Unknown error");
            }
            str = ui.readCommand();
        }
        ui.printByeMsg();
    }
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}
