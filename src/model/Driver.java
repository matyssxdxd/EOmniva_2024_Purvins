package model;

public class Driver extends Person implements Comparable<Driver> {
    private long dID;
    private String licenseNo;
    private float experienceInYears;
    private static long counter = 0;

    public Driver() {
        super();
        setLicenseNo("AT000000");
        setExperienceInYears(0);
        setdID();
    }

    public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
        super(name, surname, personCode);
        setLicenseNo(licenseNo);
        setExperienceInYears(experienceInYears);
        setdID();
    }

    public long getdID() {
        return dID;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public float getExperienceInYears() {
        return experienceInYears;
    }

    public void setdID() {
        this.dID = counter++;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = ( licenseNo != null && licenseNo.matches("[A-Z]{2}[0-9]{6}") ) ? licenseNo : "AT000000";
    }

    public void setExperienceInYears(float experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    @Override
    public String toString() {
        return "dID: " + dID + " " + super.toString() + " licenseNo: " + licenseNo + " YOE: " + experienceInYears;
    }

    @Override
    public int compareTo(Driver o) {
        if (experienceInYears > o.experienceInYears) return 1;

        else if (experienceInYears < o.experienceInYears) return -1;

        return 0;
    }
}