import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.*;
import helpers.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        UI ui = new UI(new TaskList());
        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String instruction;

        while (!ui.isTerminated()) {
            instruction = sc.nextLine();
            ui.process(instruction);
        }
    }
}


