# language: ru

Функция: редактирование животных

  Сценарий: внесение изменений в существующее животное
    * выполнен POST запрос pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * выполнен PUT запрос pet с параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * код ответа 200
    * тело ответа содержит отправленные параметры
    * выполнен GET запрос pet с параметром id животного
    * код ответа 200
    * тело ответа содержит отправленные параметры

  Сценарий: внесение изменений в несуществующее животное
    * выполнен POST запрос pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * id изменен на тот, которого нет в базе
    * выполнен GET запрос pet с параметром id животного
    * код ответа 404
    * выполнен PUT запрос pet с параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * код ответа 404

  Сценарий: внесение изменений в животное с невалидным значением параметра id
    * выполнен PUT запрос pet с параметрами животного, задано невалидное значение параметра id
      | id           | q                                                                                        |
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * код ответа 400

  Сценарий: внесение изменений в существующее животное с невалидными данными
    * выполнен POST запрос pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * выполнен PUT запрос pet с невалидными параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | whatever                                                                                 |
    * код ответа 200
    * выполнен GET запрос pet с параметром id животного
    * код ответа 200
    * тело ответа содержит отправленные в POST запросе параметры