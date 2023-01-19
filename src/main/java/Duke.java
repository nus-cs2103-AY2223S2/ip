import java.util.*;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public Boolean awaitInstruction(){
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
ok
        System.out.println("___________________");
        if (answer.startsWith("mark")){
            String wordArr[] = answer.split(" ");
            System.out.println(Arrays.toString(wordArr));
            String index = wordArr[1];
            markTask(index);
        }
        else if (answer.startsWith("unmark")){
            String wordArr[] = answer.split(" ");
            String index = wordArr[1];
            unmarkTask(index);
        }
        else if (answer.equals("bye")){
            System.out.println("Goodbye");
            return true;
        }
        else if (answer.equals("list")){ //return list of tasks
            printTaskList();
        } else { //else new task
            Task task = new Task(answer);
            addTask(task);
        }
        System.out.println("___________________");
        return false;
    }
    private void addTask(Task task){
        tasks.add(task);
        System.out.println("added:" + task.getDescription());
    }

    private void printTaskList(){
        for (int i = 0; i < tasks.size(); i++){
            Task task = tasks.get(i);
            System.out.print((i+1) + "." + "[" + task.getStatusIcon() + "] " + task.getDescription() + "\n");
        }
    }

    private void markTask(String index){
        int i = Integer.parseInt(index);
        Task task = tasks.get(i - 1);
        task.updateState();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[" + task.getStatusIcon() + "] " + task.getDescription() + "\n");
    }

    private void unmarkTask(String index){
        int i = Integer.parseInt(index);
        Task task = tasks.get(i - 1);
        task.updateState();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("[" + task.getStatusIcon() + "] " + task.getDescription() + "\n");
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
