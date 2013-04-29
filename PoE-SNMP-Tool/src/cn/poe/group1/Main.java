package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.entity.Switch;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point of the poe snmp diagnose tool.
 */
public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    private EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        Main main = new Main(new PropertyFileConfig());
    }
    
    public Main(Configuration config) {
        log.info("measurement interval : {}", config.getMeasurementInterval());
        
        Switch sw = new Switch();
        sw.setIdentifier("testid");
        sw.setType("testtype");
        
        entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
	entityManager.getTransaction().begin();
        entityManager.persist(sw);
        entityManager.getTransaction().commit();
	entityManager.close();
        
        entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
        List<Switch> result = entityManager.createQuery( "from Switch", Switch.class ).getResultList();
	for ( Switch res : result ) {
		System.out.println( "Switch (" + res.getIdentifier() + ") : " + res.getType());
	}
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
