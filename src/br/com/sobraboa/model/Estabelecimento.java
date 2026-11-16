package br.com.sobraboa.model;

public class Estabelecimento {
    private int idEstabelecimento;
    private int idUsuario;
    private String nome;
    private String descricao;
    private String endereco;
    private String cidade;
    private String telefone;
    private String horarioFuncionamento;

    public Estabelecimento() {}

    public Estabelecimento(int idUsuario, String nome, String descricao, String endereco, String cidade, String telefone, String horarioFuncionamento) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public int getIdEstabelecimento() { return idEstabelecimento; }
    public void setIdEstabelecimento(int idEstabelecimento) { this.idEstabelecimento = idEstabelecimento; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public void setHorarioFuncionamento(String horarioFuncionamento) { this.horarioFuncionamento = horarioFuncionamento; }
}