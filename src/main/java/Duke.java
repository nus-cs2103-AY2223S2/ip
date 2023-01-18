import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is Duke at your service!");
        while(myObj.hasNext()) {
            String input = myObj.nextLine();
            if(input.equals("bye")) {
                System.out.println("See you again, thanks for visiting!");
                break;
            }
            System.out.println(input);
        }

    }
}
