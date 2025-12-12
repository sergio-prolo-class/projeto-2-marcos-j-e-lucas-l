## Sobre o andamento do Projeto: 

Todas funcionalidades obrigatória descritas no site foram implemtadas.

<strong>Total de pontos até o momento:</strong> 23/32

> implementações:

1. Filtro por tipo - Concluido.
2. Ataque básico - Concluído.
3. Sistema de morte - Concluído.
3. Barra de vida - Concluído.
4. Arquivo de configurações - Concluido.
5. Fábrica de personagens - concluido

## Exemplo de como ficaram as implementações:

> O fluxo das classes ficram assim:

```md


                       <<interface>>
                         Coletador
                     +----------------+
                     | + coletar()    |
                     | + getCap()     |
                     +----------------+

                               ^
                               | implements
+----------------------------------------------------+
|                 Personagem (abstract)              |
+----------------------------------------------------+
| - posX, posY                                       |
| - atacando                                         |
| - icone, nomeImagem                                |
| + desenhar()                                       |
| + mover()                                          |
| + alterarAtaque()                                  |
+----------------------------------------------------+
            ^                ^                 ^
            |                |                 |
            |                |                 |
   +--------------+  +--------------+   +--------------+
   |   Aldeao     |  |  Cavaleiro   |   |  Arqueiro    |
   +--------------+  +--------------+   +--------------+
   | + atacar()   |  | ...          |   | ...          |
   | (Coletador)  |  |              |   |              |
   +--------------+  +--------------+   +--------------+


+-------------------------------------+
|               Tela                  |
+-------------------------------------+
| - personagens: Set<Personagem>      |
| + criarAldeao()                     |
| + criarArqueiro()                   |
| + criarCavaleiro()                  |
| + movimentarAldeoes()               |
| + atacarAldeoes()                   |
+-------------------------------------+
                 ^
                 |
                 | usada por
                 |
+---------------------------------------+
|           PainelControles             |
+---------------------------------------+
| - tela: Tela                          |
| - botões                              |
| + configurarListeners()               |
| + criarAldeaoAleatorio()              |
| + criarArqueiroAleatorio()            |
| + criarCavaleiroAleatorio()           |
+---------------------------------------+


             +------------------+
             |     Direcao      |
             +------------------+
             | CIMA, BAIXO,     |
             | ESQUERDA, DIREITA|
             +------------------+

```


### Próxima funcionalidades a serem trabalhada:

1. *Controles Avançados*

2. *Arquitetura de Software*

3. *Barra de vida*

4. *Alcance Variável*

5. *Esquiva*

> No momento só;


### 4 pontos - Filtro por tipo
Adicionado a movimentação dos tipos. Foi criada uma clase chamda `Move` que representa o tipo selecionado, ao ser selecionado outro tipo, e movimentado somente o tipo selecionado;

---

### 3 pontos - Ataque básico

<strong>Descreva o que você fez...</strong> @Lucas

---
### 3 pontos - Sistema de morte

<strong>Descreva o que você fez...</strong>@Lucas
---

### 4 pontos - Barra de vida

<strong>Descreva o que você fez...</strong> @Lucas

---

### 3 pontos – Arquivo de configurações

Foi adicionado o arquivo `joe.properties` [ver arquivo](./app/src/main/resources/joe.properties), contendo todos os valores padrão do jogo. Qualquer alteração feita nesse arquivo será refletida automaticamente em todos os pontos onde esses valores são utilizados.

Para carregar esses dados, foi criada a classe `ConfiguracaoJogo.java` [ver arquivo](./app/src/main/java/ifsc/joe/config/ConfiguracaoJogo.java). Ela é responsável por ler o arquivo de configuração e disponibilizar getters para acesso aos valores nas demais classes do projeto.

> Para mais informaões de como adicionar novos valores [ver documentação](./app/src/main/java/ifsc/joe/config/docs.md)


### 6 pontos - Fabrica de personagem 

implementado a fábrica de personagens, que visa a reutilização do código, evitando repetições desnecessárias. [ver arquivo](./app/src/main/java/ifsc/joe/factory/PersonagemFactory.java).

