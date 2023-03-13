package kz.ermek.DutyGraphicProject.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Orderly")
public class Orderly {
    @Id
    @Column(name = "orderly_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderlyId;

    @Column(name = "rang")
    @Size(min = 2, max = 100, message = "Rang should be between 2 and 100 characters")
    @NotEmpty(message = "Rang should not be empty")
    private String rang;

    @NotEmpty(message = "Orderly name should not be empty")
    @Size(min = 2, max = 100, message = "Orderly  name should be between 2 and 100 characters")
    @Column(name = "orderly_name")
    private String orderlyName;

    @ManyToOne
    @JoinColumn(name = "d_id", referencedColumnName = "duty_id")
    private Duty duty;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    public Orderly() {

    }

    public Orderly(String orderlyName) {
        this.orderlyName = orderlyName;
    }

    public int getOrderlyId() {
        return orderlyId;
    }

    public void setOrderlyId(int orderlyId) {
        this.orderlyId = orderlyId;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getOrderlyName() {
        return orderlyName;
    }

    public void setOrderlyName(String orderlyName) {
        this.orderlyName = orderlyName;
    }

    @Override
    public String toString() {
        return "Orderly{" +
                "orderlyId=" + orderlyId +
                ", rang='" + rang + '\'' +
                ", orderlyName='" + orderlyName + '\'' +
                '}';
    }

    public Duty getDuty() {
        return duty;
    }

    public void setDuty(Duty duty) {
        this.duty = duty;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
