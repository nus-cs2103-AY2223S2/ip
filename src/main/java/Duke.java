import java.util.*;
public class Duke {
    private static ArrayList<String> list = new ArrayList<>();

    //add items to list method
    public static void addItemsToList(String item) {
        list.add(item);
        System.out.println("I have added: " + item + "!");
    }

    //display items in list method
    public static void displayList() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
    public static void main(String[] args) {
        /*
        * logo credit: https://patorjk.com/software/taag/#p=testall&h=2&v=3&f=3D-ASCII&t=RubiRyo
        * dog image credit: ChatGPT
        * */
        String logo = "\n" +
                " ____  __ __ ____  ____ ____  __ __  ___  \n" +
                "|    \\|  |  |    \\|    |    \\|  |  |/   \\ \n" +
                "|  D  )  |  |  o  )|  ||  D  )  |  |     |\n" +
                "|    /|  |  |     ||  ||    /|  ~  |  O  |\n" +
                "|    \\|  :  |  O  ||  ||    \\|___, |     |\n" +
                "|  .  \\     |     ||  ||  .  \\     |     |\n" +
                "|__|\\_|\\__,_|_____|____|__|\\_|____/ \\___/ \n" +
                "                                          \n";
        String dogImage = "|\\___/|\n" +
                "| o o |\n" +
                " \\   /\n" +
                "  \\ /\n";

        System.out.println("Woof Woof!\n" + logo);

        String introMessage = "Woof! I'm RubiRyo the Japanese Spitz!\n" +
                                "Give me a command!";
        System.out.println(introMessage + "\n");
        Scanner input = new Scanner(System.in);

        //main logic loop
        loop: while(true) {
            String command = input.nextLine();
            switch(command) {
                case "list":
                    displayList();
                    break;
                case "bye":
                    System.out.println("ByeBye! Come play with me again!");
                    break loop;
                default:
                    addItemsToList(command);
            }
        }
    }
}
