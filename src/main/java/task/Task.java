package task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import util.Util;

public abstract class Task implements Serializable {

    protected final String desc;
    protected final boolean done;
    
    protected Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    @Override
    public String toString() {
        char mark = ' ';
        if (done) {
            mark = 'X';
        }

        return String.format("[%c] %s", mark, this.desc);
    }

    public abstract boolean hasDate(LocalDate date);

    public boolean hasKeywords(Set<String> keywords) {
        Set<String> descKeywords = Util.splitWhitespace(desc).stream()
                .map(s -> s.toLowerCase())
                .collect(Collectors.toCollection(HashSet::new));

        descKeywords.retainAll(keywords);
        return !descKeywords.isEmpty();
    }

    public abstract Task markDone();

    public abstract Task markNotDone();
}
