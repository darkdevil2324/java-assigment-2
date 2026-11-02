public class Book extends Item {
    public Book(int id, String title) { super(id, title); }

    @Override  // Method overriding
    public double lateFee(int days) { return days * 2.0; }

    @Override
    public String type() { return "Book"; }
}
