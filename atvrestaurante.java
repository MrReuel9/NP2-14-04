import java.util.ArrayList;
import java.util.List;


// Questão 1: Superclasse abstrata

abstract class ItemCardapio {
    protected String nome;
    protected double preco;

    public ItemCardapio(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public abstract String preparar();

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}


// Questão 2 Subclasses de ItemCardapio

class PratoPrincipal extends ItemCardapio {
    public PratoPrincipal(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public String preparar() {
        return "Preparando o prato principal: " + nome;
    }
}

class Sobremesa extends ItemCardapio {
    public Sobremesa(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public String preparar() {
        return "Preparando a sobremesa: " + nome;
    }
}


// Questão 4: Novo tipo de item - Bebida

class Bebida extends ItemCardapio {
    public Bebida(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public String preparar() {
        return "Servindo a bebida: " + nome;
    }
}


// Questão 3: Classe Pedido

class Pedido {
    private List<ItemCardapio> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public void mostrarResumo() {
        System.out.println("Resumo do Pedido:");
        for (ItemCardapio item : itens) {
            System.out.printf("- %s (R$ %.2f)\n", item.getNome(), item.getPreco());
            System.out.println("  -> " + item.preparar());
        }
    }
}


// Interface para o padrão de criação

interface CriadorDeItem {
    ItemCardapio criarItem();
}


// Fábricas para os tipos de itens

class CriadorDePratoPrincipal implements CriadorDeItem {
    @Override
    public ItemCardapio criarItem() {
        return new PratoPrincipal("Lasanha", 25.0);
    }
}

class CriadorDeSobremesa implements CriadorDeItem {
    @Override
    public ItemCardapio criarItem() {
        return new Sobremesa("Pudim", 10.0);
    }
}

class CriadorDeBebida implements CriadorDeItem {
    @Override
    public ItemCardapio criarItem() {
        return new Bebida("Suco de Laranja", 8.0);
    }
}

//executando o codigo-
public class Main {
    public static void main(String[] args) {
        // Criando as fábricas (padrão Factory Method)
        CriadorDeItem fabricaPrato = new CriadorDePratoPrincipal();
        CriadorDeItem fabricaSobremesa = new CriadorDeSobremesa();
        CriadorDeItem fabricaBebida = new CriadorDeBebida();

        // Criando o pedido
        Pedido pedido = new Pedido();

        // Adicionando itens ao pedido
        pedido.adicionarItem(fabricaPrato.criarItem());
        pedido.adicionarItem(fabricaSobremesa.criarItem());

        // Mostrando o resumo
        pedido.mostrarResumo();

        System.out.println("\nAdicionando uma bebida...\n");

        // Adicionando uma bebida ao pedido
        pedido.adicionarItem(fabricaBebida.criarItem());

        // Mostrando resumo atualizado
        pedido.mostrarResumo();
    }
}
