package model;

public class CustomerAsCompany extends AbstractCustomer {
    private String title;
    private String companyRegNo;

    public CustomerAsCompany() {
        super();
        setTitle("Rudolfs un ko");
        setCompanyRegNo("LV20394839214");
        setCustomerCode();
    }

    public CustomerAsCompany(Address address, String phone, String title, String companyRegNo) {
        super(address, phone);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setTitle(String title) {
        this.title = ( title != null && title.matches("[A-Za-z0-9%^+@=.,! ]{3,50}")) ? title : "defaultTitle";
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = (companyRegNo != null &&
                companyRegNo.matches("[A-Z]{2}[0-9]{11}")) ? companyRegNo : "LV20394839214";
    }

    @Override
    public void setCustomerCode() {
        super.customerCode = getcID() + "_company_" + companyRegNo;
    }
}
