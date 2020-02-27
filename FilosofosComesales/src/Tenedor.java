/**
 *
 * @author Luis Alberto García Rodríguez
 */
public class Tenedor{
    
    private Integer idTenedor;
    private Boolean disponible;
    
    /**
     * Constructor de la clase tenedor. 
     * Seteará la ID y pondrá como disponible el tenedor.
     * @param id Id del tenedor.
     */
    public Tenedor(Integer id) {
        this.idTenedor      = id;
        this.disponible     = true;
    }
    
    /**
     * Método tomarTenedor. 
     * Es un método sincronizado que permitirá tomar un tenedor si está disponible, 
     * y si no lo está esperar a que lo esté.
     * @param idFilosofo Filósofo que toma el tenedor.
     * @throws InterruptedException Excepción que salta si el hilo se interrumpe.
     */
    public synchronized void tomarTenedor(Integer idFilosofo) throws InterruptedException {
        // Si el tenedor no está disponible, esperamos a que esté.
        while(this.disponible == false) {
            wait();
        }
        // Una vez está disponible, se lo asignamos al filósofo y lo ponemos como no disponible.
        this.disponible = false;
        System.out.println("El filósofo " + idFilosofo + " TOMÓ el tenedor " + this.idTenedor + ".");
    }
    
    /**
     * Método soltarTenedor. 
     * Método sincronizado que permitirá soltar el tenedor, 
     * notificando a los que estén esperándolo.
     * @param idFilosofo Filósofo que soltará el tenedor.
     */
    public synchronized void soltarTenedor(Integer idFilosofo) {
        // Cuando el filósofo termina de comer, pone el tenedor disponible para que lo pueda utilizar otro filósofo.
        this.disponible = true;
        // Y notificamos a los que están esperando que ya está disponible.
        notify();
        System.out.println("El filósofo " + idFilosofo + " SOLTÓ el tenedor " + this.idTenedor + ".");
    }
}