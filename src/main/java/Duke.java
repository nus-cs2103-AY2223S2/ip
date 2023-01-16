import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I am your anime waifu!"); 
        System.out.println("What can I do for you my husbando?");        
        
        System.out.println(" (*_*)");        
        System.out.println("|(   )|");        
        System.out.println("  |-|");        

        Scanner scan = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();

        
        System.out.println("_____");        
        String input = scan.nextLine();
        while(true) {
            if (input.equals("bye")) {break;}
            else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n",i+1,tasks.get(i));
                }
            } else {
                System.out.printf("added: %s%n", input);
                tasks.add(input);
            }
            System.out.println("_____");        
            input = scan.nextLine();
        }
        System.out.println("Bye! Hope to see you again <3!");        
        
    }
}
