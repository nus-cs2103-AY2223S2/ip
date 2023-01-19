import java.util.*;
public class Duke {
    private static String[] tasks = new String[100];

    public void awaitInstruction(){
        System.out.println("Hello I am Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (answer.equals("bye")){
            System.out.println("Goodbye");
        } else{
            System.out.println(answer);
        }

    }

    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Duke duke = new Duke();
        duke.awaitInstruction();
    }

}
