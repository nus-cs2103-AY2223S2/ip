package views;

public class UI {
    private static final String DATE_FORMAT = "dd/mm/yyyy";

    public void printInitMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                + logo
                + ", your personal assistant.\n");
    }

    private String indentString(String s) {
        return "\n\t" + s;
    }

    public void printCommands() {
        System.out.println(
                "Here is the list of available commands I can do:"
                        + indentString("bye")
                        + indentString("todo [name]")
                        + indentString("deadline [name] /by [" + DATE_FORMAT + "]")
                        + indentString("event [name] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]" )
                        + indentString("list")
                        + indentString("mark [index]")
                        + indentString("unmark [index]")
                        + indentString("delete [index]")
                        + indentString("bye"));
    }

}
