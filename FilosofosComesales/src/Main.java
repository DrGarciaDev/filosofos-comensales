/**
 *
 * @author Luis Alberto García Rodríguez
 */
public class Main {
    /**
     * Método principal de la clase.
     * 
     * El bucle de los filósofos será:
     * - Sentarse
     * - Comer
     * - Pensar
     * 
     * @param args Sin uso
     */
    public static void main(String[] args){
        
        // El problema se define con 5 filósofos
        Integer filosofos = 5;
        
        // Creamos un mesero, que se encargará de asignar asientos para comer. Siempre tendrá un asiento menos que el número de filósofos.
        Mesero mesero = new Mesero(filosofos-1);
        
        // Creamos los tenedores, que serán igual al número de filósofos, y los incializamos a disponible.
        Tenedor[] tenedores = new Tenedor[filosofos];
        for (Integer i = 0; i < tenedores.length; i++) {
            tenedores[i] = new Tenedor(i);
        }
        
        // Creamos los filósofos, le asignamos su tenedor izquierdo y derecho. También le pasamos el objeto mesero para saber si hay sitio en la mesa.
        Thread[] hFilosofos = new Filosofo[filosofos];
        for (Integer i = 0; i < hFilosofos.length; i++) {
            Integer tenedor_i = i;
            Integer tenedor_d = i+1;
            if (i == hFilosofos.length-1) {
                tenedor_d = 0;
            }
            hFilosofos[i] = new Filosofo(i, tenedores[tenedor_i], tenedores[tenedor_d], mesero);
        }
        
        // Inicializamos los hilos de los filósofos para que comiencen el bucle.
        for (Integer i = 0; i < hFilosofos.length; i++) {
            hFilosofos[i].start();
        }
    }
}
