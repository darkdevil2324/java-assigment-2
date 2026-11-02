// Base class (Inheritance) - Book and Magazine extend this
public abstract class Item implements Borrowable {
    protected int id;
    protected String title;
    protected boolean available = true;
    protected String borrowedBy = "";

    public Item(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }

    @Override
    public boolean isAvailable() { return available; }

    // synchronized so two threads cannot borrow the same item together
    @Override
    public synchronized void borrow(String member) throws ItemNotAvailableException {
        if (!available) {
            throw new ItemNotAvailableException("'" + title + "' is already borrowed by " + borrowedBy);
        }
        available = false;
        borrowedBy = member;
    }

    @Override
    public synchronized void giveBack() {
        available = true;
        borrowedBy = "";
    }

    // Subclasses must override these
    public abstract double lateFee(int days);
    public abstract String type();

    @Override
    public String toString() {
        return type() + " #" + id + " - " + title + (available ? " [Available]" : " [Borrowed by " + borrowedBy + "]");
    }
}
