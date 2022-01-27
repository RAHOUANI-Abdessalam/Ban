package com.example.ban.Model;

public class Product {
    private int id;
    private String productName,price,oldPrice;
    private int favorite_stat;
    private String content;
    private String imageSTR;
    private int image ;

    public String getImageSTR() {
        return imageSTR;
    }

    public void setImageSTR(String imageSTR) {
        this.imageSTR = imageSTR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorite_stat() {
        return favorite_stat;
    }

    public void setFavorite_stat(int favorite_stat) {
        this.favorite_stat = favorite_stat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product(int id, String productName, String price, String oldPrice, int favorite_stat, String content, String image) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.oldPrice = oldPrice;
        this.favorite_stat = favorite_stat;
        this.content = content;
        this.imageSTR = image;
    }

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
