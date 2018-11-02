package domains;

import javax.persistence.*;

@Entity
public class Document {

    @Id
    private Integer id;

    private String leftDocument;

    private String rightDocument;

    public Document() {

    }

    public Document(Integer id, String left, String right) {
        this.id = id;
        this.leftDocument = left;
        this.rightDocument = right;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeft() {
        return leftDocument;
    }

    public void setLeft(String left) {
        this.leftDocument = left;
    }

    public String getRight() {
        return rightDocument;
    }

    public void setRight(String right) {
        this.rightDocument = right;
    }


    @Override
    public String toString() {
        return "Document [id=" + getId() + ", left=" + getLeft() + ", right=" + getRight() + "]";
    }
}