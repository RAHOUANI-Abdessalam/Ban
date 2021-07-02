package com.example.ban.Model;

public class Product {
    private String productName,price,oldPrice;
    private int image ;

    public Product() {
    }
    public Product(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }

    public Product(String productName, String price, String oldPrice) {
        this.productName = productName;
        this.price = price;
        this.oldPrice = oldPrice;
    }

    public Product(String productName, String price, String oldPrice, int image) {
        this.productName = productName;
        this.price = price;
        this.oldPrice = oldPrice;
        this.image = image;
    }

    public int getImage() { return image; }

    public void setImage(int image) { this.image = image; }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
}
