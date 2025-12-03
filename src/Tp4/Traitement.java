package Tp4;

class Traitement extends Thread {
    String nom;
    Traitement(String nom){
        this.nom=nom;
    }
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(" traitement "+i+" du thread "+nom);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
