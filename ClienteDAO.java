package service;

import model.Cliente;
import model.Pagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public List<Cliente> clientes = new ArrayList<>();
    private int idCounter = 1;  // Contador para gerar IDs automáticos para clientes

    // Função para cadastrar um novo cliente
    public void cadastrarCliente(String nome, String endereco, String telefone) {
        Cliente cliente = new Cliente(idCounter++, nome, endereco, telefone);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente.getNome());
    }

    // Método público para acessar a lista de clientes
    public List<Cliente> getClientes() {
        return clientes;  // Retorna a lista de clientes
    }

    // Função para listar todos os clientes
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome());
            }
        }
    }

    // Função para buscar um cliente por ID
    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;  // Cliente não encontrado
    }

    // Função para atualizar as informações pessoais de um cliente
    public void atualizarCliente(int id, String nome, String endereco, String telefone) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            cliente.setNome(nome);
            cliente.setEndereco(endereco);
            cliente.setTelefone(telefone);
            System.out.println("Cliente atualizado: " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Função para excluir um cliente
    public void excluirCliente(int id) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente excluído: " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Função para adicionar um pagamento ao histórico de um cliente
    public void adicionarPagamento(int idCliente, double valor, LocalDate dataPagamento) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            Pagamento pagamento = new Pagamento(valor, dataPagamento);
            cliente.getHistoricoPagamentos().add(pagamento);
            System.out.println("Pagamento de R$ " + valor + " registrado para o cliente " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
