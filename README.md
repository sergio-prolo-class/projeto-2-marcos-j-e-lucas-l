## Sobre o andamento do Projeto: 

Todas funcionalidades **obrigatória** descritas no site foram implemtadas.

<strong>Total de pontos até o momento:</strong> 29/32

> implementações:

1. Filtro por tipo - Concluido.
2. Ataque básico - Concluído.
3. Sistema de morte - 90% concluído.
4. Alcance variável- 90% concluído. 
5. Barra de vida - 90% Concluído.
6. Arquivo de configurações - Concluído.
7. Fábrica de personagens - concluído
8. Cache de Recursos - concluído 

---

### Próxima funcionalidades a serem trabalhada:

1. *Controles Avançados*

2. *Arquitetura de Software* - concluído

3. *Barra de vida* - concluído

4. *Alcance Variável*

5. *Esquiva*

6. *Outros...*

> No momento só;

---

## Descriçaõ das funcionalidades e seus pontos:

### 4 pontos - Filtro por tipo

Adicionado a movimentação dos tipos. Foi criada uma classe chamda `Move` que representa o tipo selecionado, ao ser selecionado outro tipo, e movimentado somente o tipo selecionado;

---

### 3 pontos - Ataque básico

Adicionado ataque básico para os personagens Arqueiro e Cavaleiro. O ataque funciona com os personagens montados e desmontados, com várias funções implementadas.

---
### 3 pontos - Sistema de morte

Sistema que organiza como os personagens morrem, quando o personagem tem vida 0 ele some do sistema.
---

### 4 pontos - Barra de vida

Barra de vida feita na classe Personagem.

---

### 3 pontos – Arquivo de configurações

Foi adicionado o arquivo `joe.properties` [ver arquivo](./app/src/main/resources/joe.properties), contendo todos os valores padrão do jogo. Qualquer alteração feita nesse arquivo será refletida automaticamente em todos os pontos onde esses valores são utilizados.

Para carregar esses dados, foi criada a classe `ConfiguracaoJogo.java` [ver arquivo](./app/src/main/java/ifsc/joe/config/ConfiguracaoJogo.java). Ela é responsável por ler o arquivo de configuração e disponibilizar getters para acesso aos valores nas demais classes do projeto.

> Para mais informaões de como adicionar novos valores [ver documentação](./app/src/main/java/ifsc/joe/config/docs.md)
---

### 6 pontos - Fabrica de personagem 

implementado a fábrica de personagens, que visa a reutilização do código, evitando repetições desnecessárias. [ver arquivo](./app/src/main/java/ifsc/joe/factory/PersonagemFactory.java).

---
## 6 Pontos - Cache de Recursos

Foi implementada uma classe com o objetivo de carregar as imagens uma única vez, para melhorar o desempenho e a performance do jogo. [ver arquivo](./app/src/main/java/ifsc/joe/resources/ImageCache.java)

A implementação foi realizada conforme as solicitações. Veja o arquivo
 da classe responsável por gerenciar o cache, e na classe abstrata foi implementado o uso do cache.

---
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
