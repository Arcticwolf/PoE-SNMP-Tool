package cn.poe.group1;

import cn.poe.group1.api.Configuration;

/**
 * The main entry point of the poe snmp diagnose tool.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main(new PropertyFileConfig());
    }
    
    public Main(Configuration config) {
        System.out.println(config.getMeasurementInterval());
    }
}
