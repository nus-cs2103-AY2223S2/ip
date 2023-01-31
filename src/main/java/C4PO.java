
import src.main.c4po.*;

public class C4PO {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    protected static final String FILE_P = "./data/tasks.txt";

    public String introduction;


    /**
     * for JavaFX Tutorial, temp
     */
    public C4PO() {
        ui = new Ui();
        parser = new Parser();
        this.storage = new Storage(C4PO.FILE_P);
        StringBuilder intro = new StringBuilder();
        try {
            tasks = new TaskList(storage.load());
            intro.append(Ui.showLoadedMsg(true)).append("\n");
            intro.append(tasks.printList(true, true));
        } catch (Exception e) {
            //Ui.print(e.getMessage());
            intro.append(Ui.showLoadingError(true));
            tasks = new TaskList();
        }
        this.introduction = intro.toString();
    }

    /**
     * C4PO instance initialises with a filepath to the data storage file location
     * @param filePath
     */
    public C4PO(String filePath) {
        ui = new Ui();
        parser = new Parser();
        this.storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            tasks.printList(true);
        } catch (Exception e) {
            Ui.print(e.getMessage());
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the bot interaction, run this to start bot
     */
    public void run() {

        //Introduce the bot
        Ui.showIntroduction();

        //Main Loop
        //From here, append newly added Tasks to the file
        boolean isExit = false;
        while (!isExit) {
            try {
                String receive = ui.getNextInput(); //reads user input
                Command c = parser.parse(receive);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.print(e.getMessage());
                Ui.showCommandError();
            } finally {
                Ui.printDashLine(1);
            }
        }
    }

    /**
     * Starts the run of the bot
     * @param args
     */
    public static void main(String[] args) {
        //new C4PO(FILE_P).run();
    }





    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws BotException {

        boolean isExit = false;

        try {
            Command c = parser.parse(input);
            String response = c.execute(tasks, ui, storage, true);
            isExit = c.isExit();
            return response;
        } catch (Exception e) {
            throw new BotException(Ui.UNABLE_TO_UNDERSTAND_QUOTE
                    + "\n"
                    + Ui.showCommandError(true));
        }
    }
}
