package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);
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
