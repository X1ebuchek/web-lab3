package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity(name = "kekw")
@Table(name = "kekw", schema = "public")
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name="HitsIdGenerator", sequenceName="HIT_ID", allocationSize = 1)
    @Column(name = "id")
    private long id;
    @Column(name = "x")
    private double x = 0;
    @Column(name = "y")
    private double y = 0;
    @Column(name = "r")
    private double r = 5;
    @Column(name = "hit")
    private boolean hit;
    @Column(name = "currTime")
    private Instant currTime;
    @Column(name = "scriptTime")
    private long scriptTime;

    public Point(double x, double y, double r, boolean hit, Instant currTime, long scriptTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.currTime = currTime;
        this.scriptTime = scriptTime;
    }

    public Point() {
    }

    public Point(Point point){
        this.x = point.x;
        this.y = point.y;
        this.r = point.r;
        this.hit = point.hit;
        this.currTime = point.currTime;
        this.scriptTime = point.scriptTime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        System.out.println(r);
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public Instant getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Instant currTime) {
        this.currTime = currTime;
    }

    public long getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(long scriptTime) {
        this.scriptTime = scriptTime;
    }
}
