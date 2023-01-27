<<<<<<<< HEAD:src/main/java/james/task/ToDo.java
package james.task;

import james.JamesException;
========
package seedu;
>>>>>>>> 9a38ae0 (Update testing classes for tasks):src/main/java/seedu/ToDo.java

public class ToDo extends Task {
    public ToDo(String description) throws JamesException {
        super(description);
        if (description.isEmpty()) {
            throw new JamesException("OOPS!!! The description of a todo task cannot be empty.");
        }
    }
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    @Override
    public String toStoreString() {
        return "T | " + super.toStoreString() + "\n";
    }

}

