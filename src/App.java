import jakarta.persistence.*;

public class App {
    public static void main(String[] args) throws Exception 
    {

        System.out.println("Hello, World!");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3");
        EntityManager em = factory.createEntityManager();
        
    }
}
