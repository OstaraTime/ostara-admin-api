package ostara2.api.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private Integer personId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID", nullable = false)
    private Dept dept;

    // Getters and Setters
    public Integer getPersonId() { return personId; }
    public void setPersonId(Integer personId) { this.personId = personId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Dept getDept() { return dept; }
    public void setDept(Dept dept) { this.dept = dept; }
}
