
import java.util.*;

class Library {

    private final Map<String, Book> booksByIsbn = new LinkedHashMap<>();
    private final Map<String, Member> membersById = new LinkedHashMap<>();
    private int bookCounter = 1000;
    private int memberCounter = 5000;

    // BOOK methods
    public String addBook(String title, String author) {
        String isbn = generateIsbn();
        Book book = new Book(isbn, title, author);
        booksByIsbn.put(isbn, book);
        return isbn;
    }

    public boolean removeBook(String isbn) {
        Book b = booksByIsbn.get(isbn);
        if (b == null) {
            return false;
        }
        if (b.isIssued()) {
            return false; // cannot remove issued book

        }
        booksByIsbn.remove(isbn);
        return true;
    }

    public Book findBookByIsbn(String isbn) {
        return booksByIsbn.get(isbn);
    }

    public List<Book> searchByTitle(String q) {
        List<Book> out = new ArrayList<>();
        String ql = q.toLowerCase();
        for (Book b : booksByIsbn.values()) {
            if (b.getTitle().toLowerCase().contains(ql)) {
                out.add(b);
            }
        }
        return out;
    }

    public List<Book> searchByAuthor(String q) {
        List<Book> out = new ArrayList<>();
        String ql = q.toLowerCase();
        for (Book b : booksByIsbn.values()) {
            if (b.getAuthor().toLowerCase().contains(ql)) {
                out.add(b);
            }
        }
        return out;
    }

    public List<Book> listAllBooks() {
        return new ArrayList<>(booksByIsbn.values());
    }

    public List<Book> listAvailableBooks() {
        List<Book> out = new ArrayList<>();
        for (Book b : booksByIsbn.values()) {
            if (!b.isIssued()) {
                out.add(b);
            }
        }
        return out;
    }

    public List<Book> listIssuedBooks() {
        List<Book> out = new ArrayList<>();
        for (Book b : booksByIsbn.values()) {
            if (b.isIssued()) {
                out.add(b);
            }
        }
        return out;
    }

    // member methods
    public String registerMember(String name, String email) {
        String id = generateMemberId();
        Member m = new Member(id, name, email);
        membersById.put(id, m);
        return id;
    }

    public Member findMemberById(String id) {
        return membersById.get(id);
    }

    public List<Member> listMembers() {
        return new ArrayList<>(membersById.values());
    }

    // Issue / return
    public boolean issueBook(String isbn, String memberId) {
        Book book = booksByIsbn.get(isbn);
        Member member = membersById.get(memberId);
        if (book == null || member == null) {
            return false;
        }
        if (book.isIssued()) {
            return false;
        }
        boolean ok = book.issueTo(memberId);
        if (ok) {
            member.addIssuedBook(isbn);
        }
        return ok;
    }

    public boolean returnBook(String isbn, String memberId) {
        Book book = booksByIsbn.get(isbn);
        Member member = membersById.get(memberId);
        if (book == null || member == null) {
            return false;
        }
        if (!book.isIssued()) {
            return false;
        }
        // ensure the member had that book
        if (!member.removeIssuedBook(isbn)) {
            return false;
        }
        return book.returnByMember();
    }

    // helpers
    private String generateIsbn() {
        return "ISBN" + (bookCounter++);
    }

    private String generateMemberId() {
        return "M" + (memberCounter++);
    }
}
