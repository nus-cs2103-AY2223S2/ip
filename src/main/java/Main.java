import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IndexOutOfBoundsException {
        Scanner sc = new Scanner(System.in);
        Lulu lulu = new Lulu();
        lulu.greet();
        if (Save.isSavePresent()) {
            Save.readSave(lulu);
        }

        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] command = s.split(" ");
            if (command[0].compareTo("bye") == 0) {
                lulu.exit();
                break;
            }
            lulu.run(s);
        }
    }
}
