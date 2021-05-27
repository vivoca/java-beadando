package hu.egyudv.beadando.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

public class UserData implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFormattedBirthDate() {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").format(birthDate);
        } catch (Exception ex) {
            return "";
        }
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setFormattedBirthDate(String birthDate) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        sdf.setLenient(false);
        this.birthDate = sdf.parse(birthDate);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
