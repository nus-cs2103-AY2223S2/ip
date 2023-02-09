package duke;

import java.io.Serializable;
import java.util.ArrayList;

public class Ui implements Serializable{

    private ArrayList<String> statementList = new ArrayList<>();
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
    }

    public void addStatement(String input) {
        this.statementList.add(input) ;
    }

    public ArrayList<String> getStatements() {
        return statementList;
    }

    public void clearStatements() {
        statementList = new ArrayList<>();
    }

}
