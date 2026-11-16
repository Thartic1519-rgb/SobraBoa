package sobraboa;

import java.util.ArrayList;
import java.util.Scanner;

public class SobraBoa {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Estabelecimento> estabelecimentos = new ArrayList<>();
    static ArrayList<Oferta> ofertas = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();
    static ArrayList<String> favoritos = new ArrayList<>();
    static int idUsuario = 1, idEstabelecimento = 1, idOferta = 1, idPedido = 1;

    public static void main(String[] args) {
        carregarExemplos();
        int opcao;
        do {
            System.out.println("\\n===== SOBRABOA =====");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Listar usuarios");
            System.out.println("3 - Cadastrar estabelecimento");
            System.out.println("4 - Listar estabelecimentos");
            System.out.println("5 - Cadastrar oferta");
            System.out.println("6 - Listar ofertas");
            System.out.println("7 - Pesquisar ofertas");
            System.out.println("8 - Favoritar oferta");
            System.out.println("9 - Realizar pedido");
            System.out.println("10 - Consultar pedidos");
            System.out.println("11 - Avaliar estabelecimento");
            System.out.println("0 - Sair");
            opcao = inteiro("Opcao: ");
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    cadastrarEstabelecimento();
                    break;
                case 4:
                    listarEstabelecimentos();
                    break;
                case 5:
                    cadastrarOferta();
                    break;
                case 6:
                    listarOfertas();
                    break;
                case 7:
                    pesquisarOfertas();
                    break;
                case 8:
                    favoritar();
                    break;
                case 9:
                    comprar();
                    break;
                case 10:
                    consultarPedidos();
                    break;
                case 11:
                    avaliar();
                    break;
                case 0:
                    System.out.println("Obrigado por usar o SobraBoa!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
        sc.close();
    }

    static void carregarExemplos() {
        Estabelecimento e1 = new Estabelecimento(idEstabelecimento++, "Padaria Pao & Cia", "Rua das Flores, 100");
        Estabelecimento e2 = new Estabelecimento(idEstabelecimento++, "Mercado Boa Compra", "Av. Central, 250");
        estabelecimentos.add(e1);
        estabelecimentos.add(e2);
        ofertas.add(new Oferta(idOferta++, e1.id, "Sacola surpresa de paes", "Paes do dia",
                "Sacola surpresa", 25, 12, 5));
        ofertas.add(new Oferta(idOferta++, e2.id, "Frutas selecionadas", "Frutas maduras",
                "Oferta comum", 18, 9, 8));
    }

    static void cadastrarUsuario() {
        String nome = texto("Nome: "), email = texto("E-mail: ");
        if (nome.isEmpty() || email.isEmpty() || usuarioEmail(email) != null) {
            System.out.println("Dados invalidos ou e-mail ja cadastrado.");
            return;
        }
        usuarios.add(new Usuario(idUsuario++, nome, email));
        System.out.println("Usuario cadastrado!");
    }

    static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
        }
        for (Usuario u : usuarios) {
            System.out.println(u.id + " - " + u.nome + " - " + u.email);
        }
    }

    static void cadastrarEstabelecimento() {
        String nome = texto("Nome do estabelecimento: ");
        String endereco = texto("Endereco: ");
        if (nome.isEmpty() || endereco.isEmpty()) {
            System.out.println("Preencha os campos.");
            return;
        }
        estabelecimentos.add(new Estabelecimento(idEstabelecimento++, nome, endereco));
        System.out.println("Estabelecimento cadastrado!");
    }

    static void listarEstabelecimentos() {
        if (estabelecimentos.isEmpty()) {
            System.out.println("Nenhum estabelecimento.");
        }
        for (Estabelecimento e : estabelecimentos) {
            System.out.println(e.id + " - " + e.nome + " - " + e.endereco);
        }
    }

    static void cadastrarOferta() {
        listarEstabelecimentos();
        int id = inteiro("ID do estabelecimento: ");
        Estabelecimento e = estabelecimento(id);
        if (e == null) {
            System.out.println("Estabelecimento nao encontrado.");
            return;
        }
        String nome = texto("Titulo da oferta: ");
        String descricao = texto("Descricao: ");
        String tipo = texto("Tipo (Oferta comum/Sacola surpresa): ");
        double original = decimal("Preco original: ");
        double promocional = decimal("Preco promocional: ");
        int qtd = inteiro("Quantidade disponivel: ");
        if (nome.isEmpty() || original <= 0 || promocional < 0 || promocional > original || qtd <= 0) {
            System.out.println("Dados ou valores invalidos.");
            return;
        }
        ofertas.add(new Oferta(idOferta++, e.id, nome, descricao, tipo, original, promocional, qtd));
        System.out.println("Oferta cadastrada!");
    }

    static void listarOfertas() {
        boolean achou = false;
        for (Oferta o : ofertas) {
            if (o.ativa && o.estoque > 0) {
                mostrarOferta(o);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma oferta disponivel.");
        }
    }

    static void pesquisarOfertas() {
        String termo = texto("Pesquisar pelo nome: ").toLowerCase();
        boolean achou = false;
        for (Oferta o : ofertas) {
            if (o.ativa && o.estoque > 0 && o.nome.toLowerCase().contains(termo)) {
                mostrarOferta(o);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Oferta nao encontrada.");
        }
    }

    static void mostrarOferta(Oferta o) {
        Estabelecimento e = estabelecimento(o.idEstabelecimento);
        System.out.printf("ID %d | %s (%s) | De R$ %.2f por R$ %.2f | Estoque: %d | %s%n",
                o.id, o.nome, o.tipo, o.original, o.promocional, o.estoque,
                e == null ? "" : e.nome);
    }

    static void favoritar() {
        listarOfertas();
        Oferta o = oferta(inteiro("ID da oferta: "));
        Usuario u = usuarioEmail(texto("Seu e-mail: "));
        if (o == null || u == null) {
            System.out.println("Oferta ou usuario nao encontrado.");
            return;
        }
        String chave = u.id + "-" + o.id;
        if (favoritos.contains(chave)) {
            System.out.println("Oferta ja favoritada.");
        } else {
            favoritos.add(chave);
            System.out.println("Adicionada aos favoritos!");
        }
    }

    static void comprar() {
        listarOfertas();
        Oferta o = oferta(inteiro("ID da oferta: "));
        if (o == null || !o.ativa || o.estoque <= 0) {
            System.out.println("Oferta indisponivel.");
            return;
        }
        Usuario u = usuarioEmail(texto("E-mail do comprador: "));
        if (u == null) {
            System.out.println("Usuario nao cadastrado.");
            return;
        }
        int qtd = inteiro("Quantidade: ");
        if (qtd <= 0 || qtd > o.estoque) {
            System.out.println("Quantidade invalida ou estoque insuficiente.");
            return;
        }
        double total = qtd * o.promocional;
        System.out.printf("Total: R$ %.2f%n", total);
        String pagamento = texto("Pagamento (Pix/Cartao - simulacao): ");
        if (!texto("Confirmar? (S/N): ").equalsIgnoreCase("S")) {
            System.out.println("Pedido cancelado.");
            return;
        }
        o.estoque -= qtd;
        pedidos.add(new Pedido(idPedido++, u.id, o.id, qtd, total, pagamento));
        System.out.println("Pedido confirmado! Retirada em: " + estabelecimento(o.idEstabelecimento).nome);
        System.out.println("Pagamento apenas simulado.");
    }

    static void consultarPedidos() {
        Usuario u = usuarioEmail(texto("E-mail do comprador: "));
        if (u == null) {
            System.out.println("Usuario nao encontrado.");
            return;
        }
        boolean achou = false;
        for (Pedido p : pedidos) {
            if (p.idUsuario == u.id) {
                Oferta o = oferta(p.idOferta);
                System.out.printf("Pedido #%d | %s | Qtd: %d | Total: R$ %.2f | %s%n",
                        p.id, o == null ? "Oferta" : o.nome, p.quantidade, p.total, p.pagamento);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum pedido para esse usuario.");
        }
    }

    static void avaliar() {
        if (pedidos.isEmpty()) {
            System.out.println("Realize um pedido antes de avaliar.");
            return;
        }
        consultarPedidos();
        int id = inteiro("Numero do pedido: ");
        Pedido p = pedido(id);
        if (p == null) {
            System.out.println("Pedido nao encontrado.");
            return;
        }
        int nota = inteiro("Nota de 1 a 5: ");
        if (nota < 1 || nota > 5) {
            System.out.println("Nota invalida.");
            return;
        }
        String comentario = texto("Comentario: ");
        p.avaliacao = "Nota " + nota + " - " + comentario;
        System.out.println("Avaliacao registrada!");
    }

    static Usuario usuarioEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.email.equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    static Estabelecimento estabelecimento(int id) {
        for (Estabelecimento e : estabelecimentos) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    static Oferta oferta(int id) {
        for (Oferta o : ofertas) {
            if (o.id == id) {
                return o;
            }
        }
        return null;
    }

    static Pedido pedido(int id) {
        for (Pedido p : pedidos) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    static String texto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static int inteiro(String msg) {
        while (true) {
            try {
                return Integer.parseInt(texto(msg));
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

    static double decimal(String msg) {
        while (true) {
            try {
                return Double.parseDouble(texto(msg).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numerico.");
            }
        }
    }

    static class Usuario {

        int id;
        String nome, email;

        Usuario(int id, String nome, String email) {
            this.id = id;
            this.nome = nome;
            this.email = email;
        }
    }

    static class Estabelecimento {

        int id;
        String nome, endereco;

        Estabelecimento(int id, String nome, String endereco) {
            this.id = id;
            this.nome = nome;
            this.endereco = endereco;
        }
    }

    static class Oferta {

        int id, idEstabelecimento, estoque;
        String nome, descricao, tipo;
        double original, promocional;
        boolean ativa = true;

        Oferta(int id, int est, String nome, String desc, String tipo, double orig, double promo, int qtd) {
            this.id = id;
            idEstabelecimento = est;
            this.nome = nome;
            descricao = desc;
            this.tipo = tipo;
            original = orig;
            promocional = promo;
            estoque = qtd;
        }
    }

    static class Pedido {

        int id, idUsuario, idOferta, quantidade;
        double total;
        String pagamento, avaliacao = "";

        Pedido(int id, int usuario, int oferta, int qtd, double total, String pagamento) {
            this.id = id;
            idUsuario = usuario;
            idOferta = oferta;
            quantidade = qtd;
            this.total = total;
            this.pagamento = pagamento;
        }
    }
}
