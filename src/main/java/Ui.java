import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public String getCommand() {
        return sc.nextLine();
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:\n");
        int listcount = 1;
        for (Task element: Task.tasks) {
            if (element != null) {
                System.out.println("" + listcount + "." + element);
                listcount += 1;
            }
        }
    }


    public void printError(String e) {
        System.out.println(e + "\n");
    }
}
