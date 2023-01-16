import java.util.Scanner;
import java.util.ArrayList;

class Task {
    boolean done;
    String name;
    public Task(String n) {
        name = n;
    }
    
    public void mark() {
        System.out.println("Nice! I've marked this task as done:");
        this.done = true;
        System.out.println(this.toString());
    }
    public void unmark() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.done = false;
        System.out.println(this.toString());
    }
    public String toString(){
        return String.format("[%s] %s",this.done ? "X" : " ", this.name);
  }
}


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I am your anime waifu!"); 
        System.out.println("What can I do for you my husbando?");        
        
        System.out.println(" (*_*)");        
        System.out.println("|(   )|");        
        System.out.println("  |-|");        

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println("_____");        
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i=0;i<tasks.size();i++) System.out.printf("%d. %s%n",i+1,tasks.get(i).toString());
            }
            
            else if (input.length() >= 5 && input.substring(0, 5).equals("mark ")) {
                try{
                    int index = Integer.parseInt(input.substring(5));
                    if (index > tasks.size() || index <= 0) System.out.println("wrong index");
                    else {
                        tasks.get(index-1).mark();
                    }
                }
                catch (NumberFormatException ex){
                    System.out.println("please mark corresponding task number");
                }
            }
            
            else if (input.length() >= 7 && input.substring(0, 7).equals("unmark ")) {
                try{
                    int index = Integer.parseInt(input.substring(7));
                    if (index > tasks.size() || index <= 0) System.out.println("wrong index");
                    else {
                        tasks.get(index-1).unmark();
                    }
                }
                catch (NumberFormatException ex){
                    System.out.println("please mark corresponding task number");
                }
            }
            
            else {
                System.out.printf("added: %s%n", input);
                tasks.add(new Task(input));
            }
            System.out.println("_____");        
            input = scan.nextLine();
        }
        System.out.println("Bye! Hope to see you again <3!");        
    }
}
