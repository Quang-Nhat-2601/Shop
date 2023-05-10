package com.example.foodapp.Domain;

public class ProductDomain {
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int star;
    private String material;
    private int numberInCart;

    public ProductDomain(String title, String pic, String description, Double fee, int star, String material) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.star = star;
        this.material = material;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
