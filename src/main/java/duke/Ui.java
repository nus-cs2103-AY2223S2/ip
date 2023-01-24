package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private Parser parser= new Parser();

    public Ui() {
        System.out.println("Echo! I'm Bond.");
        System.out.println(".....");
    }

    public void nextMission(TaskList l) {
        boolean check = true;

        while (check) {
            System.out.println("Awaiting command");
            check = this.parser.command(sc.nextLine(), l);
        }
    }
}
