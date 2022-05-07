import jakarta.persistence.*;

@Entity (name = "packageFeature")
//@IdClass(PackageFeature.class)
public class PackageFeature 
{
    //How to do unidirectional for a many to many

    //How to create tables in datagrip

    @Id
    @JoinColumn(name = "packageID")
    @ManyToOne
    private Package package1;

    @Id
    @JoinColumn(name = "featureID")
    @ManyToOne
    private Feature feature;

}
