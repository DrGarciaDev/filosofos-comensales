/**
 *
 * @author Luis Alberto García Rodríguez
 */
public class Mesero {
    
    private Integer asientos;
    
    /**
     * Método constructor.
     * @param asientos Número de asientos a asignar.
     */
    public Mesero(Integer asientos){
        this.asientos = asientos;
    }
    
    /**
     * Método darPase. Dará un asiento y restará de los disponibles. 
     * Es un método sincronizado, puesto que cuando se quede sin asientos que asignar, 
     * quedará esperando hasta que haya alguno libre.
     * @throws InterruptedException Excepción que salta si el hilo se interrumpe.
     */
    public synchronized void darAsiento() throws InterruptedException {
        // Si no hay asientos libres esperamos hasta que los haya.
        while (this.asientos == 0) {
            wait();
        }
        // Cuando hay asientos, reducimos el número de asientos libres.
        this.asientos--;
    }
    
    /**
     * Método soltarPase. 
     * Liberará un asiento y lo sumará a los disponibles. 
     * Es un método sincronizado que notificará a quien esté esperando asientos libres.
     * @throws InterruptedException Excepción que salta si el hilo se interrumpe.
     */
    public synchronized void soltarAsiento() throws InterruptedException {
        // Cuando ya ha comido el filósofo, liberá el asiento y notifica al mesero para que lo ponga libre. 
        this.asientos++;
        notify();
    }
}