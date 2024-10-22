package com.example.JavaJam.JavaJam.model;

public class OrderRequest {
    private double justJava; // Price for Cafe Au Lait
    private int justJavaQuantity; // Quantity for Just Java
    private double cafeAuLait; // Price for Cafe Au Lait
    private int cafeAuLaitQuantity; // Quantity for Cafe Au Lait
    private double icedCappuccino; // Price for Iced Cappuccino
    private int icedCappuccinoQuantity; // Quantity for Iced Cappuccino

    // Getters and setters
    public double getJustJava() {
        return justJava;
    }

    public void setJustJava(double justJava) {
        this.justJava = justJava;
    }

    public int getJustJavaQuantity() {
        return justJavaQuantity;
    }

    public void setJustJavaQuantity(int justJavaQuantity) {
        this.justJavaQuantity = justJavaQuantity;
    }

    public double getCafeAuLait() {
        return cafeAuLait;
    }

    public void setCafeAuLait(double cafeAuLait) {
        this.cafeAuLait = cafeAuLait;
    }

    public int getCafeAuLaitQuantity() {
        return cafeAuLaitQuantity;
    }

    public void setCafeAuLaitQuantity(int cafeAuLaitQuantity) {
        this.cafeAuLaitQuantity = cafeAuLaitQuantity;
    }

    public double getIcedCappuccino() {
        return icedCappuccino;
    }

    public void setIcedCappuccino(double icedCappuccino) {
        this.icedCappuccino = icedCappuccino;
    }

    public int getIcedCappuccinoQuantity() {
        return icedCappuccinoQuantity;
    }

    public void setIcedCappuccinoQuantity(int icedCappuccinoQuantity) {
        this.icedCappuccinoQuantity = icedCappuccinoQuantity;
    }

    public double calcJustJavaPrice() {
        return this.justJavaQuantity * this.justJava;
    }

    public double calcCafeAuLaitPrice() {
        return this.cafeAuLaitQuantity * this.cafeAuLait;
    }

    public double calcIcedCappuccinoPrice() {
        return this.icedCappuccino * this.icedCappuccinoQuantity;
    }

    public double calcTotal() {
        return this.calcJustJavaPrice() + this.calcCafeAuLaitPrice() + this.calcIcedCappuccinoPrice();
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "justJava=" + justJava +
                ", justJavaQuantity=" + justJavaQuantity +
                ", cafeAuLait=" + cafeAuLait +
                ", cafeAuLaitQuantity=" + cafeAuLaitQuantity +
                ", icedCappuccino=" + icedCappuccino +
                ", icedCappuccinoQuantity=" + icedCappuccinoQuantity +
                '}';
    }
}
