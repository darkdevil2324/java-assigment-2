// Interface: any item that can be borrowed must follow this contract
public interface Borrowable {
    void borrow(String member) throws ItemNotAvailableException;
    void giveBack();
    boolean isAvailable();
}
