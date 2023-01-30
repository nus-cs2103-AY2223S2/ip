import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Storage storage = new Storage("./ip-data/Ip-data.txt");
        Scanner sc = new Scanner(System.in);
        ToDoList ls;

        ls = Duke.startUp(storage);
        Duke.input(sc, ls);
        Duke.shutDown(storage, ls);
    }

    private static ToDoList startUp(Storage storage) {
        try {
            UI.welcomeMsg();
            return storage.load();
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    private static void shutDown(Storage storage, ToDoList ls) {
        try {
            storage.save(ls);
            UI.exitMsg();
        } catch (Exception e) {

        }
    }

    private static void input(Scanner sc, ToDoList ls) {
        boolean isDone = false;
        while (!isDone) {
            try {
                String[] input = Parser.inputHandler(sc.nextLine(), " ", 2, 1);
                isDone = Parser.commandHandler(input, ls);
            } catch (Exception e) {
                UI.errorMsg(e.getMessage());
            }
        }
    }
}
