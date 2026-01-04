package ConnectPro.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("COMPANY")
public class CompanyUser extends User {

    @Column(unique = true, length = 9)
    private String ein;

    @Column(length = 150)
    private String legalName;


    public CompanyUser() {
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
}
