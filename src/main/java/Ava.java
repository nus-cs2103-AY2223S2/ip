import java.util.Scanner;
public class Ava {
    public static void main(String[] args) {
        AvaUI a = new AvaUI();
        a.displayIntro();
        boolean done = false;
        while (!done) {
            Scanner myObj = new Scanner(System.in);
            a.ask();
            String input = myObj.nextLine();
            if (input.equals("bye")){
                done = true;
                a.displayExit();
            } else {
                a.displayOutput(input);
            }
        }
    }
}
