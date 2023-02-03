import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.IOException;

public class Duke {

    Storage storage;
    Ui ui;
    TaskList list;
    Parser parser;
    final static String DEFAULT_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "duke.txt";

    Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.list = this.storage.load();
        } catch (Exception e) {
            this.ui.showLoadingError();
            this.list = new TaskList();
        }
        this.parser = new Parser();
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        ui.printEntry();
        while (!this.ui.isClosed) {
            String input = sc.nextLine();
            this.parser.parse(input, this.ui, this.list);
        }
        this.storage.save(this.list);
        sc.close();
    }

    public static void main(String[] args) throws IOException {
        new Duke(DEFAULT_PATH).run();
    }



}






