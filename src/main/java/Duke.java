import java.util.Scanner;
import java.util.ArrayList;
public class Duke  {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        Ui ui = new Ui(list);









    }

    public void launch() {
        Duke duke = new Duke("");
    }

    public static void horizontalLine() {


        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }


        System.out.print("\n");

    }
}
