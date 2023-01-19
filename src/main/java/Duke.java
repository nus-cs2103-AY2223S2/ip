public class Duke {

    private static String div_open = "____________________________________________________________\n";
    private static String div_close = "____________________________________________________________\n" + "\n";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\n";
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        System.out.println(div_open + logo + greetings + div_close);
    }
}
