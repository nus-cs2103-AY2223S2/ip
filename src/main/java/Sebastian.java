import java.util.Scanner;

public class Sebastian {
    private final TaskList tasks;

    private Sebastian() {
        this.tasks = new TaskList();
    }

    private void greet() {
        Utilities.lineBreak();
        System.out.println(Utilities.space() + "Greetings, I'm Sebastian");
        System.out.println(Utilities.space() + "I'm at your service");
        Utilities.lineBreak();
    }

    private void exit() {
        Utilities.lineBreak();
        System.out.println(Utilities.space() + "Bye. It's my pleasure to serve you");
        Utilities.lineBreak();
    }

    private void echo(String instruction){
        Utilities.lineBreak();
        System.out.println(Utilities.space() + instruction);
        Utilities.lineBreak();
    }

    private void addTask(String instruction) {
        Utilities.lineBreak();
        if(this.tasks.addTask(instruction)) {
            System.out.println(Utilities.space() + "added: " + instruction);
        } else {
            System.out.println("Failed to add a task");
        }
        Utilities.lineBreak();
    }

    public void showList() {
        Utilities.lineBreak();
        System.out.println(this.tasks);
        Utilities.lineBreak();
    }

    public static void main(String[] args) {
        Sebastian sebastian = new Sebastian();
        sebastian.greet();
        Scanner scan = new Scanner(System.in);
        String instruction = scan.nextLine();
        while(!instruction.equals("bye")) {
            switch (instruction){
                case "list":
                    sebastian.showList();
                    break;
                default:
                    sebastian.addTask(instruction);
            }
            instruction = scan.nextLine();
        }
        sebastian.exit();
    }
}
