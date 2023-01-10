package duke;

import java.util.Scanner;

public class Duke {
   
    void greetEcho() {

        Printable.greet();
        Scanner sc = new Scanner(System.in);
                
        while (true) {
            String echoWord = sc.next();    
            if (!echoWord.equals(Printable.TERMINATE)) {
                System.out.println(echoWord);
            } else {
                Printable.exit();
            }
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greetEcho();
    }
}
