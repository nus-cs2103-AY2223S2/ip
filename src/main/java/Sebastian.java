import java.util.Scanner;

public class Sebastian {
    public static void main(String[] args) {
        greet();
        Scanner scan = new Scanner(System.in);
        String instruction = scan.nextLine();
        while (!instruction.equals("bye")) {
            echo(instruction);
            instruction = scan.nextLine();
        }
        exit();
    }

    private static void greet() {
        lineBreak();
        System.out.println(space() + "Greetings, I'm Sebastian");
        System.out.println(space() + "I'm at your service");
        lineBreak();
    }

    private static void exit() {
        lineBreak();
        System.out.println(space() + "Bye. It's my pleasure to serve you");
        lineBreak();
    }

    private static void echo(String instruction){
        lineBreak();
        System.out.println(space() + instruction);
        lineBreak();

    }

    private static void lineBreak(){
        String line = "-";
        String res = space() + line.repeat(80);
        System.out.println(res);
    }

    private static String space() {
        String space = " ";
        return space.repeat(5);
    }
}
