import java.util.*;

class Member {

    private final String memberId;
    private String name;
    private String email;
    private final List<String> issuedBookIsbns = new ArrayList<>();

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addIssuedBook(String isbn) {
        issuedBookIsbns.add(isbn);
    }

    public boolean removeIssuedBook(String isbn) {
        return issuedBookIsbns.remove(isbn);
    }

    public List<String> getIssuedBooks() {
        return Collections.unmodifiableList(issuedBookIsbns);
    }

    @Override
    public String toString() {
        return String.format("MemberID:%s | Name:%s | Email:%s | IssuedBooks:%d",
                memberId, name, email, issuedBookIsbns.size());
    }
}
