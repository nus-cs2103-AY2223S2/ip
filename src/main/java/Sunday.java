import command.Command;
import exceptions.SundayException;
import utilities.Parser;
import utilities.Ui;

public class Sunday {
    private Ui ui;
    public Sunday(String filepath) {
        this.ui = new Ui();
        try {
            Command.INITIALIZE.execute(filepath);
        } catch (SundayException e) {
            Ui.printException(e);
        }
    }

    private void run() {
        Command command = null;
        while (command != Command.BYE) {
            try {
                String[] fullCommand = ui.readCommand();
                command = Parser.parse(fullCommand);
                command.execute(fullCommand[1]);
            } catch (SundayException e) {
                Ui.printException(e);
            }
        }
        this.ui.close();
    }
    public static void main(String[] args) {
        new Sunday("../data/sunday.txt").run();
    }
}
