import java.io.FileNotFoundException;

public class Jarvis {

    private Storage storage;
    private ToDoList todolist;
    private Ui ui;
    private Parser parser;
    boolean isBye;

    public Jarvis (String dataPath) {
        storage = new Storage(dataPath);
        todolist = new ToDoList();
        ui = new Ui();
        parser = new Parser();
        isBye = false;

        // Print previous data
        try {
            storage.loadTasks(todolist);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    public void run() {
        AsciiArt asciiArt = new AsciiArt();
        asciiArt.printArt();

        System.out.println("\nPlease enter a command Mr Stark.");

        while(!isBye) {
            String line = ui.getNextCommand();
            isBye = parser.parse(line, todolist, storage);
        }
    }


    public static void main(String[] args) {
        new Jarvis("./data/jarvis.txt").run();
    }
}
