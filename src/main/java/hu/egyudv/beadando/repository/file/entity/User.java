package hu.egyudv.beadando.repository.file.entity;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.file.HikingRepositoryFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    @CsvBindByName(column = "ID")
    private String id;

    @CsvBindByName(column = "FIRST_NAME")
    private String firstName;

    @CsvBindByName(column = "LAST_NAME")
    private String lastName;

    @CsvDate(value = "yyyy.MM.dd")
    @CsvBindByName(column = "BIRTH_DATE")
    private Date birthDate;

    @CsvBindByName(column = "MOBILE")
    private String mobile;

    @CsvBindAndSplitByName(column = "COMPLETED_HIKING", elementType = String.class)
    private List<String> completedHikingString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Hiking> getCompletedHikingList() {
        List<Hiking> hikingList = new ArrayList<>();
        HikingRepository hikingRepository = new HikingRepositoryFile();
        for (String hikingId : completedHikingString) {
            if (hikingId != null && hikingId.length() > 0) {
                Hiking item = hikingRepository.get(hikingId);
                hikingList.add(item);
            }
        }
        return hikingList;
    }

    public void setCompletedHikingList(List<Hiking> completedHikingList) {
        List<String> hikingListString = new ArrayList<>();
        for (Hiking item : completedHikingList) {
            hikingListString.add(item.getId());
        }
        this.completedHikingString = hikingListString;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", mobile='" + mobile + '\'' +
                ", completedHikingString=" + completedHikingString +
                '}';
    }
}
