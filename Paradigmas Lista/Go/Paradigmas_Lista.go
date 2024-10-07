package main

import (
	"fmt"
	"sync"
)

// 14. Singleton (Configuracao)
type Configuracao struct {
	dados map[string]string
}

var instance *Configuracao
var once sync.Once

func GetConfiguracao() *Configuracao {
	once.Do(func() {
		instance = &Configuracao{dados: make(map[string]string)}
	})
	return instance
}

func (c *Configuracao) DefinirConfiguracao(chave, valor string) {
	c.dados[chave] = valor
}

func (c *Configuracao) ObterConfiguracao(chave string) string {
	return c.dados[chave]
}

// 1. Carro com Motor (Composição)
type Motor struct {
	Potencia int
}

type Carro struct {
	Marca      string
	Modelo     string
	Ano        int
	Motor      Motor
	Velocidade int
}

func (c *Carro) ExibirDetalhes() {
	fmt.Printf("Carro: %s %s, Ano: %d, Potência do Motor: %d CV\n", c.Marca, c.Modelo, c.Ano, c.Motor.Potencia)
}

func (c *Carro) Acelerar(aumento int) {
	c.Velocidade += aumento
}

func (c *Carro) Frear(reducao int) {
	c.Velocidade -= reducao
}

func (c *Carro) ExibirVelocidade() {
	fmt.Printf("Velocidade atual do %s: %d km/h\n", c.Modelo, c.Velocidade)
}

// 3. Conta Bancaria
type ContaBancaria struct {
	titular string
	saldo   float64
}

func (cb *ContaBancaria) Depositar(valor float64) {
	cb.saldo += valor
}

func (cb *ContaBancaria) Sacar(valor float64) error {
	if valor > cb.saldo {
		return &SaldoInsuficienteError{Saldo: cb.saldo}
	}
	cb.saldo -= valor
	return nil
}

func (cb ContaBancaria) ExibirSaldo() float64 {
	return cb.saldo
}

func (cb ContaBancaria) GetTitular() string {
	return cb.titular
}

// 15. Exceção personalizada (SaldoInsuficiente)
type SaldoInsuficienteError struct {
	Saldo float64
}

func (e *SaldoInsuficienteError) Error() string {
	return fmt.Sprintf("Saldo insuficiente. Saldo disponível: R$%.2f", e.Saldo)
}

// 4. Polimorfismo e Herança
type Animal interface {
	Som() string
}

type Cachorro struct {
	Nome string
}

func (c Cachorro) Som() string {
	return c.Nome + " faz: Au au!"
}

type Gato struct {
	Nome string
}

func (g Gato) Som() string {
	return g.Nome + " faz: Miau!"
}

func EmitirSons(animais []Animal) {
	for _, animal := range animais {
		fmt.Println(animal.Som())
	}
}

// 7. Associação (Escola e Professor)
type Professor struct {
	Nome    string
	Escolas []*Escola
}

type Escola struct {
	Nome        string
	Professores []*Professor
}

func (e *Escola) AdicionarProfessor(p *Professor) {
	e.Professores = append(e.Professores, p)
	p.Escolas = append(p.Escolas, e)
}

func (e *Escola) ExibirProfessores() {
	fmt.Printf("Escola %s tem os seguintes professores:\n", e.Nome)
	for _, professor := range e.Professores {
		fmt.Println(professor.Nome)
	}
}

func (p *Professor) ExibirEscolas() {
	fmt.Printf("O professor %s ensina nas seguintes escolas:\n", p.Nome)
	for _, escola := range p.Escolas {
		fmt.Println(escola.Nome)
	}
}

// 8. Agregação (Empresa e Empregados)
type Empregado struct {
	Nome    string
	Cargo   string
	Salario float64
}

type Empresa struct {
	Nome       string
	Empregados []Empregado
}

func (e *Empresa) AdicionarEmpregado(emp Empregado) {
	e.Empregados = append(e.Empregados, emp)
}

func (e *Empresa) ExibirEmpregados() {
	fmt.Printf("Empregados da empresa %s:\n", e.Nome)
	for _, empregado := range e.Empregados {
		fmt.Printf("Nome: %s, Cargo: %s, Salário: R$%.2f\n", empregado.Nome, empregado.Cargo, empregado.Salario)
	}
}

// 9. Interface Imprimível
type Imprimivel interface {
	Imprimir()
}

type Relatorio struct {
	Titulo   string
	Conteudo string
}

func (r Relatorio) Imprimir() {
	fmt.Printf("Relatório: %s\nConteúdo: %s\n", r.Titulo, r.Conteudo)
}

type Contrato struct {
	Termos string
}

func (c Contrato) Imprimir() {
	fmt.Printf("Contrato: %s\n", c.Termos)
}

func imprimirDocumento(doc Imprimivel) {
	doc.Imprimir()
}

// Main para execução
func main() {
	// Singleton
	config1 := GetConfiguracao()
	config2 := GetConfiguracao()
	fmt.Println("Config1 é config2?", config1 == config2)
	config1.DefinirConfiguracao("API_URL", "https://api.exemplo.com")
	fmt.Println("Configuração API_URL em config2:", config2.ObterConfiguracao("API_URL"))

	// Carro e Motor
	fmt.Println("\n Teste do Carro ")
	motor := Motor{Potencia: 120}
	carro := Carro{Marca: "Toyota", Modelo: "Corolla", Ano: 2020, Motor: motor}
	carro.ExibirDetalhes()
	carro.Acelerar(50)
	carro.ExibirVelocidade()
	carro.Frear(20)
	carro.ExibirVelocidade()

	// Conta Bancaria
	fmt.Println("\n Teste da Conta Bancária ")
	conta := ContaBancaria{titular: "João", saldo: 1000}
	fmt.Println("Titular:", conta.GetTitular())
	fmt.Println("Saldo inicial: R$", conta.ExibirSaldo())
	conta.Depositar(500)
	fmt.Println("Saldo após depósito: R$", conta.ExibirSaldo())
	err := conta.Sacar(300)
	if err != nil {
		fmt.Println("Erro:", err)
	} else {
		fmt.Println("Saldo após saque: R$", conta.ExibirSaldo())
	}
	err = conta.Sacar(1500)
	if err != nil {
		fmt.Println("Erro:", err)
	} else {
		fmt.Println("Saldo final: R$", conta.ExibirSaldo())
	}

	// Polimorfismo com animais
	fmt.Println("\n Sons dos Animais ")
	cachorro1 := Cachorro{Nome: "Pandora"}
	gato1 := Gato{Nome: "Mingau"}
	animais := []Animal{cachorro1, gato1}
	EmitirSons(animais)

	// Associação Escola e Professor
	fmt.Println("\n Associação Escola e Professor ")
	professor1 := Professor{Nome: "Ricardo"}
	professor2 := Professor{Nome: "Leonardo"}
	escola1 := Escola{Nome: "Unipe"}
	escola2 := Escola{Nome: "Uniesp"}
	escola1.AdicionarProfessor(&professor1)
	escola1.AdicionarProfessor(&professor2)
	escola2.AdicionarProfessor(&professor2)
	escola1.ExibirProfessores()
	escola2.ExibirProfessores()
	professor1.ExibirEscolas()
	professor2.ExibirEscolas()

	// Empresa e Empregados
	fmt.Println("\n Teste da Empresa ")
	empresa := Empresa{Nome: "Tech Solutions"}
	emp1 := Empregado{Nome: "Lucas", Cargo: "Desenvolvedor", Salario: 5000.0}
	emp2 := Empregado{Nome: "Mariana", Cargo: "Gerente de Projetos", Salario: 7000.0}
	empresa.AdicionarEmpregado(emp1)
	empresa.AdicionarEmpregado(emp2)
	empresa.ExibirEmpregados()

	// Interface Imprimível
	fmt.Println("\n Imprimindo Relatório e Contrato ")
	relatorio := Relatorio{Titulo: "Relatório Mensal", Conteudo: "Conteúdo do relatório..."}
	contrato := Contrato{Termos: "Cláusulas do contrato..."}
	imprimirDocumento(relatorio)
	imprimirDocumento(contrato)
}
