package app.domain;



import javax.persistence.*;

@Entity
@Table(name = "balance",schema = "public")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Value")
    private Long value = 0L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
