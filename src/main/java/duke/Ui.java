package duke;
public class Ui {
    public Ui() {
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "______________________________________\n"
                + "Hey there buddy! I'm Duke. Your Personal Task Assistant!\n"
                + "What can I do for you today?\n"
                + "______________________________________\n";
        System.out.print(greeting);
    }

    public void showResult(String textToDisplay) {
        System.out.print(textToDisplay);
    }
}
