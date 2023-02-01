package duke;

import java.util.Scanner;

/**
 * Github id: adam07018
 * @author Lu Chenyu
 */
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

    /**
     * Starting main method
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke a = new Duke();
        a.start();
    }

    /**
     * Starts chat with user
     * Format for tasks:
     * 1. todo {description}
     * eg. todo buy lunch
     * 2. deadline {description} /by {time}
     * eg. deadline return book /by 2019-10-15 1530
     * 3. event {description} /from {time} /to {time}
     * eg. event read book /from 2019-10-15 1530 /to 2020-12-11 1200
     *
     * @throws DukeException
     */
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
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

}
