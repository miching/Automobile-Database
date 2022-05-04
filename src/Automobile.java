import org.eclipse.persistence.internal.expressions.FromAliasExpression;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name = "automobile")
public class Automobile
{

    @Id
    @Column(nullable = false)
    int autoID;

    
    int trimID;

    @Column(nullable = false, length = 100)
    String vin;
    
}
