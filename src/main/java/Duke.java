import exception.EmptyDescException;
import exception.EmptyStorageException;
import exception.UnknownCommandException;
import task.Task;
import task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (EmptyStorageException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        this.ui.welcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr;
            inputArr = input.split(" ");
            if (inputArr[0].equals("bye")) {
                ui.printBye();
                break;
            } else if (inputArr[0].equals("list")) {
                ui.printTaskList(taskList);
            } else if (inputArr[0].equals("mark")) {
                try {
                    ui.printMarkStatus(taskList, inputArr[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (inputArr[0].equals("unmark")){
                try {
                    ui.printUnMarkStatus(taskList, inputArr[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                try {
                    Task t = taskList.addTasks(inputArr, inputArr[0]);
                    ui.printAddTask(this.taskList, t);
                } catch (EmptyDescException | IOException e) {
                    e.printStackTrace();
                }
            } else if (inputArr[0].equals("delete")) {
                int inputIndex = Integer.parseInt(inputArr[1]);
                try {
                    Task t = taskList.deleteTasks(inputIndex);
                    ui.printDelete(this.taskList, t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    throw new UnknownCommandException("UnknownCommandException");
                } catch (UnknownCommandException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();

    }


}
