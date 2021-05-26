package hu.egyudv.beadando.repository.file.entity;

import com.opencsv.bean.CsvBindByName;

public class UserHiking {

    @CsvBindByName(column = "ID")
    private String id;

    @CsvBindByName(column = "USER_ID")
    private String userId;

    @CsvBindByName(column = "HIKING_ID")
    private String hikingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHikingId() {
        return hikingId;
    }

    public void setHikingId(String hikingId) {
        this.hikingId = hikingId;
    }

    @Override
    public String toString() {
        return "UserHiking{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", hikingId='" + hikingId + '\'' +
                '}';
    }
}
