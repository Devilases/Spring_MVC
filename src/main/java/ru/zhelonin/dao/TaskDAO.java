package ru.zhelonin.dao;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zhelonin.domain.Task;

@Repository
public class TaskDAO{

  private final SessionFactory sessionFactory;

  public TaskDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public List<Task> getAll(int offset, int limit){
    Query<Task> query = getSession().createQuery("select t from task t", Task.class);
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    return query.getResultList();
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public int getAllCount(){
    Query<Long> query = getSession().createQuery("select count(t) from task t", Long.class);
    return Math.toIntExact(query.getFirstResult());
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public Task getById(int id){
    Query<Task> query = getSession().createQuery("select t from task t where t.id = :ID", Task.class);
    query.setParameter("ID", id);
    return query.uniqueResult();
  }
  @Transactional(propagation = Propagation.REQUIRED)
  public void saveOrUpdate(Task task){
    getSession().persist(task);
  }
  @Transactional(propagation = Propagation.REQUIRED)
  public void delete(Task task){
    getSession().remove(task);
  }

  private Session getSession(){
    return sessionFactory.getCurrentSession();
  }
}
