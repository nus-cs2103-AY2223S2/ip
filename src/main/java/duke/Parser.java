package duke;

public class Parser {
    private String[] args;

    public Parser(String argLine){
        this.args = argLine.split(" ", 2);
    }

    public Command parseArgs(){
        if (this.args.length < 2){
            return new Command(this.args[0], "");
        }
        return new Command(this.args[0], this.args[1]);
    }
}
