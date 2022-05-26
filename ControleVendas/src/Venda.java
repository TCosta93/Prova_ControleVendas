import java.time.LocalDate;

public class Venda implements Comparable<Venda> {
    private LocalDate dataVenda;
    private String data;
    private int codigo;
    private String nome;
    private double valor;
    private double valorTotal;
    private int quantidade;

    public Venda(){

    }

    public Venda(LocalDate dataVenda, String data, int codigo, String nome, double valor, double valorTotal,
            int quantidade) {
        this.dataVenda = dataVenda;
        this.data = data;
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }




    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getCodigo() {
        return codigo;
    
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    @Override
    public String toString() {
    return 
    
    
    "[codigo do Produto :"+ codigo + ", data Venda: " + data + ", nome Produto: " + nome
    + ", quantidade produto: " + quantidade + ", valor Unitario: " + valor + ", valorTotal: " + valorTotal + "]";
    }

    @Override
    public int compareTo(Venda o) {
        return 0;
    }
     
}
