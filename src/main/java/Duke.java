public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        bidFarewell();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        makeSeperation();
    }

    public static void bidFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
        makeSeperation();
    }

    public static void makeSeperation() {
        System.out.println("____________________________________________________________");
    }
}
