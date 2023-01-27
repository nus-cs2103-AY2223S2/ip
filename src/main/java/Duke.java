import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> toDoList = new ArrayList<>();
    private static void talk(){
        Scanner myObj = new Scanner(System.in);
        String inp = myObj.nextLine();
        String[] inpArr = inp.split(" ");
        while (!inp.equals("bye")) {
            if (inp.equals("list")) {
                list();
            } else if (inpArr[0].equals("mark")){
                toDoList.get(Integer.parseInt(inpArr[1])-1).markDone();
            } else if (inpArr[0].equals("unmark")){
                toDoList.get(Integer.parseInt(inpArr[1])-1).markUndone();
            } else {
                Task newTask = new Task(inp);
                toDoList.add(newTask);
                System.out.println("added: " + inp);
            }
            inp = myObj.nextLine();
            inpArr = inp.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(){
        for (int i = 0; i < toDoList.size(); i++){
            System.out.print(Integer.toString(i+1) + ". ");
            toDoList.get(i).printTask();
        }
    }

    public static void main(String[] args) {
        String name = "chatty bot";
        System.out.println("Hello from " + name);
        System.out.println("talk to me :)");
        talk();
    }



}
