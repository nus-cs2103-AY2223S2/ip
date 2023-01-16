public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Duke.say("Hello from\n" + logo);
    }

    private static void say(String whatToSay) {
        String horizontalLine = "_".repeat(60);
        System.out.println(horizontalLine);
        System.out.println(whatToSay);
        System.out.println(horizontalLine + '\n');
    }
}
