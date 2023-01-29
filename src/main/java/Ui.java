import java.util.Scanner;

public class Ui {

    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getCommand() {
        return sc.nextLine();
    }

    public void displayOpening() {
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        System.out.println("| Your favourite personal assistant:  |");
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        displayLogo();
        displayIntro();
    }

    public void displayCommand(String command) {
        displayLine();
        command = "    " + command;
        System.out.println(command);
        displayLine();
    }

    public void displayError(AlfredException e) {
        displayLine();
        String output = "     " + e.getMessage();
        System.out.println(output);
        displayLine();
    }
    public void displayBye() {
        String command = "Bye. Hope to see you again soon!";
        displayCommand(command);
    }

    public void displayLogo() {
        System.out.println(" _____ __     ______ _____ ____ ___ ");
        System.out.println("|  -  |  |   |  ____|  _  |  __| _ \\     ");
        System.out.println("| | | |  |   | |___ | |_|_| |__|| | |  ");
        System.out.println("|  -  |  |___|  ___||  _ \\  |__||_| |");
        System.out.println("|_| |_| ____ |__|   |_| \\_|____|__ /   ");
    }

    public void displayIntro() {
        String intro = "Hello! I'm Alfred :>\n"
                + "How can I help you today?";
        displayLine();
        System.out.println(intro);
        displayLine();
    }
    public void displayLine() {
        System.out.println("    ____________________________________________________________");
    }
}
