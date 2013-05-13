package cn.poe.group1.db;

import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The implementation for the MeasurementBackend interface.
 */
public class MeasurementDatabase implements MeasurementBackend {
    private EntityManager entityManager;
    
    public MeasurementDatabase(String entityManagerFactoryName) {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory(entityManagerFactoryName);
        entityManager = factory.createEntityManager();
    }

    @Override
    public void saveMeasurement(Measurement measurement) {
        entityManager.getTransaction().begin();
        entityManager.persist(measurement);
        entityManager.getTransaction().commit();
    }

    @Override
    public void persistSwitch(Switch sw) {
        entityManager.getTransaction().begin();
        entityManager.persist(sw);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteSwitch(Switch sw) {
        entityManager.getTransaction().begin();
        entityManager.remove(sw);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Switch> retrieveAllSwitches() {
        Query query = entityManager.createQuery("SELECT s FROM Switch s", Switch.class);
        return query.getResultList();
    }

    @Override
    public Switch getSwitchById(String id) {
        return entityManager.find(Switch.class, id);
    }
    
    @Override
    public void persistPort(Port port) {
        entityManager.getTransaction().begin();
        entityManager.persist(port);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deletePort(Port port) {
        entityManager.getTransaction().begin();
        entityManager.remove(port);
        entityManager.getTransaction().commit();
    }

    @Override
    public Port getPortById(Long id) {
        return entityManager.find(Port.class, id);
    }

    @Override
    public List<Port> retrieveAllPorts(Switch sw) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Port> query = builder.createQuery(Port.class);
        Root from = query.from(Port.class);
        query.select(from);
        query.where(builder.equal(from.get("sw"), sw));
        TypedQuery<Port> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Measurement> queryMeasurementsBySwitch(Switch sw, Date startTime, Date endTime) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Measurement> query = builder.createQuery(Measurement.class);
        Root from = query.from(Measurement.class);
        query.select(from);
        
        List<Predicate> predicates = new ArrayList<>();
        
        Path<String> path = from.get("port").get("sw");
        predicates.add(builder.equal(path, sw));
        if (startTime != null && endTime != null) {
            predicates.add(builder.between(from.get("measureTime"), 
                startTime, endTime));
        }
        
        query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        query.orderBy(builder.asc(from.get("measureTime")));
        
        TypedQuery<Measurement> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Measurement> queryMeasurementsByPort(Port port, Date startTime, Date endTime) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Measurement> query = builder.createQuery(Measurement.class);
        Root from = query.from(Measurement.class);
        query.select(from);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(from.get("port"), port));
        if (startTime != null && endTime != null) {
            predicates.add(builder.between(from.get("measureTime"), 
                startTime, endTime));
        }
        
        query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        query.orderBy(builder.asc(from.get("measureTime")));
        
        TypedQuery<Measurement> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Measurement> queryMeasurementsBySwitch(Switch sw) {
        return queryMeasurementsBySwitch(sw, null, null);
    }

    @Override
    public List<Measurement> queryMeasurementsByPort(Port port) {
        return queryMeasurementsByPort(port, null, null);
    }
}
