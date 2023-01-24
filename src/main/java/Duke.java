import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Command command;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        command = new Command();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() throws DukeException {
        this.ui.sendGreetingsMessage();
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                this.command.listTasksCommand(this.tasks);
            }
            else if (userInput.split(" ", 2)[0].equals("todo")) {
                this.tasks.addTask(new ToDo(this.parser.getTodoDescription(userInput)));
            }
            else if (userInput.split(" ", 2)[0].equals("deadline")) {
                this.tasks.addTask(new Deadline(this.parser.getDeadlineDescription(userInput),
                        this.parser.getDeadlineDate(userInput)));
            }
            else if (userInput.split(" ", 2)[0].equals("event")) {
                LocalDate[] eventDetails = this.parser.getEventDateDetails(userInput);
                this.tasks.addTask(new Event(this.parser.getEventDescription(userInput), eventDetails[0], eventDetails[1]));
            } else if (userInput.split(" ", 2)[0].equals("mark")) {
                this.tasks.markTask(this.parser.getTaskIndex(userInput));
            } else if (userInput.split(" ", 2)[0].equals("unmark")) {
                this.tasks.unmarkTask(this.parser.getTaskIndex(userInput));
            } else if (userInput.split(" ", 2)[0].equals("delete")) {
                this.tasks.removeTask(this.parser.getTaskIndex(userInput));
            }
            else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }
        storage.saveData(tasks);
        ui.sendGoodByeMessage();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/file.txt").run();
    }

}