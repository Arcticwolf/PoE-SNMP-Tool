package cn.poe.group1.entity;

/**
 *
 * @author sauron
 */
public class PortData {

    private Port port;
    private Integer avgCpeExtPsePortPwrMax; // milliwatts
    private Integer avgCpeExtPsePortPwrAllocated; // milliwatts
    private Integer avgCpeExtPsePortPwrAvailable; // milliwatts
    private Integer avgCpeExtPsePortPwrConsumption; // milliwatts
    private Integer avgCpeExtPsePortMaxPwrDrawn; // milliwatts

    public PortData()
    {
        this.avgCpeExtPsePortMaxPwrDrawn = 0;
        this.avgCpeExtPsePortPwrAllocated = 0;
        this.avgCpeExtPsePortPwrAvailable = 0;
        this.avgCpeExtPsePortPwrConsumption = 0;
        this.avgCpeExtPsePortPwrMax = 0;
    }
    
    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Integer getAvgCpeExtPsePortPwrMax() {
        return avgCpeExtPsePortPwrMax;
    }

    public void setAvgCpeExtPsePortPwrMax(Integer avgCpeExtPsePortPwrMax) {
        this.avgCpeExtPsePortPwrMax = avgCpeExtPsePortPwrMax;
    }

    public Integer getAvgCpeExtPsePortPwrAllocated() {
        return avgCpeExtPsePortPwrAllocated;
    }

    public void setAvgCpeExtPsePortPwrAllocated(Integer avgCpeExtPsePortPwrAllocated) {
        this.avgCpeExtPsePortPwrAllocated = avgCpeExtPsePortPwrAllocated;
    }

    public Integer getAvgCpeExtPsePortPwrAvailable() {
        return avgCpeExtPsePortPwrAvailable;
    }

    public void setAvgCpeExtPsePortPwrAvailable(Integer avgCpeExtPsePortPwrAvailable) {
        this.avgCpeExtPsePortPwrAvailable = avgCpeExtPsePortPwrAvailable;
    }

    public Integer getAvgCpeExtPsePortPwrConsumption() {
        return avgCpeExtPsePortPwrConsumption;
    }

    public void setAvgCpeExtPsePortPwrConsumption(Integer avgCpeExtPsePortPwrConsumption) {
        this.avgCpeExtPsePortPwrConsumption = avgCpeExtPsePortPwrConsumption;
    }

    public Integer getAvgCpeExtPsePortMaxPwrDrawn() {
        return avgCpeExtPsePortMaxPwrDrawn;
    }

    public void setAvgCpeExtPsePortMaxPwrDrawn(Integer avgCpeExtPsePortMaxPwrDrawn) {
        this.avgCpeExtPsePortMaxPwrDrawn = avgCpeExtPsePortMaxPwrDrawn;
    }
}
