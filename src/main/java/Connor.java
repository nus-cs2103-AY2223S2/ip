import java.util.Scanner;

public class Connor {

    public static void main(String[] args) {
        String name = "Connor";
        System.out.println("Hello! I'm " + name + ", the android sent by Cyberlife");
        System.out.println("Please type in your command below.");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String inputCommand = sc.nextLine();
            System.out.println(inputCommand);
            if (inputCommand.toUpperCase().equals("BYE")) {
                System.out.println("It was a good session Hank, Bye.");
                sc.close();
                break;
            }
        }


    }
}
