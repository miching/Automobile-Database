import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "package")
public class Package 
{
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageID;

    @Column(length = 100, nullable = false)
    private String packageName;

    @OneToMany(mappedBy = "package1")
    private Set<PackageFeature> packageFeatures;


    
}
