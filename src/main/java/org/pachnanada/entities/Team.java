package org.pachnanada.entities;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by gautampachnanda on 01/10/15.
 */
@Entity(value = "teams", noClassnameStored = true)
public class Team {

    public Team(){

    }

    @Id
    private String name;
    private String location;
    private Double annualTurnover;

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAnnualTurnover(Double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Team) {
            Team that = (Team) object;
            return Objects.equal(this.name, that.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("ID", name).add("annualTurnover", annualTurnover).toString();
    }
}
