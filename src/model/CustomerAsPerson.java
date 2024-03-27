package model;

public class CustomerAsPerson extends AbstractCustomerAsPerson {

    public CustomerAsPerson() {
        super();
        setCustomerCode();
    }

    public CustomerAsPerson(String name, String surname, String personCode, Address address, String phone) {
        super(name, surname, personCode, address, phone);
        setCustomerCode();
    }

    public String getPersonCode() {
        return super.person.getPersonCode();
    }


    @Override
    public void setCustomerCode() {
        super.customerCode = super.getcID() + "_person_" + super.person.getPersonCode();
    }

    @Override
    public String toString() {
        return super.toString() + " " + customerCode;
    }
}
