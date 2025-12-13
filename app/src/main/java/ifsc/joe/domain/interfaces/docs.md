## Questão

O professor solicitou a implementação das interfaces presentes neste diretório.
Fiquei pensativo sobre o que mais poderíamos adicionar a essas interfaces, como novos métodos, porém, no momento, a forma como estão representa uma base sólida para futuras implementações.

Meu objetivo é garantir que tudo esteja em perfeito funcionamento e atendendo a todas as solicitações do professor.


## Exemplo de como ficará as implementações:

```md

                  <<interface>>
                  Coletador
                  +-------------------------+
                  | boolean podeColetarTipo |
                  | int getRaioColeta();    |
                  +-------------------------+
                             ^
                             | implements
                  +-------------------------+
                  |         Aldeão          |
                  +-------------------------+
                  | - x, y, vida            |
                  | + métodos...            |
                  +-------------------------+
                       ^
                       |
             +----------------------+
             |  PainelDeControle   |
             +----------------------+
             | - jogador: Jogador  |
             | + métodos...        |
             +----------------------+

                       ^
                       |
             +----------------------+
             |           Tela       |
             +----------------------+
             | + exibir...          |
             | + atualizar...       |
             +----------------------+

```