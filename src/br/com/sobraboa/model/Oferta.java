package br.com.sobraboa.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Oferta {
    private int idOferta;
    private int idEstabelecimento;
    private int idCategoria;
    private String titulo;
    private String descricao;
    private String tipoOferta; // 'COMUM' ou 'SACOLA_SURPRESA'
    private BigDecimal precoOriginal;
    private BigDecimal precoPromocional;
    private int quantidadeDisponivel;
    private LocalDateTime validade;
    private String horarioRetirada;
    private String imagem;
    private boolean ativa;

    public Oferta() {}

    public Oferta(int idEstabelecimento, int idCategoria, String titulo, String descricao, 
                  String tipoOferta, BigDecimal precoOriginal, BigDecimal precoPromocional, 
                  int quantidadeDisponivel, LocalDateTime validade, String horarioRetirada, 
                  String imagem, boolean ativa) {
        this.idEstabelecimento = idEstabelecimento;
        this.idCategoria = idCategoria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoOferta = tipoOferta;
        this.precoOriginal = precoOriginal;
        this.precoPromocional = precoPromocional;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.validade = validade;
        this.horarioRetirada = horarioRetirada;
        this.imagem = imagem;
        this.ativa = ativa;
    }

    public int getIdOferta() { return idOferta; }
    public void setIdOferta(int idOferta) { this.idOferta = idOferta; }

    public int getIdEstabelecimento() { return idEstabelecimento; }
    public void setIdEstabelecimento(int idEstabelecimento) { this.idEstabelecimento = idEstabelecimento; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipoOferta() { return tipoOferta; }
    public void setTipoOferta(String tipoOferta) { this.tipoOferta = tipoOferta; }

    public BigDecimal getPrecoOriginal() { return precoOriginal; }
    public void setPrecoOriginal(BigDecimal precoOriginal) { this.precoOriginal = precoOriginal; }

    public BigDecimal getPrecoPromocional() { return precoPromocional; }
    public void setPrecoPromocional(BigDecimal precoPromocional) { this.precoPromocional = precoPromocional; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    public LocalDateTime getValidade() { return validade; }
    public void setValidade(LocalDateTime validade) { this.validade = validade; }

    public String getHorarioRetirada() { return horarioRetirada; }
    public void setHorarioRetirada(String horarioRetirada) { this.horarioRetirada = horarioRetirada; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}