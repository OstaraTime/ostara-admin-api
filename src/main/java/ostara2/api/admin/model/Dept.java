package ostara2.api.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Integer deptId;

    @Column(name = "NAME")
    private String name;

    // Getters and Setters
    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
