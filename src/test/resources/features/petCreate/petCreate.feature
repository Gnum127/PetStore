# language: ru

Функция: создание животных

  Сценарий: создание животного с валидными параметрами
    Когда выполнен POST запрос /pet с валидными параметрами животного
      | id           |                                                                         |
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    Тогда код ответа 200
    Тогда тело ответа содержит отправленные параметры

  Сценарий: создание животного с невалидными параметрами
    Когда выполнен POST запрос /pet с невалидным значением id животного
      | id           | q                                                                       |
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    Тогда код ответа 500