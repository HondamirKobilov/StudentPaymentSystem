package com.example.StudentPaymentSystem.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "transactions"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phone;
    private int course;
    private String groupName;
    private String faculty;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("student")
    private List<Transaction> transactions;


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public int getCourse() {
        return course;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getFaculty() {
        return faculty;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
