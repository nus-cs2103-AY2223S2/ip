package commands;

import data.MyData;

public class List extends Commands {
    public void execute(MyData data) {
        System.out.print(getLine());
        for (int i = 0; i < data.len(); i++) {
            System.out.printf("    %d. %s%n", i + 1, data.getData(i));
        }
        System.out.print(getLine());
    }
}
