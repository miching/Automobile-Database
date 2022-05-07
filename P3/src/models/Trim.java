package models;

import jakarta.persistence.*;

@Entity(name = "trim")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"trim_name","model"}))

public class Trim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trim_id;

//    @Column(nullable = false)
//    @ManyToOne
//    private Model model;

    @Column(nullable = false)
    private String trim_name;

    @Column(nullable = false)
    private int cost;


}
