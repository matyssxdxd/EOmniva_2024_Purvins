package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Parcel {
    private LocalDateTime orderCreated;
    private LocalDateTime plannedDelivery;
    private ParcelSize size;
    private float price;
    private boolean isFragile;
    private Driver driver;

    public Parcel() {
        setOrderCreated();
        setPlannedDelivery(LocalDateTime.now().plusDays(1));
        setSize(ParcelSize.X);
        setFragile(true);
        setPrice();
        setDriver(new Driver());
    }

    public Parcel(LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile, Driver driver) {
        setOrderCreated();
        setPlannedDelivery(plannedDelivery);
        setSize(size);
        setFragile(isFragile);
        setPrice();
        setDriver(driver);
    }

    public LocalDateTime getOrderCreated() {
        return orderCreated;
    }

    public LocalDateTime getPlannedDelivery() {
        return plannedDelivery;
    }

    public ParcelSize getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setOrderCreated() {
        this.orderCreated = LocalDateTime.now();
    }

    public void setPlannedDelivery(LocalDateTime plannedDelivery) {
        this.plannedDelivery = ( plannedDelivery != null && plannedDelivery.isAfter(orderCreated) ) ? plannedDelivery : orderCreated.plusDays(1);
    }

    public void setSize(ParcelSize size) {
        this.size = ( size != null ) ? size : ParcelSize.X;
    }

    public void setPrice() {
        int multiplier = switch (size) {
            case X -> 1;
            case S -> 2;
            case M -> 3;
            case L -> 4;
            case XL -> 5;
        };

        this.price = (float) (isFragile ? multiplier * 1.99 + 2.99 : multiplier * 1.99);
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public void setDriver(Driver driver) {
        this.driver = (driver != null) ? driver : new Driver();
    }

    @Override
    public String toString() {
        return "order created: " + orderCreated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ", planned delivery: "
                + plannedDelivery.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ", size: " + size + ", price: "
                + price + ", driver: " + driver;
    }
}
