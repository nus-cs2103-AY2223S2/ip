public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Engine ENGINE = new Engine();

    public static void main(String[] args) {
        System.out.println(LOGO + "\nOh hello...\nWhat do you need now?\n");
        while (true) {
            if (!ENGINE.run())
                break;
        }
    }
}
