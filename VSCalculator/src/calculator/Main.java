package calculator;

/**
 *
 * @author voidxy
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }

        V_Calculator view = new V_Calculator();
        M_Calculator model = new M_Calculator();

        C_Calculator calculator = new C_Calculator(view, model);
        calculator.run();
    }
}
