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
    * Pet код ответа 200
    * тело ответа содержит отправленные параметры
    * выполнен DELETE запрос /pet/id
    * Pet код ответа 200
    * выполнен GET запрос /pet/id
    * Pet код ответа 404

  Сценарий: удаление несуществующего животного
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
    * выполнен DELETE запрос /pet/id
    * Pet код ответа 404

  Сценарий: удаление существующего животного с неверный форматом параметра id
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
    * выполнен DELETE запрос /pet/id
    * Pet код ответа 400