/**
 * Project Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying as many
 * Java and SE techniques as possible along the way.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

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

    /**
     * Prints out the greeting message and a line separation
     */
    public static void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        makeSeperation();
    }

    /**
     * Prints out the goodbye message and a line separation
     */
    public static void bidFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
        makeSeperation();
    }

    /**
     * Prints out a line separation
     */
    public static void makeSeperation() {
        System.out.println("____________________________________________________________");
    }
}
