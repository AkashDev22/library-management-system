
class Book {

    private final String isbn;
    private String title;
    private String author;
    private boolean issued;
    private String issuedToMemberId;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.issued = false;
        this.issuedToMemberId = null;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public String getIssuedToMemberId() {
        return issuedToMemberId;
    }

    public boolean issueTo(String memberId) {
        if (issued) {
            return false;
        }
        issued = true;
        issuedToMemberId = memberId;
        return true;
    }

    public boolean returnByMember() {
        if (!issued) {
            return false;
        }
        issued = false;
        issuedToMemberId = null;
        return true;
    }

    @Override
    public String toString() {
        return String.format("ISBN:%s | Title:%s | Author:%s | Issued:%s",
                isbn, title, author, issued ? ("Yes -> " + issuedToMemberId) : "No");
    }
}
