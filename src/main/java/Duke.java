import java.util.*;

public class Duke {
    private Scanner scanner;
    private Task task;

    public Duke(Scanner scanner) {
        this.scanner = scanner;
        this.task = new Task();
    }

    public void start() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while(scanner.hasNext()) {
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                this.task.bye();
                scanner.close();
                return;
            }

            else if(input.equals("list")) {
                this.task.list();

            }
            else if(input.contains("mark")) {
                String[] splitInput = input.split(" ");
                int inputIndex = Integer.parseInt(splitInput[1]);
                this.task.mark(inputIndex);
            }
            else if(input.contains("unmark")) {
                String[] splitInput = input.split(" "); 
                int inputIndex = Integer.parseInt(splitInput[1]);
                this.task.unmark(inputIndex);
            }
            else {
                this.task.add(input);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke duke = new Duke(scanner);
        duke.start();
    }
}
