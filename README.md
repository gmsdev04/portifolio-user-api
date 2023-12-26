

Tecnologias utilizadas:

- Linguagem de programação:
  - Kotlin with Java 21
- Frameworks
  - Spring boot 3.X.X
  - Spring data
  - Spring actuator (Health check)
  - Swagger 2.3 (Make tests easy) =)
- Database (RDBMS)
  - MySQL (Version 8.0)
- Containers
  - Docker
  - Docker compose


http://localhost:8080/swagger-ui/index.html#/

Minhas observações: 

- Como nos requisitos não mencionava que era proibido atualizar CPF eu não bloqueie, entendo que em um caso real isso é improvável ser permitido.
- Como é uma aplicação local para teste utilizei o user, root, root mas entendo que em ambiente produtivo precisamos armazen
- Na documentação o requisito diz seguinte "Somente usuários acima de 18 anos serão cadastrados" portanto só será aceito a partir de 19 anos.
- Na documentação diz "Quando buscar por vários usuários, deve permitir realizar um filtro pelo nome", não tinha certeza se era para passar varios nomes e buscar vários users ou passar um nome e buscar vários users, fiz a segunda opção mas sei fazer a primeira também.