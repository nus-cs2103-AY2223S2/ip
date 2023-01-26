import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }

    /**
     * Executes the Duke chatbot. 
     */
    public void run() {

        ui.introMessage();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

        while (!parser.checkEnd(userInput)) {
            if (parser.checkListRequest(userInput)) {
                tasks.printContents();
            } else if (parser.checkMarkRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                tasks.markTask(itemNo);
            } else if (parser.checkDeleteRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                tasks.deleteTask(itemNo);
            } else if (parser.checkFindRequest(userInput)) {
                String toBeFound = userInput.substring(5);
                tasks.find(toBeFound);
            }
            else {
                String[] terms = userInput.split(" ");
                Task newTask;
                if (terms[0].equals("todo")) {
                    try {
                        if (terms.length == 1) {
                            String error = "The description of a todo cannot be empty";
                            throw new DukeException(error);
                        }
                        newTask = new Todo(userInput.substring(5));
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                } else if (terms[0].equals("deadline")) {
                    String[] splitBySlash = userInput.split("/");
                    try {
                        if (splitBySlash.length != 2) {
                            throw new DukeException("Wrong format for deadline Task");
                        }
                        String description = splitBySlash[0].substring(9);
                        String by = splitBySlash[1].substring(3);
                        newTask = new Deadline(description, by);
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }

                } else if (terms[0].equals("event")) {
                    String[] splitBySlash = userInput.split("/");
                    try {
                        if (splitBySlash.length != 3) {
                            throw new DukeException("Wrong format for event Task");
                        }
                        String description = splitBySlash[0].substring(6);
                        String from = splitBySlash[1].substring(5);
                        String to = splitBySlash[2].substring(3);
                        newTask = new Event(description, from, to);
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                } else {
                    try {
                        throw new DukeException("I don't know what that means.");
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                }

            }
            userInput = scan.nextLine();
        }
        storage.addToFile(tasks);
        ui.endMessage();
    }
}
