# Restassured-api-testing 

Testes desenvolvidos durante o treinamento de Analista de testes automatizados da [CWI Software](https://cwi.com.br/).<p/>
API usada para testar os cenários: https://treinamento-api.herokuapp.com/

## Cenários testados:

### Suite Healthcheck:

#### /HEALTHCHECK
Verificar se API está online<p/>
### Suite Contract :

#### /GET
Garantir o contrato do retorno da lista de reservas<p/>
Garantir o contrato do retorno de uma reserva específica<p/>

### Suite Acceptance:

#### /DELETE

Excluir um reserva com sucesso

#### /GET

Listar IDs das reservas<p/>
Listar uma reserva específica<p/>
Listar IDs de reservas utilizando o filtro firstname<p/>
Listar IDs de reservas utilizando o filtro lastname<p/>
Listar IDs de reservas utilizando o filtro checkin<p/>
Listar IDs de reservas utilizando o filtro checkout<p/>
Listar IDs de reservas utilizando o filtro checkout and checkout<p/>
Listar IDs de reservas utilizando o filtro name, checkin and checkout date<p/>

#### /POST

Criar uma nova reserva

#### /PUT

Alterar uma reserva usando o token
Alterar uma reserva usando o Basic auth<p/>
### Suite E2e :

#### /DELETE

Tentar excluir um reserva que não existe<p/>
Tentar excluir uma reserva sem autorização<p/>
#### /GET

Visualizar erro de servidor 500 quando enviar filtro mal formatado<p/>
#### /POST

Validar retorno 500 quando o payload da reserva estiver inválido<p/>
Validar a criação de mais de um livro em sequencia<p/>
Criar uma reserva enviando mais parâmetros no payload da reserva<p/>
Validar retorno 418 quando o header Accept for invalido<p/>
#### /PUT

Tentar alterar uma reserva quando o token não for enviado<p/>
Tentar alterar uma reserva quando o token enviado for inválido<p/>
Tentar alterar uma reserva que não existe<p/>

