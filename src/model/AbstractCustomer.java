package model;

import java.util.ArrayList;

public abstract class AbstractCustomer {
    private long cID;
    private Address address;
    private String phoneNo;
    private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
    protected String customerCode;
    private static long counter = 0;

    public AbstractCustomer() {
        setcID();
        setAddress(new Address());
        setPhoneNo("+37120627905");
    }

    public AbstractCustomer(Address address, String phone) {
        setcID();
        setAddress(address);
        setPhoneNo(phone);
    }

    public long getcID() {
        return cID;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setcID() {
        this.cID = counter++;
    }

    public void setAddress(Address address) {
        this.address = ( address != null ) ? address : new Address();
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = ( phoneNo != null && phoneNo.matches("[+]{1}[371]{3}[0-9]{8}"))
                ? phoneNo : "+37120627905";
    }

    public void setPackages(ArrayList<Parcel> parcels) {
        this.parcels = ( parcels != null ) ? parcels : new ArrayList<Parcel>();
    }

    public abstract void setCustomerCode();

    public void addNewParcel(Parcel parcel) throws Exception {
        if (parcel == null) throw new Exception("No parcel");

        for (Parcel tempParcel : parcels) {
            if (parcel.equals(tempParcel)) throw new Exception("Parcel already exists");
        }

        parcels.add(parcel);
    }

    @Override
    public String toString() {
        return "cID: " + cID + ", customerCode: " + customerCode + ", phoneNo: " + phoneNo + ", address: " + address + ", parcels: " + parcels;
    }
}
