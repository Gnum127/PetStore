# language: ru

Функция: вывод списка животных по статусу

  Сценарий: поиск животных в статусе "sold"
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | sold                                                                    |
    * код ответа 200
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | sold |
    * код ответа 200
    * тело ответа содержит отправленные параметры


  Сценарий: поиск животных в статусе "pending"
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | pending                                                                 |
    * код ответа 200
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | pending |
    * код ответа 200
    * тело ответа содержит отправленные параметры

  Сценарий: поиск животных в статусе "available"
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | available |
    * код ответа 200
    * тело ответа содержит отправленные параметры

  Сценарий: поиск животных в статусе "whatever"
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | whatever |
    * код ответа 400