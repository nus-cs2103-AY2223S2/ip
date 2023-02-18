package duke.util;

import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

public class DatetimeComparator implements Comparator<Pair<LocalDateTime, Task>> {
    @Override
    public int compare(Pair<LocalDateTime, Task> a, Pair<LocalDateTime, Task> b) {
        return a.getKey().compareTo(b.getKey());
    }
}