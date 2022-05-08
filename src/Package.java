import java.util.Collections;
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

    //Bidirectional One-to-Many with availablePackges
    @OneToMany(mappedBy = "package1")
    private Set<AvailablePackage> availablePackages;

    //Unidirectional many to many with Features
    @ManyToMany 
    @JoinTable
    (

        name = "packageFeatures",
        joinColumns = @JoinColumn(name = "packageID"),
        inverseJoinColumns = @JoinColumn(name = "featureID")

    )
    private Set<Feature> packageFeatures;

    public Package() 
    {



    }

    public Package(String packageName) 
    {

        this.packageName = packageName;
        availablePackages = Collections.emptySet();
        packageFeatures = Collections.emptySet();

    }

    public int getPackageID() {
        return packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<AvailablePackage> getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(Set<AvailablePackage> availablePackages) {
        this.availablePackages = availablePackages;
    }

    public Set<Feature> getPackageFeatures() {
        return packageFeatures;
    }

    public void setPackageFeatures(Set<Feature> packageFeatures) {
        this.packageFeatures = packageFeatures;
    }

    
    
}
