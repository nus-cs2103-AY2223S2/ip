package duke.parser;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Arguments container. Each option is stored in a map with its respective 
 * tokens, e.g /priority 5 will return a string array of { "5" } when retrieved
 * with getLabelledArgument()
 */
public class Arguments implements Iterable<Map.Entry<String, String[]>> {
    private final Map<String, String[]> arguments;

    private final String[] excess;

    private final String[] original;

    public Arguments(Map<String, String[]> arguments, String[] excess, String[] original) {
        this.arguments = arguments;
        this.excess = excess;
        this.original = original;
    }

    public String[] getLabelledArgument(String label) {
        return arguments.get(label);
    }

    public Arguments addLabelledArgument(String label, String[] contents) {
        arguments.putIfAbsent(label, contents);
        return this;
    }

    public boolean hasLabelledArgument(String label) {
        return arguments.containsKey(label);
    }

    public String[] getExcessArguments() {
        return excess;
    }

    public String[] getOriginalArgs() {
        return original;
    }

    public int getNumOfArgs() {
        return arguments.size() + (excess == null ? 0 : 1);
    }

    private class ArgumentsIterator implements Iterator<Map.Entry<String, String[]>> {
        private final Iterator<Map.Entry<String, String[]>> argsIterator = arguments.entrySet().iterator();

        private boolean shouldReturnExcess = true;

        @Override
        public boolean hasNext() {
            return argsIterator.hasNext() || shouldReturnExcess;
        } 

        @Override
        public Entry<String, String[]> next() {
            if (argsIterator.hasNext()) {
                return argsIterator.next();
            } else if (shouldReturnExcess && excess != null) {
                shouldReturnExcess = false;
                return Map.entry("excess", excess);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public Iterator<Entry<String, String[]>> iterator() {
        return new ArgumentsIterator();
    }

    @Override
    public String toString() {
        String str0 = arguments.entrySet()
            .stream()
            .map(entry -> String.format("%s: [%s]", entry.getKey(), String.join(", ", entry.getValue())))
            .collect(Collectors.joining("\n"));

        if (excess != null) {
            str0 += String.format("\nexcess: [%s]", String.join(", ", excess));
        }

        return str0;
    }
}
