package kude.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ItemList implements Serializable {
    private List<Item> items;

    public ItemList() {
        items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public Stream<Item> list() {
        return items.stream();
    }

    public boolean delete(Item item) {
        return items.remove(item);
    }

    public Optional<Item> get(int index) {
        if (index < 0 || index >= items.size()) {
            return Optional.empty();
        } else {
            return Optional.of(items.get(index));
        }
    }
}
