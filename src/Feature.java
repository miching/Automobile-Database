import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "feature")
public class Feature 
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int featureID;
/*
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @OneToMany //(mappedBy = "feature")
    @JoinColumn(name = "feature")
    private Set<Package> packageFeature;

    @OneToMany //(mappedBy = "feature")
    @JoinColumn(name = "feature")
    private Set<Trim> trimFeature;
 */   
}
