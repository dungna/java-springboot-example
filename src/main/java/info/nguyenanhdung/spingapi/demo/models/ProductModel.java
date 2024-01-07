package info.nguyenanhdung.spingapi.demo.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

// POJO = Plain Object Java Object
@Entity
@Table(name = "tblProduct")
public class ProductModel {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 // increment by
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    //validate = constraint
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int year;
    private Double price;
    private String url;

    // default contructor
    public ProductModel() {}
    // calculated field = transient
    @Transient
    private int age; // age is calculated from "year"
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - year;
    }
    public ProductModel(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel productModel = (ProductModel) o;
        return year == productModel.year && age == productModel.age && Objects.equals(id, productModel.id) && Objects.equals(productName, productModel.productName) && Objects.equals(price, productModel.price) && Objects.equals(url, productModel.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, year, price, url, age);
    }
}
