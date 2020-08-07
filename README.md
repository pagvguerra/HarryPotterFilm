# HarryPotterFilm
Projeto baseado no Film HarryPotter - APIs Rest para gerenciar personagens de HarryPotter  

Neste projeto foram utilizadas as tecnologias **SPRING BOOT, SPRING DATA MONGODB, SPRING DATA JPA, LOMBOK, SWAGGER**  
Banco de dados **MONGODB**  
  
As APIs disponibilizadas para utilização deste projeto são:  
**Pesquisar Personagem por Id do Personagem:** /api/characters/findById/{id} - **Método:** Get  
**Pesquisar Todos os Personagem:** /api/characters/findAll - **Método:** Get  
**Pesquisar uma Lista de Personagens por Id da Casa:** /api/characters/{id} - **Método:** Get  
**Remover um Personagem por Id do Personagem:** /api/characters/delete/{id} - **Método:** Delete  
**Remover Todos os Personagens:** /api/characters/delete/deleteAll - **Método:** Delete  
**Incluir um Personagem:** /api/characters/save - **Método:** Post  
**Atualizar um Personagem:** /api/characters/save/{id} - **Método:** Put  
  
    
**Obs:** Lembrando que para salvar ou atualizar um Personagem é necessário enviar um ID válido para a Casa (de acordo com a PotterAPI).  
Caso contrário uma mensagem de erro será apresentada como resposta.
