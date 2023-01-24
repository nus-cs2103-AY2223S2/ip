package interfaces;

import java.time.LocalDate;

public interface Task {
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    LocalDate getDueDate();
    void setDueDate(LocalDate dueDate);
}
