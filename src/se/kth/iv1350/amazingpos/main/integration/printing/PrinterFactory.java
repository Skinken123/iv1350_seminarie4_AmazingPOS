package se.kth.iv1350.amazingpos.main.integration.printing;

/**
 * A factory for creating instances of the <code>Printer</code> interface.
 * The factory is implemented as a singleton.
 */
public class PrinterFactory {
    private static final PrinterFactory INSTANCE = new PrinterFactory();
    private Printer defaulPrinter = new SwedishPrint();
    private Printer englishPrinter = new EnglishPrint();

    private PrinterFactory() {
    }

    /**
     * Gets the only instance of the <code>PrinterFactory</code> class.
     * @return the only instance of the <code>PrinterFactory</code> class.
     */
    public static PrinterFactory getPrinterFactory() {
        return INSTANCE;
    }
    /**
     * Creates an instance of the <code>Printer</code> interface.
     * @return the default printer.
     */
    public Printer getDefaultPrinter() {
        return defaulPrinter;
    }
}
