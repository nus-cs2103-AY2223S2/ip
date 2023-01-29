import java.util.Scanner;

public class Duke {
    private TaskList list;
    private Storage s;
    private Parser parser;
    private Ui ui;

    Duke() {
        list = new TaskList();
        s = new Storage();
        parser = new Parser();
        ui = new Ui();
    }

    public static void main(String[] args) throws DukeException {
        Duke a = new Duke();
        a.start();
    }

    public void start() throws DukeException {
        Scanner sc = new Scanner(System.in);
        ui.sayHello();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                Command cmd = parser.parse(input);
                cmd.execute(list);
                if (cmd.isExit()) {
                    sc.close();
                    return;
                }
            } catch (DukeException e) {
                continue;
            }
        }
    }

}
