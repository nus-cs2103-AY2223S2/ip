import java.util.Scanner;

public class Duke {

    public static void echo(){
        Scanner myObj = new Scanner(System.in);
        String inp = myObj.nextLine();
        while (!inp.equals("bye")) {
            System.out.println(inp);
            inp = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        String name = "chatty bot";
        System.out.println("Hello from " + name);
        System.out.println("talk to me :)");
        echo();
    }



}
