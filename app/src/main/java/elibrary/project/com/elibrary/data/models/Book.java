package elibrary.project.com.elibrary.data.models;

public class Book {

    public String bid;
    public String name;
    public String category;
    public String author;
    public String rackno;
    public String price;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRackno() {
        return rackno;
    }

    public void setRackno(String rackno) {
        this.rackno = rackno;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
