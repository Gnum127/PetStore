# language: ru

Функция: редактирование животных

  Сценарий: внесение изменений в существующее животное
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * Pet код ответа 200
    * выполнен PUT запрос /pet с параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * Pet код ответа 200
    * тело ответа содержит отправленные параметры
    * выполнен GET запрос /pet/id
    * Pet код ответа 200
    * тело ответа содержит отправленные параметры

  Сценарий: внесение изменений в несуществующее животное
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * Pet код ответа 200
    * id изменен на тот, которого нет в базе
    * выполнен GET запрос /pet/id
    * Pet код ответа 404
    * выполнен PUT запрос /pet с параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * Pet код ответа 404

  Сценарий: внесение изменений в существующее животное с неверный форматом параметра id
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * Pet код ответа 200
    * id изменен на невалидный
    * выполнен PUT запрос /pet с параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * Pet код ответа 400

  Сценарий: внесение изменений в существующее животное с невалидными данными
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * Pet код ответа 200
    * выполнен PUT запрос /pet с параметрами животного
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | whatever                                                                                 |
    * Pet код ответа 405