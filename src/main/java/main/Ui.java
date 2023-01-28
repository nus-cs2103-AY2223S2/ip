package main;
import java.util.Scanner;

public class Ui {
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";
    public void showLine(){
        System.out.println(separator);
    }

    public void showLoadingError(){
        System.out.println("Unable to load tasks from storage");
    }

    public void welcomeMessage() {
        String logo =
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠉⢳⠀⢰⠉⠉⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠒⡎⠀⢨⠗⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⢀⣴⣶⣤⣤⣀⠀⠀⣀⣠⣽⣀⣼⡴⠒⠦⠴⣶⠟⠛⠻⣦⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⣾⠁⠀⠀⠈⠙⠟⡻⠿⣇⣀⣯⡟⣁⣠⡴⢲⡋⢧⠀⠀⢿⡄⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⣿⢀⠀⠀⠀⠀⠀⠳⡤⣯⣈⣿⣏⠹⢧⢿⣨⣧⣼⣄⡴⠾⡇⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⣿⠏⠀⠀⠀⠀⠀⠈⠧⠴⠣⠤⠏⠳⠼⣿⠷⣿⣧⡼⢧⣴⣇⠀⠀⠀ \n" +
                        "⠀⠀⠀⣼⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠧⣤⡗⢺⣄⣼⠀⠀⠀ \n" +
                        "⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠓⢚⣠⣽⣧⣤⣤ \n" +
                        "⠐⠛⠛⣿⠛⠃⠀⠀⣾⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣶⠀⠀⠀⣀⣸⡇⠀⠀ \n" +
                        "⠀⠀⣤⠼⣷⠒⠀⠀⠈⠉⠀⠀⠀⠠⣞⣓⡆⠀⠀⠀⠉⠉⠀⠀⠀⢨⡿⠉⠛⠁ \n" +
                        "⠀⠀⠀⢀⣹⣷⣞⣁⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⢀⣹⡟⠷⢦⡄⠀ \n" +
                        "⠀⠀⠐⠋⣱⠟⠉⠙⢳⣶⣤⣤⣀⣀⣀⣀⣀⣀⣀⣀⣤⣴⠶⠛⠁⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⠻⣆⣀⠀⣀⣹⣾⢇⣩⡽⠿⢿⣍⠉⢻⡍⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⢸⡏⠉⠉⣩⣿⣿⣿⡟⠀⠀⠀⢻⣿⣿⣷⠟⠛⠷⣦⡀⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⠀⢙⣻⣿⣽⣏⣀⣸⡇⠀⠀⠀⠸⣇⣀⣿⡄⠀⠀⠘⣿⡀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⢰⡟⠉⠁⣿⡿⠿⠿⢿⣄⠀⢀⣴⠿⠿⢿⡇⠀⠀⢀⣿⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⠘⠿⢦⣤⣼⣷⣦⣤⣤⣭⣿⣯⣥⣤⣤⣼⣧⣤⣴⠿⠃⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";

        System.out.println(logo);
        System.out.println(separator);
        System.out.println("   Hello I'm hachi\n   What can I do for you today?");
        System.out.println(separator);
        System.out.println("   hachi can do these for you" +
                "\n     list                             | View your to-do list" +
                "\n     todo \"task\"                      | Add a task to your to-do list" +
                "\n     deadline \"task\" /by \"yyyy-mm-dd\" | Add a task to complete by the specified deadline" +
                "\n     event \"event\" /from \"yyyy-mm-dd\" | Add an event on the specified date" +
                "\n     /to \"yyyy-mm-dd\"" +
                "\n     mark \"num\"                       | Mark the (num)th item in your list as completed"+
                "\n     unmark \"num\"                     | Mark the (num)th item in your list as uncompleted" +
                "\n     bye                              | Quit hachi.Hachi\n");
    }


    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
