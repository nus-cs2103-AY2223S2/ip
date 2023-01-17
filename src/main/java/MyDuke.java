public class MyDuke {

    public void init() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }    

    public void quit() {
        System.out.println("Bye");
    }

    public void exec(String[] tokens) {
        String feedback = "MyDuke : ";
        System.out.println(tokens[0]);
    }
}
