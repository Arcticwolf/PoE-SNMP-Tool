package cn.poe.group1.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The port entity represents a port on a switch.
 */
@Entity
public class Port implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Switch sw;
    private Integer portNumber;
    private String comment;
    
    public Port() {
        // needs to be here because of hibernate
    }
    
    public Port(Switch sw, Integer portNumber, String comment) {
        this.sw = sw;
        this.portNumber = portNumber;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Switch getSw() {
        return sw;
    }

    public void setSw(Switch sw) {
        this.sw = sw;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Port.class).add("id", id).add("switch", getSw())
                .add("portNumber", getPortNumber()).add("comment", getComment())
                .omitNullValues().toString();
    }
}
