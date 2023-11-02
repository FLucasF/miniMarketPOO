# MiniMarket

O projeto "MiniMarket" é um aplicativo de gerenciamento de loja de conveniência. Ele fornece uma interface de usuário amigável para gerenciar produtos, controle de estoque. Os recursos incluem o cadastro de produtos e usuário, a atualização de informações do produto usuário, excluir os produtos, listar os produtos, buscar informações sobre um produto especifico e a funcionalidade de autenticação para garantir que apenas usuários autorizados tenham acesso a utilizar o programa. 

## :computer: Tecnologias utilizadas
Foi utilizado a biblioteca [java.swing](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html) para construir a parte que o usuário interage com a aplicação, com algumas partes de [JOPtionPane](https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html).
Na parte do "back end" do aplicativo, foi utilizado [MySQL](https://www.mysql.com/downloads/) para persistir os dados da aplicação e a versão 19 do [JDK](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html) do java para criar os métodos, sendo a principal linguagem utilizada no projeto.

## 🔧 Funcionalidades (simplificado)
## Produto

### Cadastrar

- É informado o `nome` do produto, a `quantidade` e o `valor`, depois é efetuado o método que ira cadastrar o produto no bando de dados do aplicativo.

### Listar

- Lista os produtos do bando de dados do aplicativo.

### Buscar

- É informado o `nome` do produto, depois o método de busca retorna o objeto e suas informações que estão salvas no bando de dados do aplicativo.

### Atualizar

- Atualiza as informações de um produto no bando de dados do aplicativo.

### Excluir

- É informado o `nome` do produto e em seguida é chamado o método que exclui um produto no bando de dados do aplicativo utilizando o nome para achar o objeto ProdutoDTO.

## Usuário

### Cadastrar
- É informado o `nome` e a `senha` do usuário, em seguida é cadastrado o usuário no banco de dados do aplicativo para poder utilizar o programa.

## 🔧 Funcionalidades (de forma aprofundada)
## Produto
### Cadastrar(nome do método: cadastrarProduto)

A método cadastrarProduto é um método que faz parte de uma classe responsável por interagir com um banco de dados. Ela recebe um objeto ProdutoDTO como parâmetro, que contém informações sobre o produto a ser cadastrado, como `nome`, `quantidade` e `valor`. Vou explicar o que esse método faz em detalhes:

SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para inserir os dados do produto no banco de dados. O SQL contém um comando `INSERT INTO` que especifica a tabela `products` e os campos `nome`, `quantidade`, e `valor` onde os dados serão inseridos. Os valores a serem inseridos são representados por três pontos de interrogação, que serão preenchidos posteriormente com os dados reais.

Conexão com o Banco de Dados: Em seguida, a método estabelece uma conexão com o banco de dados utilizando a classe Conexao. A conexão é armazenada na variável conn.

Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.

Preenchimento de Parâmetros: Os valores do objeto `ProdutoDTO (nome, quantidade e valor)` são inseridos nos lugares apropriados da consulta SQL usando métodos como `pstm.setString(1, objproductdto.getNome())`. Isso vincula os valores do objeto aos pontos de interrogação na consulta.

Execução da Consulta SQL: A consulta SQL é executada com `pstm.execute()`. Isso insere os dados na tabela do banco de dados.

Retorno de Sucesso: Se a operação de inserção for bem-sucedida, o método retorna true para indicar que o produto foi cadastrado com sucesso.

Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando JOptionPane, imprime informações de erro no console e retorna false para indicar que o cadastro falhou.

### Listar

O método listar foi separado em 3 tipos, sendo eles
- Pesquisar por ordem alfabetica(nome do método: `listarProdutoOrdemAlfabetica`):
  - A método `listarProdutoOrdemAlfabetica` é um método que faz parte de uma classe responsável por interagir com um banco de dados para listar produtos em ordem alfabética com base em seus nomes. Essa método retorna uma lista de objetos ProdutoDTO representando os produtos encontrados em ordem alfabética. Vou explicar o que esse método faz em detalhes:

    SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para selecionar todos os          dados da tabela `products` no banco de dados e ordená-los em ordem alfabética crescente com base no campo `nome`.

    Conexão com o Banco de Dados: Em seguida, a método estabelece uma conexão com o banco de dados utilizando a classe Conexao. A             conexão é armazenada na variável `conn`.

    Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.
    
    Execução da Consulta SQL: A consulta SQL é executada com `pstm.executeQuery()`. Isso recupera os dados dos produtos da tabela do banco de dados e armazena esses dados em um `ResultSet`.
    
    Iteração sobre Resultados: O código entra em um loop `while (rs.next())`, onde ele itera pelos resultados retornados pela consulta. Para cada linha de resultado (ou seja, cada produto), ele extrai o `nome`, `quantidade` e `valor` do produto do ResultSet.
    
    Criação de Objetos ProdutoDTO: Para cada produto encontrado, um objeto `ProdutoDTO` é criado e preenchido com as informações do produto (`nome`, `quantidade` e `valor`).
    
    Adição à Lista: O objeto `ProdutoDTO` recém-criado é adicionado a uma lista chamada lista. A lista é preenchida com todos os produtos encontrados durante a iteração.
    
    Encerramento de Recursos: Após a conclusão da iteração e da coleta de todos os produtos, o `ResultSet` e o `PreparedStatement` são fechados para liberar recursos.
    
    Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando `JOptionPane`, imprime informações de erro no console e retorna uma lista vazia.
    
    Retorno da Lista de Produtos: Finalmente, a lista de objetos `ProdutoDTO` preenchida com os produtos em ordem alfabética é retornada.

- Pesquisar por valor crescente(nome do método: `listarProdutoValorCrescente`)
   - A método `listarProdutoValorCrescente` é um método semelhante ao anterior, mas em vez de listar produtos em ordem alfabética, ele lista produtos em ordem crescente com base em seus valores. Vou explicar o que este método faz em detalhes:

      SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para selecionar todos os dados da tabela `products` no banco de dados e ordená-los em ordem crescente com base no campo `valor`.
      
      Conexão com o Banco de Dados: A método estabelece uma conexão com o banco de dados usando a classe `Conexao`, da mesma forma que no método anterior. A conexão é armazenada na variável `conn`.
      
      Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.
      
      Execução da Consulta SQL: A consulta SQL é executada com `pstm.executeQuery()`. Isso recupera os dados dos produtos da tabela do banco de dados e armazena esses dados em um `ResultSet`.
      
      Iteração sobre Resultados: O código entra em um loop `while (rs.next())`, onde ele itera pelos resultados retornados pela consulta. Para cada linha de resultado (ou seja, cada produto), ele extrai o `nome`, `quantidade` e `valo` do produto do `ResultSet`.
      
      Criação de Objetos ProdutoDTO: Para cada produto encontrado, um objeto ProdutoDTO é criado e preenchido com as informações do produto (nome, quantidade e valor).
      
      Adição à Lista: O objeto ProdutoDTO recém-criado é adicionado a uma lista chamada lista. A lista é preenchida com todos os produtos encontrados durante a iteração.
      
      Encerramento de Recursos: Após a conclusão da iteração e da coleta de todos os produtos, o `ResultSet` e o `PreparedStatement` são fechados para liberar recursos.
      
      Tratamento de Exceção: Como nos métodos anteriores, se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando `JOptionPane`, imprime informações de erro no console e retorna uma lista vazia.
      
      Retorno da Lista de Produtos: Finalmente, a lista de objetos `ProdutoDTO` preenchida com os produtos em ordem crescente com base nos valores é retornada.
     
- Pesquisar a lista da maneira que esta salva(nome do método: `listarProduto`)
  - A método `listarProduto` é um método que realiza uma consulta no banco de dados para listar todos os produtos da tabela `products` e retorna uma lista de objetos `ProdutoDTO` que contém informações sobre esses produtos. Vou explicar o que este método faz em detalhes:

    SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para selecionar todos os dados da tabela `products` no banco de dados.
    
    Conexão com o Banco de Dados: A método estabelece uma conexão com o banco de dados usando a classe Conexao, da mesma forma que nos métodos anteriores. A conexão é armazenada na variável `conn`.
    
    Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.
    
    Execução da Consulta SQL: A consulta SQL é executada com `pstm.executeQuery()`. Isso recupera os dados dos produtos da tabela do banco de dados e armazena esses dados em um `ResultSet`.
    
    Iteração sobre Resultados: O código entra em um loop `while (rs.next())`, onde ele itera pelos resultados retornados pela consulta. Para cada linha de resultado (ou seja, cada produto), ele extrai o `nome`, `quantidade` e `valo`r do produto do `ResultSet`.
    
    Criação de Objetos ProdutoDTO: Para cada produto encontrado, um objeto `ProdutoDTO` é criado e preenchido com as informações do produto (`nome`, `quantidade` e `valor`).
    
    Adição à Lista: O objeto `ProdutoDTO` recém-criado é adicionado a uma lista chamada lista. A lista é preenchida com todos os produtos encontrados durante a iteração.
    
    Encerramento de Recursos: Após a conclusão da iteração e da coleta de todos os produtos, o `ResultSet` e o `PreparedStatement` são fechados para liberar recursos.
    
    Tratamento de Exceção: Como nos métodos anteriores, se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando JOptionPane, imprime informações de erro no console e retorna uma lista vazia.
    
    Retorno da Lista de Produtos: Finalmente, a lista de objetos ProdutoDTO preenchida com os produtos encontrados no banco de dados é retornada.

### Pesquisar(nome do método: `pesquisarProduto`)

A método `pesquisarProduto` é um método que faz parte de uma classe responsável por interagir com um banco de dados para pesquisar informações sobre um produto com base no nome. Esta método retorna um objeto `ProdutoDTO` que contém os detalhes do produto pesquisado, caso ele exista. Vou explicar o que esse método faz em detalhes:

SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para selecionar informações do produto no banco de dados. A consulta utiliza o comando `SELECT * FROM` para buscar todos os dados na tabela `products` onde o `nome` corresponde ao nome fornecido como parâmetro.

Conexão com o Banco de Dados: Em seguida, a método estabelece uma conexão com o banco de dados utilizando a classe Conexao. A conexão é armazenada na variável `conn`.

Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.

Preenchimento de Parâmetros: O nome do produto a ser pesquisado é inserido no lugar apropriado da consulta SQL usando `pstm.setString(1, nome)`. Isso vincula o nome fornecido ao lugar apropriado na consulta.

Execução da Consulta SQL: A consulta SQL é executada com `pstm.executeQuery()`. Isso recupera os dados do produto da tabela do banco de dados e armazena esses dados em um `ResultSet`.

Verificação de Resultados: O código verifica se há algum resultado retornado da consulta usando `if (rs.next())`. Se houver um resultado (ou seja, o produto foi encontrado), os dados do produto são lidos do `ResultSet` e são definidos no objeto `produtoDTO`.

Retorno de Produto Encontrado: O objeto `produtoDTO` é retornado com os detalhes do produto pesquisado.

Exceção ProdutoNaoExistenteException: Se nenhum resultado for encontrado na consulta (ou seja, o produto não existe no banco de dados), o código lança uma exceção personalizada ProdutoNaoExistenteException com a mensagem "Produto não existente".

Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando JOptionPane, imprime informações de erro no console e, finalmente, lança a exceção ProdutoNaoExistenteException com a mesma mensagem de "Produto não existente".

### Atualizar(nome do método: `alterarProduto`)

A método `alterarProduto` é um método que faz parte de uma classe responsável por interagir com um banco de dados para atualizar informações de um produto. Ela recebe um objeto `ProdutoDTO` como parâmetro, que contém informações sobre o produto que será atualizado, incluindo o nome do produto, a nova quantidade e o novo valor. Vou explicar o que esse método faz em detalhes:

SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para atualizar os dados do produto no banco de dados. A consulta usa o comando UPDATE para especificar a tabela `products` e define quais colunas serão atualizadas (`nome`, `quantidade`, `valor`). A condição `WHERE` na consulta é usada para identificar o produto a ser atualizado com base no seu nome atual.

Conexão com o Banco de Dados: Em seguida, a método estabelece uma conexão com o banco de dados utilizando a classe Conexao. A conexão é armazenada na variável conn.

Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.

Preenchimento de Parâmetros: Os valores do objeto `ProdutoDTO` (`nome`, `quantidade` e `valor`) são inseridos nos lugares apropriados da consulta SQL usando métodos como `pstm.setString(1, objproductdto.getNome())`. Isso vincula os valores do objeto aos lugares apropriados na consulta. O último `pstm.setString(4, objproductdto.getNome())` é usado para identificar o produto que será atualizado com base no seu nome atual.

Execução da Consulta SQL: A consulta SQL é executada com `pstm.execute()`. Isso atualiza os dados na tabela do banco de dados com os novos valores fornecidos.

Retorno de Sucesso: Se a operação de atualização for bem-sucedida, o método retorna true para indicar que o produto foi atualizado com sucesso.

Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando `JOptionPane`, imprime informações de erro no console e retorna false para indicar que a atualização falhou.

### Excluir(nome do método: `excluirProduto`)

A método `excluirProduto` é um método que faz parte de uma classe responsável por interagir com um banco de dados para excluir um produto. Ela recebe um objeto `ProdutoDTO` como parâmetro, que contém informações sobre o produto que será excluído, incluindo o nome do produto. Vou explicar o que esse método faz em detalhes:

SQL Statement: Primeiro, o método cria uma string SQL que representa a consulta SQL que será executada para excluir um produto do banco de dados. A consulta utiliza o comando `DELETE FROM` para especificar a tabela `products` e define uma condição `WHERE` para identificar o produto que será excluído com base no seu nome.

Conexão com o Banco de Dados: Em seguida, a método estabelece uma conexão com o banco de dados utilizando a classe Conexao. A conexão é armazenada na variável conn.

Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a método `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.

Preenchimento de Parâmetros: O valor do objeto ProdutoDTO (nome) é inserido no lugar apropriado da consulta SQL usando `pstm.setString(1, objproductdto.getNome())`. Isso vincula o valor do objeto ao lugar apropriado na consulta, que será usado para identificar o produto a ser excluído.

Execução da Consulta SQL: A consulta SQL é executada com `pstm.execute()`. Isso exclui o produto da tabela do banco de dados com base no nome fornecido.

Retorno de Sucesso: Se a operação de exclusão for bem-sucedida, o método retorna true para indicar que o produto foi excluído com sucesso.

Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando JOptionPane, imprime informações de erro no console e retorna false para indicar que a exclusão falhou.
## Usuário
### Cadastrar o usuário(nome do método: `cadastrarUsuario`)
A função `cadastrarUsuario` é um método que realiza a inserção de um novo usuário no banco de dados. Esta função recebe um objeto `UsuarioDTO` como parâmetro, que contém informações sobre o usuário a ser cadastrado, incluindo o `nome_de_usuario` e a `senha`. Vou explicar o que este método faz em detalhes:

SQL Statement: A função cria uma string SQL que representa a consulta SQL a ser executada para inserir um novo registro na tabela `usuario`. A consulta utiliza o comando `INSERT INTO` para especificar a tabela e os campos aos quais os valores serão inseridos.

Conexão com o Banco de Dados: Em seguida, a função estabelece uma conexão com o banco de dados usando a classe `Conexao`. A conexão é armazenada na variável `conn`.

Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a função `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.

Preenchimento de Parâmetros: Os valores do objeto `UsuarioDTO` (`nome_de_usuário` e `senha`) são inseridos nos lugares apropriados da consulta SQL usando `pstm.setString(1, objUsuarioDTO.getNome_usuario())` e `pstm.setInt(2, Integer.parseInt(objUsuarioDTO.getSenha_usuario()))`. Isso vincula os valores do objeto aos lugares apropriados na consulta.

Execução da Consulta SQL: A consulta SQL é executada com `pstm.execute()`. Isso insere o novo usuário na tabela do banco de dados com o `nome_de_usuario` e a `senha` fornecidos.

Retorno de Sucesso: Se a operação de inserção for bem-sucedida, o método retorna true para indicar que o usuário foi cadastrado com sucesso.

Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando `JOptionPane`, imprime informações de erro no console e retorna false para indicar que o cadastro falhou.

### Excluir usuario(nome do método: `excluirUsuario`)
A função `excluirUsuario` é um método que realiza a exclusão de um usuário do banco de dados com base no nome de usuário. Ela recebe um objeto `UsuarioDTO` como parâmetro, contendo o nome do usuário a ser excluído. Vou explicar o que esse método faz em detalhes:

SQL Statement: A função cria uma string SQL que representa a consulta SQL a ser executada para excluir um registro da tabela `usuario`. A consulta utiliza o comando `DELETE FROM` para especificar a tabela da qual o registro será excluído e o critério para a exclusão, que é o nome do usuário.

Conexão com o Banco de Dados: A função estabelece uma conexão com o banco de dados usando a classe Conexao. A conexão é armazenada na variável conn.

Preparação da Consulta SQL: A consulta SQL é preparada para a execução com a função `conn.prepareStatement(sql)`. Isso é feito para evitar problemas de segurança e SQL injection.

Preenchimento de Parâmetros: O nome de usuário a ser excluído é inserido no lugar apropriado da consulta SQL usando `pstm.setString(1, usuarioDTO.getNome_usuario())`. Isso vincula o nome do usuário fornecido ao lugar apropriado na consulta.

Execução da Consulta SQL: A consulta SQL é executada com `pstm.execute()`. Isso exclui o usuário da tabela do banco de dados com o nome de usuário especificado.

Retorno de Sucesso: Se a operação de exclusão for bem-sucedida, o método retorna true para indicar que o usuário foi excluído com sucesso.

Tratamento de Exceção: Se ocorrer algum erro durante a execução da consulta SQL (por exemplo, se a conexão com o banco de dados falhar ou se houver algum problema na consulta), o método captura a exceção, exibe uma mensagem de erro ao usuário usando `JOptionPane`, imprime informações de erro no console e retorna false para indicar que a exclusão falhou.

## ⚙️ Executando os testes
  Os testes foram dividos em duas classes, uma para testar as funcionalidades com produtos e a outra foi para testar as funcionalidades do Usuario. Foi utilizado a ferramenta `JUnit` na versão `4.13.2`, a seguir esta um resumo de como foi efetuado o teste e seu estilo
### Teste com Produto

- Cadastro:

  Um novo produto é criado com os parâmetros "teste", 10 e 10, e é tentado cadastrar no sistema usando o método cadastrarProduto do `produtoDAO`.
Em seguida, é verificado se o cadastro foi bem-sucedido `(assertTrue(cadastro))`.
Depois, é feita uma verificação para garantir que o produto foi realmente cadastrado no sistema, usando `verificaProduto`.

- Pesquisa:

  O código tenta pesquisar um produto com o nome "teste" usando o método pesquisarProduto do `produtoDAO`.
É verificado se a pesquisa não lança exceções `(fail("Não deveria lançar exceção"))`.
Depois, são feitas asserções para garantir que os detalhes do produto pesquisado correspondem aos detalhes esperados (`nome`, `quantidade` e `valor`).

- Atualizar:

  Aqui, um novo produto com informações alteradas é criado (nome "testeAlterar", quantidade 25, valor 15) e é usado o método alterarProduto do produtoDAO para atualizar as informações do produto.
As asserções são usadas para verificar se as informações do produto foram atualizadas corretamente.

- Excluir:

  Os testes finais tentam excluir os produtos cadastrados anteriormente (o original e o alterado).
Verifica-se se a exclusão foi bem-sucedida `(assertTrue(produtoExcluido)` e `assertTrue(produtoExcluidoAt))`.


### Teste com Usuario
- Criação de um usuário:

  No início do teste, um objeto `UsuarioDTO` é criado e configurado com um nome de usuário "teste" e uma senha "1234".
Em seguida, o método cadastrarUsuario do `usuarioDAO` é chamado para cadastrar o usuário no sistema.
É verificado se o cadastro foi bem-sucedido através da asserção `assertTrue(cadastrar)`.

- Exclusão do usuário:

Após o cadastro do usuário, o teste tenta excluir o mesmo usuário usando o método `excluirUsuario` do `usuarioDAO`.
É verificado se a exclusão foi bem-sucedida através da asserção `assertTrue(excluir)`.
## 🛠️ Construído com

Mencione as ferramentas que você usou para criar seu projeto

- [Java](https://docs.oracle.com/javase/8/docs/api/overview-summary.html) - A linguagem utilizada
- [Maven](https://maven.apache.org/) - Gerente de Dependência
- [MySQL](https://dev.mysql.com/doc/) - Utilizado para persistencia de dados
- [Junit](https://junit.org/junit4/) - Utilizado para testes das funcionalidades

## 📌 Versão
- Java - JDK 19
- Maven - 
- MySQL - 8.0.33
- Junit - 4.13.2
## ✒️ Autores

- **Lucas Felipe** - *Desenvolvedor*

## :shipit: Colaboradores

- **Lucas Monteiro** - *Monitor* - [perfil](https://github.com/Lucaspzzzz)
- **Felipe Alencar** - *Monitor* - [perfil]()
