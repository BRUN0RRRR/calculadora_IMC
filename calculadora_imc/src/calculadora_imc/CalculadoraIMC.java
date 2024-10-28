import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraIMC extends JFrame {
    private JTextField txtPeso, txtAltura;
    private JLabel labelResultado;
    private JButton btnCalcular, btnLimpar;

    public CalculadoraIMC() {
        setTitle("Calculadora de IMC");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Entrada de peso
        JPanel painelPeso = new JPanel();
        painelPeso.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField(10);
        painelPeso.add(txtPeso);

        // Entrada de altura
        JPanel painelAltura = new JPanel();
        painelAltura.add(new JLabel("Altura (m):"));
        txtAltura = new JTextField(10);
        painelAltura.add(txtAltura);

        // Resultado do IMC
        JPanel painelResultado = new JPanel();
        labelResultado = new JLabel("Resultado: ");
        painelResultado.add(labelResultado);

        // Botões de calcular e limpar
        JPanel painelBotoes = new JPanel();
        btnCalcular = new JButton("Calcular");
        btnLimpar = new JButton("Limpar");
        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnLimpar);

        // Adiciona todos os painéis ao JFrame
        add(painelPeso);
        add(painelAltura);
        add(painelResultado);
        add(painelBotoes);

        // Ações dos botões
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(txtPeso.getText());
            double altura = Double.parseDouble(txtAltura.getText());

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this, "Peso e altura devem ser maiores que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cálculo do IMC
            double imc = peso / (altura * altura);
            String interpretacao = interpretarIMC(imc);

            // Exibe o resultado com duas casas decimais
            labelResultado.setText(String.format("IMC: %.2f - %s", imc, interpretacao));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Insira valores numéricos válidos para peso e altura.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String interpretarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    private void limparCampos() {
        txtPeso.setText("");
        txtAltura.setText("");
        labelResultado.setText("Resultado: ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraIMC().setVisible(true);
            }
        });
    }
}
