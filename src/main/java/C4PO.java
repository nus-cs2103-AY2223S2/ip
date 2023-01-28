
public class C4PO {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Parser parser;

    protected static final String FILE_P = "./data/tasks.txt";

    public C4PO(String filePath) {
        ui = new Ui();
        parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.printList();
        } catch (Exception e) {
            System.out.println(e);
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main loop of the bot interaction
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
                System.out.println(e);
            } finally {
                Ui.printDashLine(1);
            }


        }

    }

    public static void main(String[] args) {
        new C4PO(FILE_P).run();
    }
}
