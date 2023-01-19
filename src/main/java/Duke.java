import java.util.*;
public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();

    public Boolean awaitInstruction(){
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("___________________");
        if (answer.equals("bye")){
            System.out.println("Goodbye");
            return true;
        }
        if (answer.equals("list")){
            for (int i = 0; i < tasks.size(); i++){
                System.out.print((i+1) + ": " + tasks.get(i) + "\n");
            }
        }
        else{
            tasks.add(answer);
            System.out.println("added:" + answer);
        }
        System.out.println("___________________");
        return false;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke\nWhat can I do for you?");
        Duke duke = new Duke();
        boolean isBye = false;
        while (!isBye){
            isBye = duke.awaitInstruction();
        }
    }

}
