import static java.lang.Thread.sleep;

/**
 *
 * @author Luis ALberto García Rodríguez
 */
public class Filosofo extends Thread{
    
    private Integer idFilosofo;  
    private Tenedor tenedor_i;
    private Tenedor tenedor_d;
    private Mesero  mesero;
    
    /**
     * Constructor de la clase filósofo.
     * @param id Id del filósofo
     * @param tenedor_i Tenedor asignado a la mano izquierda
     * @param tenedor_d Tenedor asignado a la mano derecha
     * @param mesero Mesero encargado de dar asientos
     */
    public Filosofo(Integer id, Tenedor tenedor_i, Tenedor tenedor_d, Mesero mesero) {
        this.idFilosofo = id;
        this.tenedor_i  = tenedor_i;
        this.tenedor_d  = tenedor_d;
        this.mesero     = mesero;
    }
    
    /**
     * Método de inicialización de los hilos. 
     * Realizará en bucle las fases de los filósofos.
     */
    @Override
    public void run()  {
        while(true) {
            try {
                this.sentarse();
                this.comer();
                this.pensar();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Método para sentarse. 
     * Pedirá al mesero un asiento, y cuando se lo dé, 
     * el filósofo se sentará en la mesa.
     * @throws InterruptedException 
     */
    private void sentarse() throws InterruptedException {
        // Comprobamos si hay sitio en la mesa. Si lo hay, el mesero nos da un asiento.
        this.mesero.darAsiento();
        System.out.println("El filósofo " + idFilosofo + " se ha SENTADO.");
    }
    
    /**
     * Método para comer. 
     * El filósofo sentado tomará ambos tenedores cuando estén disponibles, 
     * comerá, y los soltará de nuevo. 
     * Tardará 10 segundos en comer.
     * @throws InterruptedException 
     */
    private void comer() throws InterruptedException {
        // tomamos los tenedores, si no están libres, esperamos a que lo estén.
        this.tenedor_i.tomarTenedor(idFilosofo);
        this.tenedor_d.tomarTenedor(idFilosofo);
        
        // Una vez tenemos ambos tenedores, comemos. Tarda 10 segundos en comer.
        System.out.println("El filósofo " + idFilosofo + " ESTÁ COMIENDO.");
        sleep(10000);
        
        // Soltamos los tenedores para que otro filósofo los pueda usar.
        this.tenedor_i.soltarTenedor(idFilosofo);
        this.tenedor_d.soltarTenedor(idFilosofo);
    }
    
    /**
     * Método pensar. El filósofo se levantará de la mesa, 
     * liberará un asiento que notificará al mesero, y se dedicará a pensar.
     * @throws InterruptedException 
     */
    private void pensar() throws InterruptedException {
        // Cuando ha comido, el mesero lo saca de la mesa y libera un asiento para que otro filósofo pueda pedirlo y el filósofo se queda pensando.
        this.mesero.soltarAsiento();
        System.out.println("El filósofo " + idFilosofo + " está PENSANDO.");
    }
}