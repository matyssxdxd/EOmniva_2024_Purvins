package model;

public class Address {
    private City city;
    private String streetOrHouseTitle;
    private int houseNo;

    public Address() {
        setCity(City.Ventspils);
        setStreetOrHouseTitle("Inzenieru iela");
        setHouseNo(101);
    }

    public Address(City city, String streetOrHouseTitle, int houseNo) {
        setCity(city);
        setStreetOrHouseTitle(streetOrHouseTitle);
        setHouseNo(houseNo);
    }

    public City getCity() {
        return city;
    }

    public String getStreetOrHouseTitle() {
        return streetOrHouseTitle;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setCity(City city) {
        this.city = ( city != null ) ? city : City.Other;
    }

    public void setStreetOrHouseTitle(String streetOrHouseTitle) {
        this.streetOrHouseTitle = ( streetOrHouseTitle != null &&
                streetOrHouseTitle.matches("[A-Z]{1}[A-Za-z0-9 -]+") ) ? streetOrHouseTitle : "Inzenieru iela";
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = ( houseNo > 0 && houseNo < 500 ) ? houseNo : 0;
    }

    @Override
    public String toString() {
        return streetOrHouseTitle + " " + houseNo + " " + city;
    }
}
