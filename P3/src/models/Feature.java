package models;

import jakarta.persistence.*;

@Entity(name = "feature")

public class Feature {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int package_id;

    @Column(unique = true)
    private String name;


}
