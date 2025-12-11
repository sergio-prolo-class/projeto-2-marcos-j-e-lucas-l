## Como implementar novos valores com essa configuração

Vamos supor que você queira adicionar um novo atributo para algum personagem — por exemplo, `saude`. O processo segue três etapas simples:

---

### 1. Definir o novo campo no `joe.properties`

```conf
# saude
saude.aldeao=100
```

---

### 2. Configurar no `ConfiguracaoJogo.java`

Aqui você precisa ajustar duas partes:

#### **a) Adicionar o valor padrão no método `carregarValoresPadrao`**

Esse valor será usado caso o arquivo `joe.properties` não seja encontrado:

```java
propriedades.setProperty("saude.aldeao", "100");
```

#### **b) Criar um getter para acessar o valor do arquivo de configuração**

O getter lê o valor do `properties` e já permite reutilização em outras classes:

```java
public int getAldeaoSaude() {
    return getInt("saude.aldeao");
}
```

---

### 3. Usar o novo valor no código

Por fim, basta garantir que a classe de configuração esteja instanciada e acessar o getter:

```java
// importar as configurações
import ifsc.joe.config.ConfiguracaoJogo;

// instanciar
private static final ConfiguracaoJogo config = new ConfiguracaoJogo();

// usar
private static final int SAUDE = config.getAldeaoSaude(); // 100
```

#### Observação

A classe abstrata `Personagem` já instancia `ConfiguracaoJogo`.
Isso significa que todas as classes que herdam de `Personagem` podem acessar a configuração diretamente, sem precisar criar uma nova instância.
