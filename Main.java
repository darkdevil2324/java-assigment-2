import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Path file = Paths.get("library.txt");
        Library lib = new Library();

        try {
            lib.load(file);
        } catch (IOException e) {
            System.out.println("Could not load file: " + e.getMessage());
        }
        if (lib.all().isEmpty()) {
            lib.addItem(new Book(1, "Java: The Complete Reference"));
            lib.addItem(new Book(2, "Clean Code"));
            lib.addItem(new Magazine(3, "Tech Today"));
        }

        new AutoSaver(lib, file).start();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show items\n2. Borrow\n3. Return\n4. Late fee\n5. Two-thread demo\n6. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        for (Item i : lib.all()) System.out.println(i);
                        break;
                    case "2":
                        System.out.print("Item id: ");
                        int bid = Integer.parseInt(sc.nextLine());
                        System.out.print("Your name: ");
                        lib.borrow(bid, sc.nextLine());
                        System.out.println("Borrowed successfully.");
                        break;
                    case "3":
                        System.out.print("Item id: ");
                        lib.giveBack(Integer.parseInt(sc.nextLine()));
                        System.out.println("Returned successfully.");
                        break;
                    case "4":
                        System.out.print("Item id: ");
                        Item item = lib.find(Integer.parseInt(sc.nextLine()));
                        System.out.print("Days late: ");
                        int days = Integer.parseInt(sc.nextLine());
                        System.out.println(item.type() + " late fee: Rs " + item.lateFee(days));
                        break;
                    case "5":
                        twoThreadDemo(lib);
                        break;
                    case "6":
                        lib.save(file);
                        System.out.println("Saved. Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (ItemNotAvailableException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Two users try to borrow the same item at the same time
    static void twoThreadDemo(Library lib) throws InterruptedException {
        Runnable r1 = () -> tryBorrow(lib, "Alice");
        Runnable r2 = () -> tryBorrow(lib, "Bob");
        Thread t1 = new Thread(r1), t2 = new Thread(r2);
        t1.start(); t2.start();
        t1.join(); t2.join();
    }

    static void tryBorrow(Library lib, String name) {
        try {
            lib.borrow(2, name);
            System.out.println(name + " got the book.");
        } catch (ItemNotAvailableException e) {
            System.out.println(name + " failed: " + e.getMessage());
        }
    }
}
