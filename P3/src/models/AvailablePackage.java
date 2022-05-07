package models;

import jakarta.persistence.*;

@Entity(name = "availablepackage")

public class AvailablePackage {

//    @Column(nullable = false)
//    @ManyToOne
//    private Trim trim;

//    @Column(nullable = false)
//    @ManyToOne
//    private Package apackage;

    @Column(nullable = false)
    private int cost;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int available_id;

}
