
import java.util.*;

public class LibraryManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        seedSampleData();
        System.out.println("Welcome to Library Management System (Core Java)");
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addBookPrompt();
                    break;
                case "2":
                    removeBookPrompt();
                    break;
                case "3":
                    registerMemberPrompt();
                    break;
                case "4":
                    issueBookPrompt();
                    break;
                case "5":
                    returnBookPrompt();
                    break;
                case "6":
                    searchBooksPrompt();
                    break;
                case "7":
                    listAvailableBooks();
                    break;
                case "8":
                    listAllBooks();
                    break;
                case "9":
                    listMembers();
                    break;
                case "0":
                    System.out.println("Exiting. Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Register Member");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Search Books");
        System.out.println("7. List Available Books");
        System.out.println("8. List All Books");
        System.out.println("9. List Members");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private static void addBookPrompt() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author required.");
            return;
        }
        String isbn = library.addBook(title, author);
        System.out.println("Added. ISBN = " + isbn);
    }

    private static void removeBookPrompt() {
        System.out.print("ISBN to remove: ");
        String isbn = scanner.nextLine().trim();
        boolean ok = library.removeBook(isbn);
        System.out.println(ok ? "Removed." : "Cannot remove (not found or issued).");
    }

    // This method calls Library.registerMember
    private static void registerMemberPrompt() {
        System.out.print("Member name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name required.");
            return;
        }
        String id = library.registerMember(name, email);
        System.out.println("Member registered. ID = " + id);
    }

    private static void issueBookPrompt() {
        System.out.print("ISBN to issue: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Member ID: ");
        String mid = scanner.nextLine().trim();
        boolean ok = library.issueBook(isbn, mid);
        System.out.println(ok ? "Issued." : "Failed to issue (check ISBN/member/availability).");
    }

    private static void returnBookPrompt() {
        System.out.print("ISBN to return: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Member ID: ");
        String mid = scanner.nextLine().trim();
        boolean ok = library.returnBook(isbn, mid);
        System.out.println(ok ? "Returned." : "Failed to return (check ISBN/member).");
    }

    private static void searchBooksPrompt() {
        System.out.println("Search by: 1) Title 2) Author 3) ISBN");
        String c = scanner.nextLine().trim();
        if (c.equals("1")) {
            System.out.print("Title query: ");
            String q = scanner.nextLine().trim();
            List<Book> r = library.searchByTitle(q);
            r.forEach(System.out::println);
            if (r.isEmpty()) {
                System.out.println("No results.");
            }
        } else if (c.equals("2")) {
            System.out.print("Author query: ");
            String q = scanner.nextLine().trim();
            List<Book> r = library.searchByAuthor(q);
            r.forEach(System.out::println);
            if (r.isEmpty()) {
                System.out.println("No results.");
            }
        } else if (c.equals("3")) {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine().trim();
            Book b = library.findBookByIsbn(isbn);
            System.out.println(b == null ? "Not found." : b);
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void listAvailableBooks() {
        List<Book> list = library.listAvailableBooks();
        if (list.isEmpty()) {
            System.out.println("No available books.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void listAllBooks() {
        List<Book> list = library.listAllBooks();
        if (list.isEmpty()) {
            System.out.println("No books.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void listMembers() {
        List<Member> list = library.listMembers();
        if (list.isEmpty()) {
            System.out.println("No members.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void seedSampleData() {
        library.addBook("Effective Java", "Joshua Bloch");
        library.addBook("Clean Code", "Robert C. Martin");
        library.registerMember("Amar Kumar", "amar@example.com");
        library.registerMember("Akash kumar", "akashkumar@example.com");
    }
}
