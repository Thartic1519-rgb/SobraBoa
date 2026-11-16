package br.com.sobraboa.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {
    private int idPedido;
    private int idUsuario;
    private int idEstabelecimento;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private String status; // 'AGUARDANDO_PAGAMENTO', 'CONFIRMADO', 'PRONTO_PARA_RETIRADA', 'CONCLUIDO', 'CANCELADO'

    public Pedido() {}

    public Pedido(int idUsuario, int idEstabelecimento, BigDecimal valorTotal, String status) {
        this.idUsuario = idUsuario;
        this.idEstabelecimento = idEstabelecimento;
        this.dataPedido = LocalDateTime.now();
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdEstabelecimento() { return idEstabelecimento; }
    public void setIdEstabelecimento(int idEstabelecimento) { this.idEstabelecimento = idEstabelecimento; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}