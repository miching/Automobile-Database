import jakarta.persistence.*;

@Entity
public class TrimFeature
{
    
    @Id
    @JoinColumn(name = "featureID")
    @ManyToOne
    private Feature feature;

    @Id
    @JoinColumn(name = "trimID")
    @ManyToOne
    private Trim trim;

}
