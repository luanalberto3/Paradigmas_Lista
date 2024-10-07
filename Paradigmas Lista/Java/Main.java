import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe Motor
class Motor {
    private int potencia;


    public Motor(int potencia) {
        this.potencia = potencia;

    }

    public int getPotencia() {
        return potencia;
    }

}

// Classe Carro
class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private Motor motor;
    private int velocidade;

    public Carro(String marca, String modelo, int ano, Motor motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.motor = motor;
        this.velocidade = 0;
    }

    public void acelerar(int incremento) {
        this.velocidade += incremento;
    }

    public void frear(int decremento) {
        this.velocidade -= decremento;
        if (this.velocidade < 0) {
            this.velocidade = 0;
        }
    }

    public void exibirVelocidade() {
        System.out.println("Velocidade atual: " + this.velocidade + " km/h");
    }

    public void exibirDetalhes() {
        System.out.println("Carro: " + marca + " " + modelo + ", Ano: " + ano);
        System.out.println("Motor: " + motor.getPotencia());
    }
}


class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}

// Classe ContaBancaria
class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        } else {
            saldo -= valor;
        }
    }
}

// Classe Animal
abstract class Animal {
    protected String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    public abstract String som();
}

// Classe Cachorro
class Cachorro extends Animal {
    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    public String som() {
        return nome + " faz: Au au!";
    }
}

// Classe Gato
class Gato extends Animal {
    public Gato(String nome) {
        super(nome);
    }

    @Override
    public String som() {
        return nome + " faz: Miau!";
    }
}


class Bicharada {
    public static void emitirSons(List<Animal> animais) {
        for (Animal animal : animais) {
            System.out.println(animal.som());
        }
    }
}

// Classe Professor
class Professor {
    private String nome;
    private List<Escola> escolas;

    public Professor(String nome) {
        this.nome = nome;
        this.escolas = new ArrayList<>();
    }

    public void adicionarEscola(Escola escola) {
        if (!escolas.contains(escola)) {
            escolas.add(escola);
        }
    }

    public void exibirEscolas() {
        System.out.println("O professor " + nome + " leciona nas seguintes escolas:");
        for (Escola escola : escolas) {
            System.out.println("- " + escola.getNome());
        }
    }

    public String getNome() {
        return nome;
    }
}

// Classe Escola
class Escola {
    private String nome;
    private List<Professor> professores;

    public Escola(String nome) {
        this.nome = nome;
        this.professores = new ArrayList<>();
    }

    public void adicionarProfessor(Professor professor) {
        if (!professores.contains(professor)) {
            professores.add(professor);
            professor.adicionarEscola(this);
        }
    }

    public void exibirProfessores() {
        System.out.println("A escola " + nome + " tem os seguintes professores:");
        for (Professor professor : professores) {
            System.out.println("- " + professor.getNome());
        }
    }

    public String getNome() {
        return nome;
    }
}

// Classe Empregado
class Empregado {
    private String nome;
    private String cargo;
    private double salario;

    public Empregado(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public void exibirDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salário: R$ " + salario);
        System.out.println("--------------------");
    }

    public String getNome() {
        return nome;
    }
}

// Classe Empresa
class Empresa {
    private String nome;
    private List<Empregado> empregados;

    public Empresa(String nome) {
        this.nome = nome;
        this.empregados = new ArrayList<>();
    }

    public void adicionarEmpregado(Empregado empregado) {
        empregados.add(empregado);
    }

    public void exibirEmpregados() {
        System.out.println("A empresa " + nome + " tem os seguintes empregados:");
        for (Empregado empregado : empregados) {
            empregado.exibirDetalhes();
        }
    }
}

// Interface Imprimivel
interface Imprimivel {
    void imprimir();
}


class Relatorio implements Imprimivel {
    private String titulo;
    private String conteudo;

    public Relatorio(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    @Override
    public void imprimir() {
        System.out.println(" " + titulo + " ");
        System.out.println(conteudo);
    }
}


class Contrato implements Imprimivel {
    private String titulo;
    private String clausulas;

    public Contrato(String titulo, String clausulas) {
        this.titulo = titulo;
        this.clausulas = clausulas;
    }

    @Override
    public void imprimir() {
        System.out.println(" " + titulo + " ");
        System.out.println(clausulas);
    }
}

// Classe Abstrata Funcionario
abstract class Funcionario {
    protected String nome;

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularSalario();
}

// Classe FuncionarioHorista
class FuncionarioHorista extends Funcionario {
    private double valorPorHora;
    private int horasTrabalhadas;

    public FuncionarioHorista(String nome, double valorPorHora, int horasTrabalhadas) {
        super(nome);
        this.valorPorHora = valorPorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double calcularSalario() {
        return valorPorHora * horasTrabalhadas;
    }
}

// Classe FuncionarioAssalariado
class FuncionarioAssalariado extends Funcionario {
    private double salario;

    public FuncionarioAssalariado(String nome, double salario) {
        super(nome);
        this.salario = salario;
    }

    @Override
    public double calcularSalario() {
        return salario;
    }
}

// Classe Calculadora
class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public int somar(int a, int b, int c) {
        return a + b + c;
    }
}

// Classe Produto
class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public Produto somar(Produto outro) {
        double novoPreco = this.preco + outro.preco;
        return new Produto(this.nome + " & " + outro.nome, novoPreco);
    }

    @Override
    public String toString() {
        return "Produto: " + nome + ", Preço: R$ " + preco;
    }
}

// Classe Configuracao (Singleton)
class Configuracao {
    private static Configuracao instancia = null;
    private Map<String, String> configuracoes;

    private Configuracao() {
        configuracoes = new HashMap<>();
    }

    public static Configuracao getInstancia() {
        if (instancia == null) {
            instancia = new Configuracao();
        }
        return instancia;
    }

    public void definirConfiguracao(String chave, String valor) {
        configuracoes.put(chave, valor);
    }

    public String obterConfiguracao(String chave) {
        return configuracoes.getOrDefault(chave, null);
    }
}

// Classe Matematica
class Matematica {
    public static long calcularFatorial(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número deve ser não-negativo.");
        }
        long fatorial = 1;
        for (int i = 1; i <= numero; i++) {
            fatorial *= i;
        }
        return fatorial;
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {
        //Configuracao (Singleton)
        Configuracao config1 = Configuracao.getInstancia();
        Configuracao config2 = Configuracao.getInstancia();

        System.out.println("Config1 é config2? " + (config1 == config2));

        config1.definirConfiguracao("API_URL", "https://api.exemplo.com");
        System.out.println("Configuração API_URL em config2: " + config2.obterConfiguracao("API_URL"));

        //Motor e Carro
        System.out.println("\n Teste do Carro ");
        Motor motor = new Motor(120);
        Carro carro = new Carro("Toyota", "Corolla", 2020, motor);
        carro.exibirDetalhes();
        carro.acelerar(50);
        carro.exibirVelocidade();
        carro.frear(20);
        carro.exibirVelocidade();

        //ContaBancaria
        System.out.println("\n Teste da Conta Bancária ");
        ContaBancaria conta = new ContaBancaria("João", 1000);
        System.out.println("Titular: " + conta.getTitular());
        System.out.println("Saldo inicial: R$ " + conta.getSaldo());

        conta.depositar(500);
        System.out.println("Saldo após depósito: R$ " + conta.getSaldo());

        try {
            conta.sacar(300);
            System.out.println("Saldo após saque: R$ " + conta.getSaldo());

            conta.sacar(1500);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Saldo final: R$ " + conta.getSaldo());

        //polimorfismo com animais
        System.out.println("\n Sons dos Animais ");
        List<Animal> animais = new ArrayList<>();
        animais.add(new Cachorro("Pandora"));
        animais.add(new Gato("Igui"));
        animais.add(new Cachorro("Sky"));
        animais.add(new Gato("Logan"));

        Cachorro cachorro = new Cachorro("Rex");
        Gato gato = new Gato("Mingau");

        System.out.println(cachorro.som());
        System.out.println(gato.som());
        Bicharada.emitirSons(animais);

        // Testando associação entre Escola e Professor
        System.out.println("\n Associação Escola e Professor ");
        Professor professor1 = new Professor("Ricardo");
        Professor professor2 = new Professor("Leonardo");

        Escola escola1 = new Escola("unipe");
        Escola escola2 = new Escola("uniesp");

        escola1.adicionarProfessor(professor1);
        escola1.adicionarProfessor(professor2);

        escola2.adicionarProfessor(professor2);

        escola1.exibirProfessores();
        escola2.exibirProfessores();

        professor1.exibirEscolas();
        professor2.exibirEscolas();

        //Empresa e Empregados
        System.out.println("\n Teste da Empresa ");
        Empresa empresa = new Empresa("Tech Solutions");

        Empregado emp1 = new Empregado("Lucan", "Desenvolvedor", 5000.0);
        Empregado emp2 = new Empregado("Mariana", "Gerente de Projetos", 7000.0);
    

        empresa.adicionarEmpregado(emp1);
        empresa.adicionarEmpregado(emp2);
  

        empresa.exibirEmpregados();

        //interface Imprimivel
        System.out.println("\n Imprimindo Relatório e Contrato ");
        Relatorio relatorio = new Relatorio("Relatório Mensal", "Conteúdo do relatório...");
        Contrato contrato = new Contrato("Contrato de Prestação de Serviço", "Cláusulas do contrato...");

        imprimirDocumento(relatorio);
        imprimirDocumento(contrato);

        //classe Calculadora
        System.out.println("\n Cálculos com a Calculadora ");
        Calculadora calc = new Calculadora();

        System.out.println("Soma de 2 números: " + calc.somar(5, 10));
        System.out.println("Soma de 3 números: " + calc.somar(5, 10, 15));

        // Testando Funcionarios
        System.out.println("\n Cálculo de Salário ");
        FuncionarioHorista funcionarioHorista = new FuncionarioHorista("Luan", 50.0, 160);
        FuncionarioAssalariado funcionarioAssalariado = new FuncionarioAssalariado("Ricardo", 15000.0);

        System.out.println(funcionarioHorista.getNome() + " - Salário: R$ " + funcionarioHorista.calcularSalario());
        System.out.println(funcionarioAssalariado.getNome() + " - Salário: R$ " + funcionarioAssalariado.calcularSalario());

        // Testando a classe Produto
        System.out.println("\n Testando a Classe Produto ");
        Produto produto1 = new Produto("Produto A", 50.0);
        Produto produto2 = new Produto("Produto B", 30.0);

        System.out.println(produto1);
        System.out.println(produto2);

        Produto produtoSomado = produto1.somar(produto2);
        System.out.println("Produto combinado: " + produtoSomado);

        // Testando o método estático calcularFatorial
        System.out.println("\n Cálculo de Fatorial ");
        int numeroFatorial = 5;
        long fatorial = Matematica.calcularFatorial(numeroFatorial);
        System.out.println("Fatorial de " + numeroFatorial + " é: " + fatorial);
    }

    //imprimir documentos
    public static void imprimirDocumento(Imprimivel documento) {
        documento.imprimir();
    }
}
