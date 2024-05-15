package se.kth.iv1350.amazingpos.main.integration;

/**
 * Represents a class which will create the external systems in the integration layer.
 */
public class ExternalSystemsCreator {
    ExternalAccountingSystem externalAS = new ExternalAccountingSystem();
    ExternalInventorySystem externalIS = new ExternalInventorySystem();

    /**
     * Gets the external accounting system.
     * @return returns an object resposible for communication with the external accounting system.
     */
    public ExternalAccountingSystem getExternalAS() {
        return externalAS;
    }
    
    /**
     * Gets the external inventory system.
     * @return returns an object resposible for communication with the external inventory system.
     */
    public ExternalInventorySystem getExternalIS() {
        return externalIS;
    }
}
