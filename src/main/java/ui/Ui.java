package ui;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public void welcomeMsg(){
        System.out.println(logo + "Hello I am Duke\nWhat can I do for you?");
    }
    public void errMsg(String error){
        System.out.println(error);
    }
    public void showLine(){
        System.out.println("___________________");
    }
}
