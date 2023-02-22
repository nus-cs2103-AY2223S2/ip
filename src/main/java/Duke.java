
public class Duke {

    public static void run() {
        Ui.greet();
        boolean stillOn = true;
        Storage.loadData();
        while(stillOn) {
            Parser.makeSense(Ui.getInput());
            stillOn = Parser.parserStatus;
        }
    }

    public static void main(String[] args) {
        run();
    }
}