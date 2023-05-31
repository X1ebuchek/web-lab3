package org.example;

import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
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
    MBeanServer mBeanServer;
    HitAndMissMBean hitAndMiss;
    HitPercentMBean hitPercent;


    public MainBean() {
        entityManagerFactory = Persistence.createEntityManagerFactory("database");
        this.mBeanServer = ManagementFactory.getPlatformMBeanServer();
        this.hitPercent = new HitPercent();
        this.hitAndMiss = new HitAndMiss();
        try {
            mBeanServer.registerMBean(hitPercent, new ObjectName("Xlebuchek:name=hitPercent"));
            mBeanServer.registerMBean(hitAndMiss, new ObjectName("Xlebuchek:name=hitAndMiss"));
        }catch (Exception e) {
            e.printStackTrace();
        }
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
        short check = pointChecker.check(point.getX(), point.getY(), point.getR());
        if (check==-1){
            hitAndMiss.shotOutOfBounds();
            hitPercent.shot(false);
            return;
        }else if(check==0){
            point.setHit(false);
            hitPercent.shot(false);
            hitAndMiss.shot(false);
        } else{
            point.setHit(true);
            hitPercent.shot(true);
            hitAndMiss.shot(true);
        }
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
