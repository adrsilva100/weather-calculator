Api responsavel por realizar o calculo do clima dos planetas Vulcanos, Ferengis e Betasoides e disponibilizar informações referentes ao clima desses planetas nos proximos 10 anos.

# Acesso a API
API esta exposta na AWS utilizando Elastic Beanstalk.
Documentação no padrão OpenAPI está disponivel neste link http://apiweathercalculator.us-east-1.elasticbeanstalk.com/swagger-ui.html

Rotas:
 - http://apiweathercalculator.us-east-1.elasticbeanstalk.com/wheater/v1/initialize 
 - http://apiweathercalculator.us-east-1.elasticbeanstalk.com/wheater/v1/climate?day=566
 - http://apiweathercalculator.us-east-1.elasticbeanstalk.com/wheater/v1/amountofdays?climate=rain

Exemplo:
- Metodo POST http://apiweathercalculator.us-east-1.elasticbeanstalk.com/wheater/v1/initialize
  Rota responsavel por inicializar a base de dados contento os proximos 10 anos.
Response
  HTTP STATUS CODE 200 = Carga realizada com sucesso.
  HTTP STATUS CODE 422 = Erro durante o processo de carga.
  
- Metodo GET http://apiweathercalculator.us-east-1.elasticbeanstalk.com/wheater/v1/climate?day=566
  Parametro day contendo o número do dia a ser pequisado o clima.
  
Response 200
```
{
    "day": 566,
    "climate": "RAIN"
}
```
Response 404 = Dia informado não existe na base de dados.

- Metodo GET http://apiweathercalculator.us-east-1.elasticbeanstalk.com/wheater/v1/amountofdays?climate=rain
  Parametro climate contendo o tipo do clima a ser consultado: RAIN, DRY, GREAT OR NORMAL
  
Response 200 informando RAIN
```
{
    "amount_of_days": 936,
    "number_of_day": 72
}
```

Response 200 informando DRY, GREAT E NORMAL
```
{
    "amount_of_days": 11
}
```

Response 400 = Quando informado um climate invalido
```
{
    "code": 400,
    "response": "Parameter climate not found or not valid=dry22"
}
```
