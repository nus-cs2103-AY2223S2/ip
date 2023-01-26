package duke;

public class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        System.out.println(
                "Please follow the date format: yyyy-MM-dd hh:mm (e.g. 2019-10-15 18:00), otherwise your input will be invalid.");
        printDashes();
    }

    public void printDashes() {
        System.out.println("****************************************");
    }

    public void println(String s) {
        System.out.println(s);
    }
}