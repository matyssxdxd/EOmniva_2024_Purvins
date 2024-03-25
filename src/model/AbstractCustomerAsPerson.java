package model;

public abstract class AbstractCustomerAsPerson extends AbstractCustomer {
    protected Person person;

    public AbstractCustomerAsPerson() {
        super();
        this.person = new Person();
    }

    public AbstractCustomerAsPerson(String name, String surname, String personCode, Address address, String phone) {
        super(address, phone);
        this.person = new Person(name, surname, personCode);
    }

    @Override
    public String toString() {
        return person + " " + super.toString();
    }
}
