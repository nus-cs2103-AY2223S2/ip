package duke;

/**
 * The Duke class represents the chatbot application.
 */
public class Duke {
    private Bot bot;
    private Ui ui;

    /**
     * Constructs and initializes Duke.
     */
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

    /**
     * Begins and runs the bot's loop.
     */
    public void run() {
        boolean running =  true;
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
