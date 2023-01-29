import command.Command;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * <h1>Duke Task Manager</h1>
 * The Duke program a that the creation of tasks such as Todo, Event and
 * Deadline. The program allows to listing of those added tasks and mark them as
 * done and not done, it also allows deletion of wrongly added task.
 *
 * @author Leng Wei Cong, Justin
 */
public class Duke {
	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	/**
	 * Constructor
	 * 
	 * @param filePath the path to the text file that stores the tasks
	 * @return nothing
	 */
	public Duke(String filePath) {
		this.ui = new Ui();
		this.storage = new Storage(filePath);
		this.tasks = new TaskList(this.storage.load());
	}

	/**
	 * Runs Duke.
	 * 
	 * @return nothing
	 */
	public void run() {
		this.ui.showWelcome();
		this.handleRequest();
		this.ui.showExit();
	}

	/**
	 * Handles the request from user input.
	 * 
	 * @return nothing
	 */
	public void handleRequest() {
		while (true) {
			try {
				String fullcommand = ui.readCommand();
				Command command = Parser.parse(fullcommand);

				// Breaks when is an exit command
				if (command.isExit()) {
					break;
				}

				// Executes command
				command.execute(tasks, ui, storage);

			} finally {
				ui.showDivider();
			}
		}
	}

	/**
	 * Prints whatever the user inputs.
	 * 
	 * @param input the user input
	 * @return nothing
	 */
	public void echo(String input) {
		ui.showMessage(input);
	}

	/**
	 * This is the main method which makes use of run method.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		new Duke("data/duke.txt").run();
	}
}
