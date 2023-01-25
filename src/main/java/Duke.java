import exception.EmptyDescException;
import exception.EmptyStorageException;
import exception.UnknownCommandException;

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
        //...
        this.ui.welcome();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr;
            inputArr = input.split(" ");
            if (inputArr[0].equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("See yer again RUFF!");
                System.out.println("____________________________________________________________");
                break;
            } else if (inputArr[0].equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list dawg:");
                taskList.printList();
                System.out.println("____________________________________________________________");
            } else if (inputArr[0].equals("mark")) {
                try {
                    taskList.markStatus(inputArr[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("____________________________________________________________");
                System.out.println("The task is marked, dawg");
                System.out.println("____________________________________________________________");
            } else if (inputArr[0].equals("unmark")){
                try {
                    taskList.unMarkStatus(inputArr[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("____________________________________________________________");
                System.out.println("Gotcha dawg, unmarked.");
                System.out.println("____________________________________________________________");
            } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                try {
                    taskList.addTasks(inputArr, inputArr[0]);
                } catch (EmptyDescException | IOException e) {
                    e.printStackTrace();
                }
            } else if (inputArr[0].equals("delete")) {
                int inputIndex = Integer.parseInt(inputArr[1]);
                try {
                    taskList.deleteTasks(inputIndex);
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
