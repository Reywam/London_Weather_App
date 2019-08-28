package app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Dates {
    public Dates() {
        date = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "idDate", fetch = FetchType.LAZY)
    private List<WeatherData> data;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}