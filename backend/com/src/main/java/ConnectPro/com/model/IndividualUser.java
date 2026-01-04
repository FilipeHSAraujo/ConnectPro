package ConnectPro.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("INDIVIDUAL")
public class IndividualUser extends User {

    @Column(unique = true, length = 9)
    private String ssn;

    public IndividualUser() {
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
