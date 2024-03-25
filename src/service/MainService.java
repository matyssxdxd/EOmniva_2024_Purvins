package service;

import model.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainService {
    private static ArrayList<Driver> allDrivers = new ArrayList<Driver>();
    private static ArrayList<AbstractCustomer> allCustomers = new ArrayList<AbstractCustomer>();

    public static void main(String[] args) {
        createNewDriver("Daniels", "Kalmars", "130202-20821" , "AT789221", 8.3f);
        createNewDriver("Kalmars", "Kalmars", "130202-20821" , "AT832221", 52.3f);
        createNewDriver("Rudolfs", "Sniedzins", "020202-20202" , "AT123456", 36.5f);
        createNewDriver("Matiss", "Kalmars", "130202-20821" , "AT789011", 12.5f);
        createNewCustomerAsCompany(new Address(City.Ventspils, "Inzenieru iela", 101), "Kalmars un ko", "+37120627905", "LV20394839214");
        createNewCustomerAsCompany(new Address(City.Ventspils, "Lielais prospekts", 11), "Sniedzins un ko", "+37128727333", "LV48594834834");
        createNewCustomerAsPerson("Janis", "Briedis", "050302-20831", new Address(City.Ventspils, "Parventas iela", 22), "+37128374854");
        createNewCustomerAsPerson("Janis", "Kodejs", "040202-20831", new Address(City.Ventspils, "Liela iela", 11), "+37126374531");


        try {
            System.out.println(retrieveDriverByPersonCode("130202-20821"));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            updateDriverLicenseNoByPersonCode("130202-20821", "AT583938");
            System.out.println(allDrivers.get(1));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            updateDriverExperienceByPersonCode("130202-20821", 50.4f);
            System.out.println(allDrivers.get(1));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ArrayList<CustomerAsCompany> customersAsCompany = retrieveAllCustomersAsCompany();
            System.out.println(customersAsCompany);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ArrayList<CustomerAsPerson> customersAsPerson = retrieveAllCustomersAsPerson();
            System.out.println(customersAsPerson);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            createNewParcelForCustomer(LocalDateTime.now().plusDays(3), ParcelSize.XL, true, allDrivers.get(0), allCustomers.get(2).getCustomerCode());
            System.out.println(allCustomers.get(2));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println(allDrivers);
            sortDriversByExperience();
            System.out.println(allDrivers);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createNewDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
        // TODO: Check inputs.
        allDrivers.add(new Driver(name, surname, personCode, licenseNo, experienceInYears));
    }

    public static Driver retrieveDriverByPersonCode(String personCode) throws Exception {

        if (personCode == null) throw new Exception("Person code provided is invalid.");

        for (Driver driver : allDrivers) {
            if (driver.getPersonCode().equals(personCode)) return driver;
        }

        throw new Exception("No driver with that personCode was found.");
    }

    public static void updateDriverLicenseNoByPersonCode(String personCode, String licenseNo) throws Exception {
        if (personCode == null || licenseNo == null) throw new Exception("There is no personCode or licenseNo provided.");

        for (Driver driver : allDrivers) {
            if (driver.getPersonCode().equals(personCode)) {
                driver.setLicenseNo(licenseNo);
                return;
            }
        }

        throw new Exception("No driver with that personCode was found.");
    }

    public static void updateDriverExperienceByPersonCode(String personCode, float experienceInYears) throws Exception {
        if (personCode == null) throw new Exception("There is no personCode provided.");

        for (Driver driver : allDrivers) {
            if (driver.getPersonCode().equals(personCode)) {
                driver.setExperienceInYears(experienceInYears);
                return;
            }
        }

        throw new Exception("No driver with that personCode was found.");
    }

    public static void removeDriverByPersonCode(String personCode) throws Exception {
        if (personCode == null) throw new Exception("There is no personCode provided.");

        for (Driver driver : allDrivers) {
            if (driver.getPersonCode().equals(personCode)) {
                allDrivers.remove(driver);
                return;
            }
        }

        throw new Exception("No driver with that personCode was found.");
    }

    public static void createNewCustomerAsPerson(String name, String surname, String personCode, Address address, String phone) {
        // TODO: Check inputs.
        allCustomers.add(new CustomerAsPerson(name, surname, personCode, address, phone));
    }

    public static void createNewCustomerAsCompany(Address address, String phone, String title, String companyRegNo) {
        // TODO: Check inputs.
        allCustomers.add(new CustomerAsCompany(address, phone, title, companyRegNo));
    }

    public static ArrayList<CustomerAsPerson> retrieveAllCustomersAsPerson() throws Exception {
        if (allCustomers.isEmpty()) throw new Exception("There are no customers in allCustomers.");

        ArrayList<CustomerAsPerson> result = new ArrayList<CustomerAsPerson>();

        for (AbstractCustomer customer : allCustomers) {
            if (customer instanceof CustomerAsPerson) result.add((CustomerAsPerson) customer);
        }

        if (result.isEmpty()) throw new Exception("There are no CustomerAsCompany in allCustomers.");

        return result;
    }

    public static ArrayList<CustomerAsCompany> retrieveAllCustomersAsCompany() throws Exception {
        if (allCustomers.isEmpty()) throw new Exception("There are no customers in allCustomers.");

        ArrayList<CustomerAsCompany> result = new ArrayList<CustomerAsCompany>();

        for (AbstractCustomer customer : allCustomers) {
            if (customer instanceof CustomerAsCompany) result.add((CustomerAsCompany) customer);
        }

        if (result.isEmpty()) throw new Exception("There are no CustomerAsCompany in allCustomers.");

        return result;
    }

    public static AbstractCustomer retrieveCustomerByCustomerCode(String customerCode) throws Exception {
        if (customerCode == null) throw new Exception("No customerCode provided.");

        for (AbstractCustomer customer : allCustomers) {
            if (customer.getCustomerCode().equals(customerCode)) return customer;
        }

        throw new Exception("There was no customer with that customerCode.");
    }

    public static void createNewParcelForCustomer(LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile, Driver driver, String customerCode) throws Exception {
        // TODO: Check inputs.
        retrieveCustomerByCustomerCode(customerCode).addNewParcel(new Parcel(plannedDelivery, size, isFragile, driver));
    }

    public static ArrayList<Parcel> retrieveAllParcels() throws Exception {
        if (allCustomers.isEmpty()) throw new Exception("There are no customers.");

        ArrayList<Parcel> result = new ArrayList<Parcel>();

        for (AbstractCustomer customer : allCustomers) {
            result.addAll(customer.getParcels());
        }

        if (result.isEmpty()) throw new Exception("There are no parcels.");

        return result;
    }

    public static ArrayList<Parcel> retrieveAllParcelsByCustomerCode(String customerCode) throws Exception {
        if (customerCode == null) throw new Exception("Invalid customerCode.");

        return retrieveCustomerByCustomerCode(customerCode).getParcels();
    }

    public static ArrayList<Parcel> retrieveAllParcelsByDriverPersonCode(String personCode) throws Exception {
        if (personCode == null) throw new Exception("Invalid personCode.");

        ArrayList<Parcel> allParcels = retrieveAllParcels();
        ArrayList<Parcel> result = new ArrayList<Parcel>();

        for (Parcel parcel : allParcels) {
            if (parcel.getDriver().getPersonCode().equals(personCode)) result.add(parcel);
        }

        if (result.isEmpty()) throw new Exception("There are no parcels being delivered by this driver.");

        return result;
    }

    public static ArrayList<Parcel> retrieveAllParcelsByCity(City city) throws Exception {
        if (city == null) throw new Exception("Invalid city.");

        ArrayList<Parcel> result = new ArrayList<Parcel>();

        for (AbstractCustomer customer : allCustomers) {
            if (customer.getAddress().getCity().equals(city)) result.addAll(customer.getParcels());
        }

        if (result.isEmpty()) throw new Exception("There are no parcels being delivered by this driver.");

        return result;
    }

    public static ArrayList<Parcel> retrieveAllParcelsBySize(ParcelSize size) throws Exception {
        if (size == null) throw new Exception("Invalid size.");

        ArrayList<Parcel> allParcels = retrieveAllParcels();
        ArrayList<Parcel> result = new ArrayList<Parcel>();

        for (Parcel parcel : allParcels) {
            if (parcel.getSize().equals(size)) result.add(parcel);
        }

        if (result.isEmpty()) throw new Exception("There are no parcels being delivered by this driver.");

        return result;
    }

    public static float calculatePriceOfAllCustomerParcelsByCustomerCode(String customerCode) throws Exception {
        if (customerCode == null) throw new Exception("Invalid customerCode.");

        ArrayList<Parcel> customerParcels = retrieveCustomerByCustomerCode(customerCode).getParcels();
        float sum = 0;

        if (customerParcels.isEmpty()) throw new Exception("This customer has no parcels.");

        for (Parcel parcel : customerParcels) {
            sum += parcel.getPrice();
        }

        return sum;
    }

    public static int[] retrieveStatisticsOfCustomerParcelSize(String customerCode) throws Exception {
        if (customerCode == null) throw new Exception("Invalid customerCode.");

        ArrayList<Parcel> customerParcels = retrieveCustomerByCustomerCode(customerCode).getParcels();
        if (customerParcels.isEmpty()) throw new Exception("This customer has no parcels.");

        int[] result = new int[5];

        for (Parcel parcel : customerParcels) {
            switch (parcel.getSize()) {
                case X:
                    result[0]++;
                    break;
                case S:
                    result[1]++;
                    break;
                case M:
                    result[2]++;
                    break;
                case L:
                    result[3]++;
                    break;
                case XL:
                    result[4]++;
                    break;
            }
        }

        return result;
    }

    public static void sortDriversByExperience() throws Exception {
        if (allDrivers.isEmpty()) throw new Exception("All drivers are empty.");

        for (int i = 0; i < allDrivers.size() ; i++) {
            for (int j = 0; j < allDrivers.size(); j++) {
                if (((Comparable) allDrivers.get(i)).compareTo(allDrivers.get(j)) == 1) {
                    Driver temp = allDrivers.get(i);
                    allDrivers.set(i, allDrivers.get(j));
                    allDrivers.set(j, temp);
                }
            }
        }
    }

    public static int calculateHowManyParcelsTodayDeliveredToSpecificCity(City city) throws Exception {
        if (city == null) throw new Exception("Invalid city.");

        int sum = 0;

        for (AbstractCustomer customer : allCustomers) {
            if (customer.getAddress().getCity().equals(city)) {
                ArrayList<Parcel> customerParcels = customer.getParcels();
                if (customerParcels.isEmpty()) continue;
                for (Parcel parcel : customerParcels) {
                    if (parcel.getPlannedDelivery().getDayOfYear() == LocalDateTime.now().getDayOfYear()) sum++;
                }
            }
        }

        return sum;
    }
}
