package org.example;

import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@ManagedBean(name = "MainBean")
public class MainBean implements Serializable {
    PointChecker pointChecker = new PointChecker();
    List<Point> points = new ArrayList<>();
    private Point point = new Point();

    EntityManagerFactory entityManagerFactory;

    public MainBean() {
        entityManagerFactory = Persistence.createEntityManagerFactory("database");
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    public void addToList(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        long time1 = System.nanoTime();
        point.setHit(pointChecker.check(point.getX(), point.getY(), point.getR()));
        System.out.println(point.getX());
        long scriptTime = System.nanoTime() - time1;
        point.setScriptTime(scriptTime);
        point.setCurrTime(Instant.now());
        points.add(new Point(point));
        transaction.begin();
        entityManager.persist(new Point(point));
        transaction.commit();
        point = new Point();

    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }


}
