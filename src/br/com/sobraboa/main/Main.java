package br.com.sobraboa.main;

import br.com.sobraboa.model.*;
import br.com.sobraboa.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        EstabelecimentoService estService = new EstabelecimentoService();
        OfertaService ofertaService = new OfertaService();
        PedidoService pedidoService = new PedidoService();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========================================");
            System.out.println("          SISTEMA SOBRABOA              ");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Cadastrar Estabelecimento");
            System.out.println("4 - Listar Estabelecimentos");
            System.out.println("5 - Cadastrar Oferta");
            System.out.println("6 - Listar Ofertas");
            System.out.println("7 - Realizar Pedido");
            System.out.println("8 - Listar Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> {
                        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("E-mail: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String tel = scanner.nextLine();

                        Usuario u = usuarioService.cadastrar(new Usuario(nome, email, senha, tel));
                        System.out.println(">> Usuário cadastrado com Sucesso! ID: " + u.getIdUsuario());
                    }
                    case 2 -> {
                        System.out.println("\n--- LISTA DE USUÁRIOS ---");
                        ArrayList<Usuario> usuarios = usuarioService.consultar();
                        for (Usuario u : usuarios) {
                            System.out.println("ID: " + u.getIdUsuario() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail());
                        }
                    }
                    case 3 -> {
                        System.out.println("\n--- CADASTRO DE ESTABELECIMENTO ---");
                        System.out.print("ID do Usuário Proprietário: ");
                        int idUser = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome Fantasia: ");
                        String nome = scanner.nextLine();
                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();
                        System.out.print("Endereço: ");
                        String end = scanner.nextLine();
                        System.out.print("Cidade: ");
                        String cid = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String tel = scanner.nextLine();
                        System.out.print("Horário de Funcionamento: ");
                        String hor = scanner.nextLine();

                        Estabelecimento est = estService.cadastrar(new Estabelecimento(idUser, nome, desc, end, cid, tel, hor));
                        System.out.println(">> Estabelecimento cadastrado com Sucesso! ID: " + est.getIdEstabelecimento());
                    }
                    case 4 -> {
                        System.out.println("\n--- LISTA DE ESTABELECIMENTOS ---");
                        ArrayList<Estabelecimento> estabelecimentos = estService.consultar();
                        for (Estabelecimento e : estabelecimentos) {
                            System.out.println("ID: " + e.getIdEstabelecimento() + " | Nome: " + e.getNome() + " | Cidade: " + e.getCidade());
                        }
                    }
                    case 5 -> {
                        System.out.println("\n--- CADASTRO DE OFERTA ---");
                        System.out.print("ID do Estabelecimento: ");
                        int idEst = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID da Categoria (ex: 1): ");
                        int idCat = Integer.parseInt(scanner.nextLine());
                        System.out.print("Título: ");
                        String tit = scanner.nextLine();
                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();
                        System.out.print("Tipo (COMUM ou SACOLA_SURPRESA): ");
                        String tipo = scanner.nextLine();
                        System.out.print("Preço Original (ex: 30.00): ");
                        BigDecimal pOrig = new BigDecimal(scanner.nextLine());
                        System.out.print("Preço Promocional (ex: 10.00): ");
                        BigDecimal pProm = new BigDecimal(scanner.nextLine());
                        System.out.print("Quantidade Disponível: ");
                        int qtd = Integer.parseInt(scanner.nextLine());
                        System.out.print("Horário de Retirada (ex: 18:00 - 19:00): ");
                        String horRet = scanner.nextLine();

                        Oferta o = ofertaService.cadastrar(new Oferta(idEst, idCat, tit, desc, tipo, pOrig, pProm, qtd, LocalDateTime.now().plusHours(12), horRet, "imagem.png", true));
                        System.out.println(">> Oferta cadastrada com Sucesso! ID: " + o.getIdOferta());
                    }
                    case 6 -> {
                        System.out.println("\n--- LISTA DE OFERTAS ---");
                        ArrayList<Oferta> ofertas = ofertaService.consultar();
                        for (Oferta o : ofertas) {
                            System.out.println("ID: " + o.getIdOferta() + " | Título: " + o.getTitulo() + " | Preço: R$ " + o.getPrecoPromocional() + " | Qtd: " + o.getQuantidadeDisponivel());
                        }
                    }
                    case 7 -> {
                        System.out.println("\n--- REALIZAR PEDIDO ---");
                        System.out.print("ID do Usuário Comprador: ");
                        int idUser = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID do Estabelecimento: ");
                        int idEst = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID da Oferta Desejada: ");
                        int idOf = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantidade: ");
                        int qtd = Integer.parseInt(scanner.nextLine());
                        System.out.print("Preço Unitário (ex: 15.00): ");
                        BigDecimal pUnit = new BigDecimal(scanner.nextLine());

                        BigDecimal total = pUnit.multiply(new BigDecimal(qtd));
                        Pedido p = new Pedido(idUser, idEst, total, "CONFIRMADO");

                        ArrayList<ItemPedido> itens = new ArrayList<>();
                        itens.add(new ItemPedido(0, idOf, qtd, pUnit));

                        Pedido realizado = pedidoService.cadastrarPedido(p, itens);
                        System.out.println(">> Pedido Realizado com Sucesso! ID do Pedido: " + realizado.getIdPedido() + " | Total: R$ " + realizado.getValorTotal());
                    }
                    case 8 -> {
                        System.out.println("\n--- LISTA DE PEDIDOS ---");
                        ArrayList<Pedido> pedidos = pedidoService.consultar();
                        for (Pedido p : pedidos) {
                            System.out.println("ID Pedido: " + p.getIdPedido() + " | Cliente ID: " + p.getIdUsuario() + " | Status: " + p.getStatus() + " | Total: R$ " + p.getValorTotal());
                        }
                    }
                    case 0 -> System.out.println("\nEncerrando o sistema...");
                    default -> System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.err.println("Erro na operação: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
