# language: ru

Функция: создание животных

  Сценарий: создание животного с валидными параметрами
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

  Сценарий: создание животного с невалидными параметрами
    * выполнен POST запрос /pet с параметрами животного
      | id           | q                                                                       |
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * Pet код ответа 405