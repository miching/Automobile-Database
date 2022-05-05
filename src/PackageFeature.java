import jakarta.persistence.*;

@Entity (name = "packageFeature")
public class PackageFeature 
{
    //How to do unidirectional for a many to many

    @Id
    @JoinColumn(name = "packageID")
    @ManyToOne
    private Package package1;

    @Id
    @JoinColumn(name = "featureID")
    @ManyToOne
    private Feature feature;

}
