package hu.egyudv.beadando.repository.db.entity;


import javax.persistence.*;

@Entity
@Table
public class UserHiking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "hiking_id")
    private Long hikingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHikingId() {
        return hikingId;
    }

    public void setHikingId(Long hikingId) {
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
