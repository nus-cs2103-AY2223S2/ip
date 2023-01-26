package duke;

public class Duke {
    private final Bot bot;
    private final Ui ui;

    public Duke() {
        this.bot = new Bot();
        this.ui = new Ui();

        try {
            bot.init();
            ui.displayGreeting();
        } catch (DukeException e) {
            ui.displayInitError();
        }
    }

    public void run() {
        boolean running = true;
        while (running & ui.hasUserInput()) {
            String input = ui.getUserInput();
            BotResult result = bot.process(input);
            ui.displayResponse(result.response);
            running = result.resultStatus != BotResult.BotStatus.Exit;
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
