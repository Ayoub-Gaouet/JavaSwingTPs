package Tp4;
public class test {

    public static void main(String[] args) {
        System.out.println("Debut programme principale...");
        Traitement ta= new Traitement("A");
        ta.start();

        Traitement tb= new Traitement("B");
        tb.start();

        Traitement tc= new Traitement("C");
        tc.start();


        try {
            //permet d'arreter un programme jusq'au mort
            ta.join();
            tb.join();
            tc.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("end programme principale...");

    }
}


