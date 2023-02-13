# language: ru

Функция: удаление животных

  Сценарий: удаление существующего животного
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * тело ответа содержит отправленные параметры животного
    * выполнен DELETE запрос /pet/id
    * код ответа 200
    * выполнен GET запрос /pet/id
    * код ответа 404

  Сценарий: удаление несуществующего животного
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * id изменен на тот, которого нет в базе
    * выполнен DELETE запрос /pet/id
    * код ответа 404

  Сценарий: удаление животного с неверный форматом параметра id
    * выполнен DELETE запрос /pet/ с невалидным значением параметра id = q
    * код ответа 400