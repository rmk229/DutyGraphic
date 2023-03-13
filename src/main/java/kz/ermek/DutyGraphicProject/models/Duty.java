package kz.ermek.DutyGraphicProject.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Duty")
public class Duty {
    @Id
    @Column(name = "duty_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dutyId;

    @NotEmpty(message = "Duty name should not be empty")
    @Size(min = 2, max = 100, message = "Duty name should be between 2 and 100 characters")
    @Column(name = "duty_name")
    private String dutyName;

    @Column(name = "rang")
    @Size(min = 2, max = 100, message = "Rang should be between 2 and 100 characters")
    @NotEmpty(message = "Rang should not be empty")
    private String rang;

    @OneToMany(mappedBy = "duty")
    private List<Orderly> orderlies;

    public Duty() {

    }

    public Duty(String dutyName) {
        this.dutyName = dutyName;
    }


    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "Duty{" +
                "dutyId=" + dutyId +
                ", dutyName='" + dutyName + '\'' +
                ", rang='" + rang + '\'' +
                '}';
    }

    public List<Orderly> getOrderlies() {
        return orderlies;
    }

    public void setOrderlies(List<Orderly> orderlies) {
        this.orderlies = orderlies;
    }
}
