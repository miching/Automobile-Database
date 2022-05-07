package models;

import jakarta.persistence.*;

@Entity(name = "model")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"model_id","year"}))
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int model_id;

    @Column(nullable = false)
    private String model_name;

    @Column(nullable = false)
    private int year;

}
