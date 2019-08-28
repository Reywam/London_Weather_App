package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToMany(mappedBy = "idDate", fetch = FetchType.LAZY)
    private List<WeatherInfo> data;

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

    public List<WeatherInfo> getData() {
        return data;
    }

    public void setData(List<WeatherInfo> data) {
        this.data = data;
    }
}