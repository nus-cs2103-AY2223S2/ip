import java.util.*;
public class Duke {
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

        while(true) {
            String command = input.nextLine();
            System.out.println(command);

            if(command.equals("bye")) {
                System.out.println("ByeBye! Come play with me again!");
                input.close();
                break;
            }
        }
    }
}
