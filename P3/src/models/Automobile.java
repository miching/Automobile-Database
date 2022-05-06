package models;

import jakarta.persistence.*;

@Entity(name = "automobile")

public class Automobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auto_id;

//    @ManyToOne
//    private Trim trim;

    @Column(unique = true)
    private String vin;

}
