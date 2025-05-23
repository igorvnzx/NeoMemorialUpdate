package ui;

import service.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MainGui {
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static SistemaFunerario sistemaFunerario = new SistemaFunerario();  // Instanciando o SistemaFunerario

    public static void main(String[] args) {
        // Criar e exibir a janela de login
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);  // Centraliza a janela

        // Criar o painel de login
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));  // Layout em grade
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Adiciona margens

        // Adicionar componentes de login
        JLabel userLabel = new JLabel("Usuário:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordText = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Adicionar componentes ao painel
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(new JLabel());  // Espaço vazio
        panel.add(loginButton);

        // Configurar a janela de login
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        // Ação de login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                char[] password = passwordText.getPassword();

                // Validação simples (usuário "admin" e senha "12345")
                if (user.equals("admin") && new String(password).equals("12345")) {
                    // Fechar a janela de login
                    frame.dispose();
                    // Chamar a tela de escolha do sistema
                    showSystemChoice();
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para exibir a tela de escolha do sistema
    private static void showSystemChoice() {
        // Criar e exibir a janela
        JFrame frame = new JFrame("Escolha o Sistema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);  // Centraliza a janela

        // Criar o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Adiciona margens

        // Adicionar título
        JLabel titleLabel = new JLabel("Escolha o sistema que deseja acessar:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Botões para escolher o sistema
        JButton sistemaFinanceiroButton = new JButton("Sistema Financeiro");
        sistemaFinanceiroButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sistemaFinanceiroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSistemaFinanceiro(frame);
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sistemaFinanceiroButton);

        JButton sistemaFunerarioButton = new JButton("Sistema Funerário");
        sistemaFunerarioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sistemaFunerarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSistemaFunerario(frame);
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sistemaFunerarioButton);

        // Adicionar o novo botão para o sistema de gestão de clientes
        JButton sistemaClientesButton = new JButton("Sistema de Gestão de Clientes");
        sistemaClientesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sistemaClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSistemaClientes(frame);
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sistemaClientesButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Exibir o painel
        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para exibir o sistema financeiro
    private static void mostrarSistemaFinanceiro(JFrame frame) {
        // Criar painel do sistema financeiro
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Exemplo de campo de entrada para cliente
        JLabel nomeLabel = new JLabel("Nome do Cliente:");
        JTextField nomeField = new JTextField();
        nomeField.setMaximumSize(new Dimension(200, 30));
        panel.add(nomeLabel);
        panel.add(nomeField);

        // Adicionar botão para gerar carnê
        JButton gerarCarneButton = new JButton("Gerar Carnê");
        gerarCarneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulação de criação de cliente e carnê
                // Garantir que você está passando todos os parâmetros necessários para o construtor
                Cliente cliente = new Cliente(1, nomeField.getText());

                BoletoService boletoService = new BoletoService();
                Carne carne = boletoService.gerarCarne(cliente, 200.0, 3, LocalDate.now().minusDays(10));
                JOptionPane.showMessageDialog(frame, "Carnê gerado para " + cliente.getNome());

                // Exibir opções de pagamento após gerar o carnê
                mostrarOpcoesDePagamento(frame, carne, boletoService);
            }
        });
        panel.add(gerarCarneButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);  // Atualiza o painel com o novo conteúdo
        frame.revalidate();
        frame.repaint();
    }

    // Método para mostrar as opções de pagamento após gerar o carnê
    private static void mostrarOpcoesDePagamento(JFrame frame, Carne carne, BoletoService boletoService) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Escolha a opção de pagamento:");
        panel.add(label);

        // Botão para pagar boleto
        JButton pagarBoletoButton = new JButton("Pagar Boleto");
        pagarBoletoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String metodo = JOptionPane.showInputDialog("Escolha o método de pagamento (pix/cartao): ");
                int idBoleto = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do boleto a ser pago:"));

                boletoService.marcarComoPago(idBoleto);
                JOptionPane.showMessageDialog(frame, "Pagamento realizado com sucesso!");

                // Exibir boletos inadimplentes após o pagamento
                mostrarBoletosInadimplentes(frame, boletoService);
            }
        });
        panel.add(pagarBoletoButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Método para exibir boletos inadimplentes
    private static void mostrarBoletosInadimplentes(JFrame frame, BoletoService boletoService) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Boletos Inadimplentes:");
        panel.add(label);

        // Obter a lista de boletos inadimplentes
        java.util.List<Boleto> boletosInadimplentes = boletoService.listarInadimplentes();

        // Verificar se há boletos inadimplentes
        if (boletosInadimplentes.isEmpty()) {
            // Se não houver boletos, exibe a mensagem "Nenhuma Pendência"
            panel.add(new JLabel("Nenhuma Pendência"));
        } else {
            // Se houver boletos, exibe cada um deles
            for (Boleto b : boletosInadimplentes) {
                panel.add(new JLabel("Boleto ID: " + b.getId() + " - Cliente: " + b.getCliente().getNome() + " - Vencimento: " + b.getVencimento()));
            }
        }

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Método para exibir o sistema funerário
    private static void mostrarSistemaFunerario(JFrame frame) {
        // Criar painel do sistema funerário
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Sistema Funerário: Escolha uma opção");
        panel.add(label);

        // Botão para adicionar plano
        JButton adicionarPlanoButton = new JButton("Adicionar Plano");
        adicionarPlanoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePlano = JOptionPane.showInputDialog("Nome do Plano:");
                double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do Plano:"));
                String formaPagamento = JOptionPane.showInputDialog("Forma de Pagamento:");
                sistemaFunerario.adicionarPlano(nomePlano, preco, formaPagamento);
                JOptionPane.showMessageDialog(frame, "Plano '" + nomePlano + "' adicionado.");
            }
        });
        panel.add(adicionarPlanoButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}
