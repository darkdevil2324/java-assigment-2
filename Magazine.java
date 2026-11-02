public class Magazine extends Item {
    public Magazine(int id, String title) { super(id, title); }

    @Override  // Method overriding (different fee than Book)
    public double lateFee(int days) { return days * 1.0; }

    @Override
    public String type() { return "Magazine"; }
}
