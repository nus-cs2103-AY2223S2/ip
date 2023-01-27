import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<String> toDoList = new ArrayList<>();
    private static void talk(){
        Scanner myObj = new Scanner(System.in);
        String inp = myObj.nextLine();
        while (!inp.equals("bye")) {
            if (inp.equals("list")){
                list();
            }
            else {
                toDoList.add(inp);
                System.out.println("added: " + inp);
            }
            inp = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(){
        for (int i = 0; i < toDoList.size(); i++){
            System.out.println(Integer.toString(i+1) + ". " + toDoList.get(i));
        }
    }

    public static void main(String[] args) {
        String name = "chatty bot";
        System.out.println("Hello from " + name);
        System.out.println("talk to me :)");
        talk();
    }



}
