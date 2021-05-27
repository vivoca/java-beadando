package hu.egyudv.beadando.repository.db.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_hiking")
public class UserHiking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "hiking_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hiking hiking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hiking getHiking() {
        return hiking;
    }

    public void setHiking(Hiking hiking) {
        this.hiking = hiking;
    }

    @Override
    public String toString() {
        return "UserHiking{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", hiking='" + hiking + '\'' +
                '}';
    }

}
