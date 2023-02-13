package duke.util;
public class Ui {

    public Ui() {
        String name = "todo bot";
        System.out.println("Hello from " + name);
        System.out.println("------------------------------------");
        System.out.println("I can help you take care of your daily todos :)");
        System.out.println("There are 3 types of tasks I can handle.");
        System.out.println("------------------------------------");
        System.out.println("1. normal todo, format: ");
        System.out.println("   todo task");
        System.out.println("2. deadline, format: ");
        System.out.println("   deadline task /by yyyy-mm-dd");
        System.out.println("3. event, format: ");
        System.out.println("   event task /from yyyy-mm-dd /to yyyy-mm-dd");
        System.out.println("------------------------------------");
    }


}
