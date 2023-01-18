public class Duke {
    public static void main(String[] args) {
        String logo = "   _____  _  _                      \n" +
                "  / ____|| |(_)                     \n" +
                " | |     | | _  _ __   _ __   _   _ \n" +
                " | |     | || || '_ \\ | '_ \\ | | | |\n" +
                " | |____ | || || |_) || |_) || |_| |\n" +
                "  \\_____||_||_|| .__/ | .__/  \\__, |\n" +
                "               | |    | |      __/ |\n" +
                "               |_|    |_|     |___/ ";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Takes in a command and attempts to perform it, if valid.
     * @param   command a string containing the command entered by the user
     * @return          true if programme should continue accepting further commands, else false
     */
    private static boolean parseCommand(String command) {
        switch (command) {
            case "bye":
                prettyPrint("Hope I helped. Goodbye!");
                return false;
            default:
                prettyPrint(command);
                return true;
        }
    }

    /**
     * Prints out the specified string with a prepended ">>> ".
     * @param output the output to be printed
     */
    private static void prettyPrint(String output) {
        System.out.println(">>> " + output);
    }
}
