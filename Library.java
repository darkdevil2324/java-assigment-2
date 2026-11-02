import java.io.IOException;
import java.nio.file.*;
import java.util.*;

// Collections (LinkedHashMap) + Java NIO (Files, Path) used here
public class Library {
    private final Map<Integer, Item> items = new LinkedHashMap<>();

    public void addItem(Item item) { items.put(item.getId(), item); }

    public Item find(int id) throws ItemNotAvailableException {
        Item item = items.get(id);
        if (item == null) throw new ItemNotAvailableException("No item with id " + id);
        return item;
    }

    public void borrow(int id, String member) throws ItemNotAvailableException {
        find(id).borrow(member);
    }

    public void giveBack(int id) throws ItemNotAvailableException {
        find(id).giveBack();
    }

    public Collection<Item> all() { return items.values(); }

    // NIO: save to file
    public synchronized void save(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Item i : items.values()) {
            lines.add(i.type() + "," + i.getId() + "," + i.getTitle() + "," + i.isAvailable());
        }
        Files.write(path, lines);
    }

    // NIO: load from file
    public void load(Path path) throws IOException {
        if (!Files.exists(path)) return;
        for (String line : Files.readAllLines(path)) {
            String[] p = line.split(",");
            int id = Integer.parseInt(p[1]);
            Item item = p[0].equals("Book") ? new Book(id, p[2]) : new Magazine(id, p[2]);
            if (p[3].equals("false")) {
                item.available = false;
                item.borrowedBy = "someone";
            }
            addItem(item);
        }
    }
}
