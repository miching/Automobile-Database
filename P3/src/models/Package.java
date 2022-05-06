package models;
import jakarta.persistence.*;

@Entity(name = "package")

public class Package {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int package_id;

    @Column(nullable = false)
    private String package_name;

}
