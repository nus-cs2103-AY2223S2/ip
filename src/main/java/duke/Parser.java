package duke;

public class Parser {
    private String[] args;

    public Parser(String argLine){
        this.args = argLine.split(" ", 2);
    }

    public Command parseArgs(){
        return new Command(this.args[0], this.args[1]);
    }
}
