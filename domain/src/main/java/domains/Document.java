package domains;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Document {

    @Id
    private long id;

    @Lob
    private String left;

    @Lob
    private String right;

    public Document() {

    }

    public Document(long id, String left, String right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "Document [id=" + getId() + ", left=" + getLeft() + ", right=" + getRight() + "]";
    }
}