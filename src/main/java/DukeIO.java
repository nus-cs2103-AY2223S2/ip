
public interface DukeIO {
    String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String GREET = "Hello, I'm Ekud! What can I do for you?";
    String FAREWELL = "Bye. Always at your service.";
    String terminateString = "bye";

    String listString = "list";



    static String wrapContent(String content) {
        return "\t~~~\n\t" + content + "\n\t~~~";
    }
}
