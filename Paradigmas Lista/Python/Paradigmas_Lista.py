from abc import ABC, abstractmethod



class Carro:
    def __init__(self, marca, modelo, ano, velocidade, motor):
        self.motor = motor
        self.marca = marca
        self.modelo = modelo
        self.ano = ano
        self.velocidade = velocidade
    
    def exibir_detalhes(self):
        print(f"Marca: {self.marca}, Modelo: {self.modelo}, Ano: {self.ano}")
        
    def Acelerar(self):
        self.velocidade += 10
        
    def Freiar(self):
        self.velocidade -= 10
        if self.velocidade < 0:
            self.velocidade = 0
        
    def exibir_velocidade(self):
        print(f"Velocidade: {self.velocidade} km/h")

class Motor:
    def __init__(self, nome, potencia):
        self.potencia = potencia
        self.nome = nome
        
    def exibir_detalhes(self):
        return f"Motor de {self.potencia} HP, Tipo: {self.tipo}"
        

carro1 = Carro("Toyota", "Corolla", 2020, 40,"v8")
carro2 = Carro("Ford", "Mustang", 2018, 0,"v6")
carro3 = Carro("Chevrolet", "Camaro", 2021, 100,"v7")

print("Teste Carros")

carro1.Freiar()
carro2.Freiar()
carro3.Acelerar()
carro1.exibir_detalhes()
carro1.exibir_velocidade()
carro2.exibir_detalhes()
carro2.exibir_velocidade()
carro3.exibir_detalhes()
carro3.exibir_velocidade()

print("")
print("")

class ContaBancaria:
    def __init__(self, saldo, titular):
        self.__saldo = saldo
        self.titular = titular
    
    def get_saldo(self):
        return self.__saldo    

        
    def depositar(self, valor):
        if valor > 0:
            self.__saldo += valor
            print(f"Depósito de R${valor:.2f} realizado com sucesso.")
        else:
            print("Valor de depósito inválido.")
    
    
    def sacar(self, valor):
        if valor > self._saldo:
            raise SaldoInsuficienteException(
                f"Saldo insuficiente! Saldo atual: R${self._saldo:.2f}, valor solicitado: R${valor:.2f}"
            )
        elif valor <= 0:
            print("Valor de saque inválido.")
        else:
            self._saldo -= valor
            print(f"Saque de R${valor:.2f} realizado com sucesso.")


class SaldoInsuficienteException(Exception):
    def __init__(self, mensagem="Saldo insuficiente para realizar o saque."):
        super().__init__(mensagem)






class Animal:
    def __init__(self, nome):
        self.nome = nome
    
    def som(self):
        raise NotImplementedError

class Cachorro(Animal):
    def som(self):
        return f"{self.nome} faz: Au au!"

class Gato(Animal):
    def som(self):
        return f"{self.nome} faz: Miau!"
    
def Bicharada(animais):
    for animal in animais:
        print(animal.som())
        
animais = [Cachorro("Pandora"), Gato("igui"), Cachorro("Sky"), Gato("logan")]        
cachorro = Cachorro("Rex")
gato = Gato("Mingau")

print("Teste Animais")

print(cachorro.som())
print(gato.som())
Bicharada(animais)

print("")
print("")

class Escola:
    def __init__(self, nome):
        self.nome = nome
        self.professores = []
        
    def adicionar_professor(self, professor):
        
        if professor not in self.professores:
            
            self.professores.append(professor)
            professor.adicionar_escola(self)
        
    def exibir_escola(self):
        
        print(f"Escola: {self.nome}")
        print("Professores:")
        
        for professor in self.professores:
            print(f"- {professor.nome}, Materia: {professor.materia}")

class Professor:
    def __init__(self, nome, materia):
        self.materia = materia
        self.nome = nome
        self.escolas = []
        
    def adicionar_escola(self, escola):
        
        if escola not in self.escolas:
            
            self.escolas.append(escola)
            escola.adicionar_professor(self)
            
    def exibir_professor(self):
        
        print(f"Professor: {self.nome}, Materia: {self.materia}")
        print("Escolas onde leciona:")
        
        for escola in self.escolas:
            print(f"- {escola.nome}")
            
escola = Escola("Unipe")
professor = Professor("Ricardo", "Paradigmas Lin. Programação")

escola.adicionar_professor(professor)

print("Teste Escola")

escola.exibir_escola()
professor.exibir_professor()

print("")
print("")

class Empresa:
    def __init__(self, nome):
        self.nome = nome
        self.empregados = []
        
    def adicionar_empregado(self, empregado):
        
        if empregado not in self.empregados:
            self.empregados.append(empregado)


    def exibir_empregados(self):
        
        print(f"Empresa: {self.nome}")
        print("Empregados:")
        
        for empregado in self.empregados:
            print(f"- {empregado.exibir_empregado()}")

class Empregado:
    def __init__(self, nome, cargo, salario):
        self.salario = salario
        self.nome = nome
        self.cargo = cargo
        
    def exibir_empregado(self):
        return f"Nome: {self.nome}, Cargo: {self.cargo}, Salário: R${self.salario:.2f}"
        
empresa = Empresa("DevTech")
empregado = Empregado("Luan alberto", "Desenvolvedor", 5500.00)

empresa.adicionar_empregado(empregado)

print("Teste Empresa")

empresa.exibir_empregados()

print("")
print("")


class Imprimivel(ABC):
    
    @abstractmethod
    def imprimir(self):
        pass
    
    
class Relatorio(Imprimivel):
    def __init__(self, titulo, conteudo):
        self.titulo = titulo
        self.conteudo = conteudo
    
    def imprimir(self):
        print(f"Relatório: {self.titulo}")
        print(f"Conteúdo: {self.conteudo}")
        
class Contrato(Imprimivel):
    def __init__(self, partes, objeto):
        self.partes = partes
        self.objeto = objeto
    
    def imprimir(self):
        print(f"Contrato entre: {self.partes}")
        print(f"Objeto do contrato: {self.objeto}")
      
      
def imprimir_documento(documento: Imprimivel):
    documento.imprimir()  
        
relatorio = Relatorio("Relatório Financeiro", "Conteúdo do relatório financeiro.")
contrato = Contrato("DevTech, Unipe", "Prestação de serviços de TI")

print("Teste Documento")

imprimir_documento(relatorio)
print()
imprimir_documento(contrato)

print()
print()


class Calculadora:
    
    def somar(self, *numeros):
        
        if len(numeros) == 2:
            return numeros[0] + numeros[1]
        elif len(numeros) == 3:
            return numeros[0] + numeros[1] + numeros[2]
        else:
            raise ValueError("O método somar aceita apenas 2 ou 3 números")
        
calc = Calculadora()

print("Teste Calculadora")

resultado_2_numeros = calc.somar(10, 23)
print(f"Soma de 2 números: {resultado_2_numeros}")

resultado_3_numeros = calc.somar(5, 12, 15)
print(f"Soma de 3 números: {resultado_3_numeros}")

print()
print()



class Funcionario(ABC):
    
    def __init__(self, nome):
        self.nome = nome
    
    @abstractmethod
    def calcularSalario(self):
        pass

class FuncionarioHorista(Funcionario):
    
    def __init__(self, nome, horas_trabalhadas, valor_hora):
        super().__init__(nome)
        
        self.horas_trabalhadas = horas_trabalhadas
        self.valor_hora = valor_hora
    
    def calcularSalario(self):
        return self.horas_trabalhadas * self.valor_hora
    
    
class FuncionarioAssalariado(Funcionario):
    
    def __init__(self, nome, salario_mensal):
        super().__init__(nome)
        
        self.salario_mensal = salario_mensal
    
    def calcularSalario(self):
        return self.salario_mensal
    
    
    
horista = FuncionarioHorista("Luan", 160, 50)
assalariado = FuncionarioAssalariado("Ricardo", 10000)

print("Teste Funcionarios")
print(f"Salário de {horista.nome}: R${horista.calcularSalario():.2f}")
print(f"Salário de {assalariado.nome}: R${assalariado.calcularSalario():.2f}")

print()
print()



class Produto:
    def __init__(self, nome, preco):
        
        self.nome = nome
        self.preco = preco

    def __add__(self, outro_produto):
        
        if isinstance(outro_produto, Produto):
            return self.preco + outro_produto.preco
    
    def __str__(self):
        return f"{self.nome}: R${self.preco:.2f}"
    
    
produto1 = Produto("Pizza Congelada", 22.50)
produto2 = Produto("Mouse Bluetooth", 150.75)

total = produto1 + produto2

print("Teste Produtos")
print(f"Soma dos preços de {produto1.nome} e {produto2.nome}: R${total:.2f}")



class Matematica:
    
    @staticmethod
    def fatorial(n):
        
        if n < 0:
            raise ValueError("Fatorial não é definido para números negativos.")
        elif n == 0 or n == 1:
            return 1
        else:
            fatorial = 1
            for i in range(2, n + 1):
                fatorial *= i
            return fatorial

numero = 5

resultado = Matematica.fatorial(numero)

print("Teste Matematica")
print(f"Fatorial de {numero} é: {resultado}")
print()
print()



class Configuracao:

    _instancia = None
    
    def __new__(cls):
        
        if cls._instancia is None:
            cls._instancia = super(Configuracao, cls).__new__(cls)
        return cls._instancia
    
    def __init__(self):
        if not hasattr(self, 'configuracoes'):

            self.configuracoes = {}
    
    def definir_configuracao(self, chave, valor):
        self.configuracoes[chave] = valor
    
    def obter_configuracao(self, chave):
        return self.configuracoes.get(chave, None)

config1 = Configuracao()
config2 = Configuracao()

print("Teste Configuração")

print(f"Config1 é config2? {config1 is config2}")

config1.definir_configuracao("API_URL", "https://api.exemplo.com")

print(f"Configuração API_URL em config2: {config2.obter_configuracao('API_URL')}")