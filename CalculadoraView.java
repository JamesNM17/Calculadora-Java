import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraView extends JFrame implements ActionListener {

    private JTextField display;
    private CalculadoraModel model = new CalculadoraModel();

    public CalculadoraView() {
        setTitle("Calculadora");
        setSize(320, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 30));

        criarDisplay();
        criarBotoes();

        setVisible(true);
    }

    private void criarDisplay() {
        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(20, 20, 20));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(display, BorderLayout.NORTH);
    }

    private void criarBotoes() {
        JPanel painel = new JPanel(new GridLayout(4, 4, 10, 10));
        painel.setBackground(new Color(30, 30, 30));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            estilizarBotao(botao);
            botao.addActionListener(this);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
    }

    private void estilizarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setFocusPainted(false);

        if (botao.getText().matches("[+\\-*/=]")) {
            botao.setBackground(new Color(255, 149, 0));
            botao.setForeground(Color.BLACK);
        } else if (botao.getText().equals("C")) {
            botao.setBackground(new Color(200, 60, 60));
            botao.setForeground(Color.WHITE);
        } else {
            botao.setBackground(new Color(60, 60, 60));
            botao.setForeground(Color.WHITE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            display.setText(display.getText() + cmd);
            return;
        }

        if (cmd.equals("C")) {
            display.setText("");
            return;
        }

        if (cmd.matches("[+\\-*/]")) {
            model.setNumero(Double.parseDouble(display.getText()));
            model.setOperador(cmd);
            display.setText("");
            return;
        }

        if (cmd.equals("=")) {
            try {
                double resultado = model.calcular(Double.parseDouble(display.getText()));
                display.setText(String.valueOf(resultado));
            } catch (ArithmeticException ex) {
                display.setText("Erro");
            }
        }
    }
}