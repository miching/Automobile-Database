import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "feature")
public class Feature 
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int featureID;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    private Set<Package> packageFeature;

    private Set<Trim> trimFeature;

    private Set<Model> modelFeature;
    
}
