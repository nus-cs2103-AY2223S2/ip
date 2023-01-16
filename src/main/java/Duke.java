import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I am your anime waifu!"); 
        System.out.println("What can I do for you my husbando?");        
        
        System.out.println(" (*_*)");        
        System.out.println("|(   )|");        
        System.out.println("  |-|");        
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while(true) {
            if(input.equals("bye")) {break;}
            System.out.println(input);
            input = scan.nextLine();
        }
        System.out.println("Bye! Hope to see you again <3!");        
        
    }
}
