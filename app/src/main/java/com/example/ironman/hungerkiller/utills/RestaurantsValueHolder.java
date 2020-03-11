package com.example.ironman.hungerkiller.utills;


public class RestaurantsValueHolder {
    String key="";
    String name="";
    String info="";
    String url="";
    int price=0;
    int quantity=1;

    public RestaurantsValueHolder(){

    }

    public RestaurantsValueHolder(String key, String name, String info, String url, int price, int quantity) {
        this.key = key;
        this.name = name;
        this.info = info;
        this.url = url;
        this.price = price;
        this.quantity = quantity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
