import java.util.*;

public class Duke {
    Scanner scanner;
    List<String> list;

    public Duke(Scanner scanner) {
        this.scanner = scanner;
        this.list = new ArrayList<>();
    }

    public void start() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while(scanner.hasNext()) {
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                this.bye();
                scanner.close();
                return;
            }

            else if(input.equals("list")) {
                this.list();

            }
            else {
                this.add(input);
            }
        }
    }

    private void add(String input) {
        this.list.add(input);
        System.out.println("added: " + input);
    }

    private void bye() {
        System.out.println("Duke: " + "Bye" + ". Hope I never see you again!");
    }

    private void list() {
        System.out.println("list: ");
        for(int i=1; i<list.size() + 1; i++) {
            System.out.println(i + ". " + list.get(i-1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke duke = new Duke(scanner);
        duke.start();
    }
}
