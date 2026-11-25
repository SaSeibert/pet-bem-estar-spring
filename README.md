# Sistema Pet Bem-Estar

Aplicação desenvolvida como requisito da disciplina de Programação para Web I (N2). O sistema tem como objetivo o cadastro e gerenciamento de tutores (clientes) e seus animais de estimação, contando com controle de acesso e autenticação de usuários.

## Configuração do Banco de Dados

O projeto utiliza o SGBD MySQL. O banco de dados configurado na aplicação é: `springpet`

## Passo a Passo para Execução

1. Crie previamente o schema `springpet` no seu servidor MySQL.
2. Abra o terminal na pasta raiz do projeto e inicie a aplicação com o comando: `./mvnw spring-boot:run` (ou `.\mvnw.cmd spring-boot:run` caso esteja no Windows).
3. Com o servidor rodando, acesse no navegador: `http://localhost:8080/login`.

## Funcionalidades Implementadas (Etapa N2)

1. **Camada de Serviço:** Criação das classes `TutorService`, `BichoService` e `UsuarioService`. Os controllers agora apenas repassam as requisições para os serviços, que são os únicos responsáveis por se comunicar com os DAOs.
2. **Validação de Exclusão:** O sistema bloqueia a remoção de tutores que possuam animais vinculados. Nesses casos, o `TutorService` dispara uma `RegraDeNegocioException` exibindo um aviso amigável na tela de listagem.
3. **Unicidade de Telefone:** O `TutorService` verifica se o número de telefone informado já está em uso por outro tutor (tanto no cadastro quanto na edição), evitando registros duplicados e alertando no próprio formulário.
4. **Ficha do Tutor:** Tela de detalhamento acessível ao clicar no nome do tutor, listando seu nome, telefone e todos os animais associados (utilizando consulta JPQL no DAO).
5. **Autenticação e Controle de Sessão:** Senhas criptografadas com `BCryptPasswordEncoder` e proteção de rotas através de um `LoginInterceptor`, que redireciona usuários não autenticados para a tela de login.
6. **Identidade Visual:** Estilização das telas seguindo a paleta de cores da cliente (tons de Verde, Laranja e Fundo Creme), além da inclusão da logomarca no cabeçalho de todas as páginas.

## Tecnologias Utilizadas

* Java 17 / Spring Boot
* Spring Data JPA
* Thymeleaf
* MySQL
* HTML / CSS
