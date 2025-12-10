## Sobre o andamento do Projeto: 

Todas funcionalidades obrigatória descritas no site foram implemtadas.

<strong>Total de pontos até o momento:</strong> 11/32

> implementações:

1. Filtro por tipo - Concluido.
2. Ataque básico - Concluído.
3. Sistema de morte - Concluído.
3. Barra de vida - Concluído.

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
### 3 pontos - Ataque básico
### 3 pontos - Sistema de morte
### 4 pontos - Barra de vida

Adicionado a movimentação dos tipos. Foi criada uma clase chamda `Move` que representa o tipo selecionado, ao ser selecionado outro tipo, e movimentado somente o tipo selecionado;