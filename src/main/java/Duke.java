import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        indent("Hi, I'm Duke ");
        indent("What can I do for you :) ?\n");
        line();

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while(!echo.equals("bye")){
            indent(echo + "\n");
            line();
            echo = scanner.nextLine();
        }
        indent("Bye. Hope to hear from you again!");
        line();
    }

    public static void indent(String txt){
        System.out.println("     " + txt);
    }

    public static void line(){
        System.out.println("_________________________________________________________________");
    }
}
