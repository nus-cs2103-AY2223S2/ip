import java.util.Scanner;

public class Ui {

    private String name;
    private Scanner sc;


    public Ui(String name) {
        this.name = name;
        sc = new Scanner(System.in);
    }

    public void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to: \n" + logo);
        System.out.println("Hello! I'm " + name + " !");
    }



}
