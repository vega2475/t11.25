import javax.swing.*;


public class GuiForm extends JFrame {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton formattingButton;
    private JTextField widthField;
    private JPanel panelMain;

    public GuiForm(){
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        formattingButton.addActionListener(e -> {
            try {
                String text = (textArea1.getText());
                String widthStr = widthField.getText();
                int width = Integer.parseInt(widthStr);
                StringBuilder textOut = Solution.solve(text, width);
                textArea2.setText(textOut.toString());
            }
            catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Некорректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


}
