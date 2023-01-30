public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.showLine();
        System.out.println("Hiii Im\n" + logo);
        System.out.println("What can I do for you hmm?");
        this.showLine();
    }

    public void showLine() {
        String line = "-------------------------------";
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("OHOH the input cannot make it!");
    }

}
