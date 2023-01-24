public class Ui {
    public void printText(String text) {
        System.out.printf("     %s\n", text);
    }

    public void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    public void printStartup() {
        String logo =
                " /\\_/\\\n" +
                        "( o.o )   ~meow~\n" +
                        " > ^ <";
        System.out.println(logo);
        printHorizontal();
        printText("Hello! I'm Duke");
        printText("What can I do for you?");
        printHorizontal();
    }
}
