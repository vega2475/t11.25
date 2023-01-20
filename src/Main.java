import java.util.Locale;

/**
 made in Russia
 Плагин для Microsoft Word 2023 "Выравнивание текста".
 */

public class Main {
    public static void winMain() throws Exception {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Util.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiForm().setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        winMain();
    }


}