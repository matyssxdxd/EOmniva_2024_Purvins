package service;

import model.*;

import java.util.Arrays;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainService {
    private static ArrayList<Driver> allDrivers = new ArrayList<Driver>();
    private static ArrayList<AbstractCustomer> allCustomers = new ArrayList<AbstractCustomer>();

    public static void main(String[] args) {
        try {
            createNewDriver("Daniels", "Kalmars", "210298-21121" , "AT789221", 8.3f);
            createNewDriver("Kurts", "Kalmars", "300506-20221" , "AT832221", 1.2f);
            createNewDriver("Rudolfs", "Sniedzins", "110202-20202" , "AT123456", 36.5f);
            createNewDriver("Matiss", "Kalmars", "091099-23221" , "AT789011", 12.5f);

            System.out.println("createNewDriver()");
            for (Driver driver : allDrivers) {
                System.out.println(driver);
            }

            System.out.println("\nretrieveDriverByPersonCode()");
            System.out.println(retrieveDriverByPersonCode("210298-21121") + "\n");

            System.out.println("updateDriverLicenseNoByPersonCode() & updateDriverExperienceByPersonCode()");
            System.out.println(allDrivers.get(0) + "\n");

            updateDriverLicenseNoByPersonCode("210298-21121", "AT122312");
            updateDriverExperienceByPersonCode("210298-21121", 1.2f);

            System.out.println(allDrivers.get(0) + "\n");

            System.out.println("removeDriverByPersonCode()");
            System.out.println(allDrivers.get(0) + "\n");

            removeDriverByPersonCode("210298-21121");

            for (Driver driver : allDrivers) {
                System.out.println(driver);
            }

            System.out.println("\ncreateNewCustomerAsCompany() & createNewCustomerAsPerson()");

            createNewCustomerAsCompany(new Address(City.Ventspils, "Inzenieru iela", 101), "Kalmars un ko", "+37120627905", "LV20394839214");
            createNewCustomerAsCompany(new Address(City.Riga, "Lielais prospekts", 11), "Sniedzins un ko", "+37128727333", "LV48594834834");

            createNewCustomerAsPerson("Janis", "Briedis", "050302-20831", new Address(City.Daugavpils, "Parventas iela", 22), "+37128374854");
            createNewCustomerAsPerson("Andris", "Liepa", "040202-20831", new Address(City.Ventspils, "Liela iela", 11), "+37126374531");

            for (AbstractCustomer customer : allCustomers) {
                System.out.println(customer);
            }

            System.out.println("\nretrieveAllCustomersAsPersons()");
            for (CustomerAsPerson customer : retrieveAllCustomersAsPerson()) {
                System.out.println(customer);
            }

            System.out.println("\nretrieveAllCustomersAsCompany()");
            for (CustomerAsCompany customer : retrieveAllCustomersAsCompany()) {
                System.out.println(customer);
            }

            System.out.println("\ncreateNewParcelForCustomer()");

            createNewParcelForCustomer(LocalDateTime.now().plusHours(3), ParcelSize.M, true, allDrivers.get(0), allCustomers.get(2).getCustomerCode());
            createNewParcelForCustomer(LocalDateTime.now().plusDays(3), ParcelSize.M, true, allDrivers.get(1), allCustomers.get(2).getCustomerCode());
            createNewParcelForCustomer(LocalDateTime.now().plusDays(3), ParcelSize.L, true, allDrivers.get(0), allCustomers.get(2).getCustomerCode());
            createNewParcelForCustomer(LocalDateTime.now().plusHours(1), ParcelSize.XL, true, allDrivers.get(0), allCustomers.get(0).getCustomerCode());
            createNewParcelForCustomer(LocalDateTime.now().plusHours(1), ParcelSize.L, true, allDrivers.get(0), allCustomers.get(3).getCustomerCode());


            System.out.println(allCustomers.get(2).getParcels() + "\n");
            System.out.println(allCustomers.get(0).getParcels() + "\n");
            System.out.println(allCustomers.get(3).getParcels() + "\n");

            System.out.println("retrieveAllParcelsByCustomerCode()");
            for (Parcel parcel : retrieveAllParcelsByCustomerCode(allCustomers.get(2).getCustomerCode())) {
                System.out.println(parcel);
            }

            System.out.println("\nretrieveAllParcelsByDriverPersonCode()");
            for (Parcel parcel : retrieveAllParcelsByDriverPersonCode("300506-20221")) {
                System.out.println(parcel);
            }

            System.out.println("\nretrieveAllParcelsByCity()");
            for (Parcel parcel : retrieveAllParcelsByCity(City.Ventspils)) {
                System.out.println(parcel);
            }

            System.out.println("\nretrieveAllParcelsBySize()");
            for (Parcel parcel : retrieveAllParcelsBySize(ParcelSize.M)) {
                System.out.println(parcel);
            }

            System.out.println("\ncalculatePriceOfAllCustomersParcelsByCustomerCode()");
            System.out.println(calculatePriceOfAllCustomerParcelsByCustomerCode(allCustomers.get(2).getCustomerCode()) + "\n");

            System.out.println("retrieveStatisticsOfCustomerParcelSize()");
            System.out.println(Arrays.toString(retrieveStatisticsOfCustomerParcelSize(allCustomers.get(2).getCustomerCode())) + "\n");

            System.out.println("sortDriversByExperience()");
            for (Driver driver : allDrivers) {
                System.out.println(driver);
            }

            sortDriversByExperience();

            System.out.println();

            for (Driver driver : allDrivers) {
                System.out.println(driver);
            }

            System.out.println("\ncalculateHowManyParcelsTodayDeliveredToSpecificCity()");
            System.out.println(calculateHowManyParcelsTodayDeliveredToSpecificCity(City.Ventspils) + "\n");

            System.out.println("generateCustomersAsPersonAndParcel()");
            generateCustomerAsPersonAndParcel();
            generateCustomerAsPersonAndParcel();

            for (AbstractCustomer customer : allCustomers) {
                System.out.println(customer);
            }

            System.out.println("\ngenerateCustomerAsCompanyAndParcel()");
            generateCustomerAsCompanyAndParcel();
            generateCustomerAsCompanyAndParcel();

            for (AbstractCustomer customer : allCustomers) {
                System.out.println(customer);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createNewDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception {
        if (name == null || surname == null || personCode == null || licenseNo == null || experienceInYears < 0) throw new Exception("There is a problem with input parameters.");

        for (Driver driver : allDrivers) {
            if (driver.getPersonCode().equals(personCode)) {
                throw new Exception("Driver with that personCode already exists.");
            }
        }

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

    public static void createNewCustomerAsPerson(String name, String surname, String personCode, Address address, String phone) throws Exception {
        if (name == null || surname == null || personCode == null || address == null || phone == null) throw new Exception("There is a problem with input parameters.");

        ArrayList<CustomerAsPerson> customers = new ArrayList<>();

        try {
            customers = retrieveAllCustomersAsPerson();
        } catch (Exception ignored) {}

        for (CustomerAsPerson customer : customers) {
            if (customer.getPersonCode().equals(personCode)) throw new Exception("Person with that personCode already exists.");
        }

        allCustomers.add(new CustomerAsPerson(name, surname, personCode, address, phone));
    }

    public static void createNewCustomerAsCompany(Address address, String phone, String title, String companyRegNo) throws Exception {
        if (address == null || phone == null || title == null || companyRegNo == null) throw new Exception("There is a problem with input parameters.");

        ArrayList<CustomerAsCompany> customers = new ArrayList<>();

        try {
            customers = retrieveAllCustomersAsCompany();
        } catch (Exception ignored) {}

        for (CustomerAsCompany customer : customers) {
            if (customer.getCompanyRegNo().equals(companyRegNo)) throw new Exception("Company with that companyRegNo already exists.");
        }

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
        if (plannedDelivery == null || size == null || driver == null || customerCode == null) throw new Exception("There is a problem with input parameters.");

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

        if (result.isEmpty()) throw new Exception("There are no parcels being delivered to this city.");

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

    public static void generateCustomerAsPersonAndParcel() throws Exception {
        String[] names = {"Janis", "Rudolfs", "Daniels", "Andris", "Matiss", "Ritvars", "Raitis", "Roberts", "Haralds",
                "Ugis", "Juris", "Dimitrijs", "Sergejs"};
        String[] surnames = {"Berzins", "Sniedzins", "Kalmars", "Buks", "Briedis", "Vadonis", "Lieltevs", "Namatevs",
                "Biezbardis", "Kungs", "Zivs"};

        Random rand = new Random();

        City[] cities = {City.Ventspils, City.Daugavpils, City.Liepaja, City.Riga, City.Jelgava, City.Other};

        String[] streets = {"Inzenieru iela", "Juras iela", "Saules iela", "Katolu iela", "Valdemara iela", "Liela iela",
                "Lielais prospekts", "Klusa iela", "Puskina iela", "Matisa iela"};

        Address address = new Address(
                cities[rand.nextInt(cities.length)],
                streets[rand.nextInt(streets.length)],
                rand.nextInt(99) + 1);

        CustomerAsPerson customer = new CustomerAsPerson(
                names[rand.nextInt(names.length)],
                surnames[rand.nextInt(surnames.length)],
                String.format("%02d%02d%02d-%05d", rand.nextInt(30) + 1, rand.nextInt(12) + 1,
                        rand.nextInt(99) + 1, rand.nextInt(99999) + 1),
                address,
                String.format("+3712%07d", rand.nextInt(10000000)));

        ParcelSize[] parcelSizes = {ParcelSize.X, ParcelSize.M, ParcelSize.L, ParcelSize.XL, ParcelSize.S};

        Parcel parcel = new Parcel(
                LocalDateTime.now().plusDays(3),
                parcelSizes[rand.nextInt(parcelSizes.length)],
                rand.nextBoolean(),
                allDrivers.get(rand.nextInt(allDrivers.size())));

        customer.addNewParcel(parcel);
        allCustomers.add(customer);
    }

    public static void generateCustomerAsCompanyAndParcel() throws Exception {
        City[] cities = {City.Ventspils, City.Daugavpils, City.Liepaja, City.Riga, City.Jelgava, City.Other};

        String[] streets = {"Inzenieru iela", "Juras iela", "Saules iela", "Katolu iela", "Valdemara iela", "Liela iela",
                "Lielais prospekts", "Klusa iela", "Puskina iela", "Matisa iela"};

        String[] titleFirst = {"Janis", "Babum", "Uga", "Buga", "Ritvars", "Ods", "Rudolfs", "Daniels"};
        String[] titleSecond = {"Un Ko", "Burger", "Foods", "Eat", "Kebab", "Tacos", "Kalmari", "Sniedzini", "Bistro", "Restaurant"};

        Random rand = new Random();

        Address address = new Address(
                cities[rand.nextInt(cities.length)],
                streets[rand.nextInt(streets.length)],
                rand.nextInt(99) + 1);

        CustomerAsCompany company = new CustomerAsCompany(
                address,
                String.format("+3712%07d", rand.nextInt(10000000)),
                titleFirst[rand.nextInt(titleFirst.length)] + " " + titleSecond[rand.nextInt(titleSecond.length)],
                String.format("LV%09d%09d", rand.nextInt(999999999), rand.nextInt(99))
                );


        ParcelSize[] parcelSizes = {ParcelSize.X, ParcelSize.M, ParcelSize.L, ParcelSize.XL, ParcelSize.S};

        Parcel parcel = new Parcel(
                LocalDateTime.now().plusDays(3),
                parcelSizes[rand.nextInt(parcelSizes.length)],
                rand.nextBoolean(),
                allDrivers.get(rand.nextInt(allDrivers.size())));

        company.addNewParcel(parcel);
        allCustomers.add(company);
    }


}
