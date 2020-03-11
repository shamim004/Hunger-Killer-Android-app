package com.example.ironman.hungerkiller;

public class DummyRestaurentsValueHolder {
    String name;
    String info;
    int price;
    String url="";
    int quantity=1;


    DummyRestaurentsValueHolder(){

    }

    public DummyRestaurentsValueHolder(String name, String info, int price, String url, int quantity) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.url = url;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
