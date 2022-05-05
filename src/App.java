import jakarta.persistence.*;

public class App {
    public static void main(String[] args) throws Exception 
    {

        System.out.println("Hello, World!");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3");
        EntityManager em = factory.createEntityManager();

        
        // The EntityManager object lets us find, create, update, and delete individual
        // instances of our entity classes.
        
        System.out.println("Example 1: find an entity based on its primary key.");
        Automobile car1 = em.find(Automobile.class, 1); // parameter 2: the primary key value.
        if (car1 != null) {
            System.out.println("Museum with ID 1: " + car1);
        }
        else {
            System.out.println("There is no car with ID 1");
        }


        // The next "if" block will protect me if I run this code multiple times.
        // Otherwise we'll keep trying to create an object with a non-unique primary key,
        // and crash the program.
        if (firstMuseum == null) {
            System.out.println();
            System.out.println("Example 2: creating a new entity.");
            
            // We must begin and later end a transaction when modifying the database.
            em.getTransaction().begin();
            
            Museum newMuseum = new Museum(4, "Metropolitan Museum of Art of New York City", 
                "New York, NY");
            // The previous line just creates an object. It's not in the database yet.
            // The next line tells JPA to "stage" the object
            em.persist(newMuseum);

            // Committing the transaction will push/"flush" the changes to the database.
            em.getTransaction().commit();
            System.out.println("Museum " + newMuseum + " added to database. Go check DataGrip if you don't believe me!");

            // Example 3: updating an entity.
            Museum fromDatabase = em.find(Museum.class, 4);
            em.getTransaction().begin();
            fromDatabase.setLocation("Manhattan, New York, NY");
            // This object is already staged, since it was retrieved from the EntityManager.
            // We just need to flush the change.
            em.getTransaction().commit();
        }

        System.out.println();
        System.out.println("Example #3: retrieving an object without its primary key");
        // EntityManager.find can only look at primary keys. To do other queries,
        // we have to write "JPQL" -- a language very similar to SQL.

        // Suppose we want to find "Museum of Latin American Art". We'd write this query:
        // SELECT * FROM MUSEUMS WHERE NAME = 'Museum of Latin American Art'

        // JPA doesn't use SQL; it uses JPQL, which doesn't select from arbitrary tables,
        // but instead selects from the entity types known to JPA, like MUSEUMS.
        String jpaQuery = "SELECT m FROM museums m WHERE m.location = " +
            "'Manhattan, New York, NY'";


        // The important bit is "MUSEUMS m"; this tells the query to iterate over the
        // MUSEUMS table one row at a time, temporarily calling each row "m". We can then
        // refer to "m" like it's an object, in order to select a row or filter based 
        // on its columns.

        // createQuery returns a Query object, which can be executed with getSingleResult
        // if it always returns <= 1 object.
        Museum molaa = em.createQuery(jpaQuery, Museum.class).getSingleResult();
        System.out.println("MOLAA retrieved: " + molaa);

        // If we want to SAFELY involve user input, we use a TypedQuery.
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a museum name: ");
        String name = input.nextLine();

        // A TypedQuery is strongly typed; a normal Query would not be.
        var namedMuseum = em.createQuery("SELECT m FROM museums m WHERE "
            + "m.name = ?1", Museum.class);
        namedMuseum.setParameter(1, name);
        try {
            Museum requested = namedMuseum.getSingleResult();
            System.out.println("Your requested museum: " + requested);
        }
        catch (NoResultException ex) {
            System.out.println("Museum with name '" + name + "' not found.");
        }

        System.out.println();
        System.out.println("Example #4: Using JPQL to select all museums");
        // This is simple. Just omit the WHERE, and use .getResultList().
        var museums = em.createQuery("select m from museums m", Museum.class).getResultList();

        for (Museum m : museums) {
            System.out.println(m);
        }
    

        
    }
}
