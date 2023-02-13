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

<<<<<<< HEAD

=======
    public String startUpMsg() {
        String name = "todo bot";
        String helloMsg = "";
        helloMsg += "Hello from " + name + "\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "I can help you take care of your daily todos :)\n";
        helloMsg += "There are 3 types of tasks I can handle.\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "1. normal todo, format: \n";
        helloMsg += "   todo task\n";
        helloMsg += "2. deadline, format: \n";
        helloMsg += "   deadline task /by yyyy-mm-dd\n";
        helloMsg += "3. event, format: \n";
        helloMsg += "   event task /from yyyy-mm-dd /to yyyy-mm-dd\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "Here are some operations you can perform on the tasks\n";
        helloMsg += "1. mark taskNumber\n";
        helloMsg += "2. unmark taskNumber\n";
        helloMsg += "3. delete taskNumber\n";
        helloMsg += "4. find tasks\n";
        helloMsg += "5. snooze taskNumber newTaskDate\n";
        helloMsg += "6. list\n";
        helloMsg += "------------------------------------\n";
        return helloMsg;
    }
>>>>>>> branch-A-JavaDoc
}
