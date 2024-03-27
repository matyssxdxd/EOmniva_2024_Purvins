package model;

public class Person {
    private String name;
    private String surname;
    private String personCode;

    public Person() {
        setName("Daniel");
        setSurname("Tentacles");
        setPersonCode("150398-28304");
    }

    public Person(String name, String surname, String personCode) {
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setName(String name) {
        this.name = ( name != null && name.matches("[A-Z]{1}[a-z]+") ) ? name : "defaultName";
    }

    public void setSurname(String surname) {
        this.surname = ( surname != null && surname.matches("[A-Z]{1}[a-z]+") ) ? surname : "defaultSurname";
    }

    public void setPersonCode(String personCode) {
        this.personCode = ( personCode != null && personCode.matches("[0-9]{6}-[0-9]{5}")) ? personCode : "000000-00000";
    }

    @Override
    public String toString() {
        return "name: " + name + ", surname: " + surname + ", personCode: " + personCode;
    }
}
