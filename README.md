# Sobre o Projeto: 

Foram feitas as seguintes implementações:

## Implementações escolhidas:

### Total de pontos: 47.

### Sistema de combate:

1. Ataque Básico - 3 pontos.
2. Sistema de Morte - 3 pontos.
3. Alcance variável - 4 pontos.

### Controles avançados:

1. Filtro por tipo - 4 pontos.
2. Controle de montaria - 5 pontos.

### Arquitetura de software:

1. Arquivo de configurações - 3 pontos.
2. Fábrica de personagens - 6 pontos.
3. Cache de recursos - 6 pontos.

### Interface do usuário:

1. Barra de vida: 4 pontos.

### Funcionalidades do jogo: 

1. Sistema de coleta: 4 pontos.
2. Economia: 5 pontos.


## Descrição das funcionalidades e seus pontos:

### 3 pontos - Ataque básico

Adicionado ataque básico para os personagens Arqueiro e Cavaleiro. O ataque funciona com os personagens montados e desmontados, com várias funções implementadas.

---

### 3 pontos - Sistema de morte

Sistema que organiza como os personagens morrem, quando o personagem tem vida 0 ele some do sistema, e com uma contagem de baixas.

---

### 4 pontos - Alcance variável

Limite de alcance para os ataques do Arqueiro e do Guerreiro. Foram criadas implementações nas respectivas classes e na classe Tela.

---

### 4 pontos - Filtro por tipo

Adicionado a movimentação dos tipos. Foi criada uma classe chamda `Move` que representa o tipo selecionado, ao ser selecionado outro tipo, e movimentado somente o tipo selecionado;

---

### 5 pontos - Controle de montaria

Implementação centrada na interface ComMontaria e diversas melhorias feitas nas classes que usam essa interface e na classe Tela.

---

### 3 pontos – Arquivo de configurações

Foi adicionado o arquivo `joe.properties` [ver arquivo](./app/src/main/resources/joe.properties), contendo todos os valores padrão do jogo. Qualquer alteração feita nesse arquivo será refletida automaticamente em todos os pontos onde esses valores são utilizados.

Para carregar esses dados, foi criada a classe `ConfiguracaoJogo.java` [ver arquivo](./app/src/main/java/ifsc/joe/config/ConfiguracaoJogo.java). Ela é responsável por ler o arquivo de configuração e disponibilizar getters para acesso aos valores nas demais classes do projeto.

> Para mais informaões de como adicionar novos valores [ver documentação](./app/src/main/java/ifsc/joe/config/docs.md)
---

### 6 pontos - Fabrica de personagem 

Implementado a fábrica de personagens, que visa a reutilização do código, evitando repetições desnecessárias. [ver arquivo](./app/src/main/java/ifsc/joe/factory/PersonagemFactory.java).

---
### 6 Pontos - Cache de Recursos

Foi implementada uma classe com o objetivo de carregar as imagens uma única vez, para melhorar o desempenho e a performance do jogo. [ver arquivo](./app/src/main/java/ifsc/joe/resources/ImageCache.java)

A implementação foi realizada conforme as solicitações. Veja o arquivo
 da classe responsável por gerenciar o cache, e na classe abstrata foi implementado o uso do cache.

---

### 4 pontos - Barra de vida

Barra de vida criada na classe Personagem, com diversas modificações para mudar a cor de acordo com o dano.

---


### 5 pontos - Economia

Foi implementado um sistema de economia com a adição de métodos na superclasse Estoque, e com alterações também nas classes PersonagemFactory, Tela e PainelControles.

# Fluxo de implementações:


---

## Camada de Domínio (Domain Layer)

### Hierarquia de Personagens

                       Personagem (Abstract)
                     ┌─────────────────────┐
                     │ - posX:  int         │
                     │ - posY: int         │
                     │ - vida: int         │
                     │ - vidaMaxima: int   │
                     │ - velocidade: int   │
                     ├─────────────────────┤
                     │ + mover()           │
                     │ + desenhar()        │
                     │ + receberDano()     │
                     │ + estaMorto()       │
                     └─────────────────────┘
                               ▲
                ┌──────────────┼──────────────┐
                │              │              │
      ┌─────────────┐  ┌──────────────┐  ┌─────────────┐
      │   Aldeao    │  │  Arqueiro    │  │ Cavaleiro   │
      ├─────────────┤  ├──────────────┤  ├─────────────┤
      │ - montado   │  │ - flechas    │  │ - montado   │
      ├─────────────┤  ├──────────────┤  ├─────────────┤
      │ implements:  │  │ implements:   │  │ implements:  │
      │ • Coletador │  │ • Guerreiro  │  │ • Guerreiro │
      │ • Montaria  │  │ • Coletador  │  │ • Montaria  │
      └─────────────┘  └──────────────┘  └─────────────┘


### Hierarquia de Recursos

                      Recursos (Abstract)
                    ┌──────────────────────┐
                    │ - posX: int          │
                    │ - posY: int          │
                    │ - tipo: TipoRecurso  │
                    ├──────────────────────┤
                    │ + desenhar()         │
                    │ + verificaProximidade│
                    └──────────────────────┘
                              ▲
                ┌─────────────┼─────────────┐
                │             │             │
       ┌────────────┐  ┌──────────┐  ┌──────────┐
       │  Madeira   │  │   Ouro   │  │  Trigo   │
       └────────────┘  └──────────┘  └──────────┘


---

### Interfaces

```
┌───────────────────────────┐
│   <<interface>>           │
│        Coletador          │
├───────────────────────────┤
│ + podeColetarTipo(tipo)   │
│ + getRaioColeta()         │
└───────────────────────────┘

┌───────────────────────────┐
│   <<interface>>           │
│        Guerreiro          │
├───────────────────────────┤
│ + atacar()                │
│ + ataque(alvo)            │
│ + getForcaAtaque()        │
│ + defender(dano)          │
│ + getAlcanceAtaque()      │
└───────────────────────────┘

┌───────────────────────────┐
│   <<interface>>           │
│       ComMontaria         │
├───────────────────────────┤
│ + estaMontado()           │
│ + alternarMontaria()      │
│ + getVelocidadeMontado()  │
│ + getVelocidadeDesmontado()│
│ + getTipoMontaria()       │
└───────────────────────────┘
```

### Enums

```
┌─────────────────┐
│ <<enumeration>> │
│     Direcao     │
├─────────────────┤
│ CIMA            │
│ BAIXO           │
│ ESQUERDA        │
│ DIREITA         │
└─────────────────┘

┌─────────────────┐
│ <<enumeration>> │
│  TipoRecurso    │
├─────────────────┤
│ MADEIRA         │
│ OURO            │
│ TRIGO           │
└─────────────────┘

┌─────────────────┐
│ <<enumeration>> │
│ TipoPersonagem  │
├─────────────────┤
│ TODOS           │
│ ALDEAO          │
│ ARQUEIRO        │
│ CAVALEIRO       │
└─────────────────┘
```

### Classes de domínio

```
┌─────────────────────────────────────────────┐
│               Personagem                    │
│                 <<abstract>>                │
├─────────────────────────────────────────────┤
│ - posX: int                                  │
│ - posY: int                                  │
│ - vida: int                                  │
│ - vidaMaxima: int                            │
│ - velocidade: int                            │
│ - nomeImagem: String                         │
│ - imagemNormal: Image                        │
│ - imagemAtacando: Image                      │
│ - atacando: boolean                          │
├─────────────────────────────────────────────┤
│ + desenhar(g, painel)                        │
│ + mover(direcao, maxLargura, maxAltura)      │
│ + receberDano(dano): void                    │
│ + estaMorto(): boolean                       │
│ + distanciaAlvo(alvo): double                │
└─────────────────────────────────────────────┘
           ▲                   ▲                   ▲
           │                   │                   │
           │                   │                   │
┌──────────────────────┐  ┌──────────────────────┐  ┌────────────────────────┐
│       Aldeao         │  │       Arqueiro       │  │       Cavaleiro        │
├──────────────────────┤  ├──────────────────────┤  ├────────────────────────┤
│ implements Coletador │  │ implements Guerreiro │  │ implements Guerreiro   │
│ implements ComMontaria│ │ implements Coletador │  │ implements ComMontaria │
├──────────────────────┤  ├──────────────────────┤  ├────────────────────────┤
│ + podeColetarTipo()  │  │ + ataque(alvo)       │  │ + ataque(alvo)         │
│ + alternarMontaria() │  │ + getAlcanceAtaque() │  │ + getAlcanceAtaque()   │
└──────────────────────┘  │ + coletarMadeira()   │  │                        │
                          └──────────────────────┘  └────────────────────────┘
```

### Recursos do mapa

```
┌──────────────────────────────┐
│           Recursos            │
│           <<abstract>>        │
├──────────────────────────────┤
│ - posX: int                   │
│ - posY: int                   │
│ - nomeImagem: String          │
│ - icone: Image                │
│ - tipo: TipoRecurso           │
├──────────────────────────────┤
│ + desenhar(g, painel)         │
│ + verificaProximidade(x,y,r)  │
└──────────────────────────────┘
       ▲           ▲           ▲
       │           │           │
┌─────────────┐ ┌───────────┐ ┌──────────┐
│   Madeira   │ │   Ouro    │ │  Trigo   │
└─────────────┘ └───────────┘ └──────────┘
```

### Fábrica e Configuração

```
┌────────────────────────────────────────┐      uses       ┌──────────────────────────────┐
│           PersonagemFactory            │ ─────────────→  │       ConfiguracaoJogo       │
├────────────────────────────────────────┤                 │         <<Singleton>>         │
│ + criar(tipo, x, y): Personagem        │                 ├──────────────────────────────┤
│ + criarAleatorio(x, y): Personagem     │                 │ - propriedades: Properties    │
│ + criarComCusto(tipo,x,y,estoque)      │                 ├──────────────────────────────┤
│ + podeSerCriado(tipo): boolean         │                 │ + getInstancia()              │
└────────────────────────────────────────┘                 │ + getters de valores          │
                                                          │ + recarregar()                │
                                                          └──────────────────────────────┘
```

### Cache de Imagens

```
┌──────────────────────────────┐
│          ImageCache          │
│          <<Singleton>>       │
├──────────────────────────────┤
│ - cache: Map<String, Image>  │
│ - tentativasCarregamento: int│
│ - hitCache: int              │
├──────────────────────────────┤
│ + getInstancia()             │
│ + get(nome): Image           │
│ + preCarregarImagens(...)    │
│ + exibirEstatisticasCache()  │
│ + getTamanhoCache()          │
│ + getTaxaHitCache(): double  │
└──────────────────────────────┘
```

### UI

```
┌──────────────────────────────┐      contains      ┌────────────────────────┐
│          JanelaJogo          │ ────────────────→ │     PainelControles     │
├──────────────────────────────┤                    ├────────────────────────┤
│ + exibir()                   │                    │ + getPainelPrincipal() │
│ - frame: JFrame              │                    │ + listeners de botões  │
└──────────────────────────────┘                    └────────────────────────┘
                                        contains      │
                                                     ▼
                                           ┌─────────────────┐
                                           │      Tela       │
                                           ├─────────────────┤
                                           │ - personagens   │
                                           │ - recursos      │
                                           │ - estoque       │
                                           ├─────────────────┤
                                           │ + paintComponent│
                                           │ + combate()     │
                                           │ + coletarRecursos()│
                                           │ + movimentarPersonagens()│
                                           └─────────────────┘
```

Observações:
- Setas “implements” indicam implementação de interfaces nas classes concretas.
- “uses” indica dependência de configuração pela fábrica.
- A UI orquestra eventos e delega para a lógica da Tela.






