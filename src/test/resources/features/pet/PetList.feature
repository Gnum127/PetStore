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
    * Pet код ответа 200
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | sold |
    * Pet код ответа 200
    * тело ответа содержит параметры животного


  Сценарий: поиск животных в статусе "pending"
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | pending                                                                 |
    * Pet код ответа 200
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | pending |
    * Pet код ответа 200
    * тело ответа содержит параметры животного

  Сценарий: поиск животных в статусе "available"
    * выполнен POST запрос /pet с параметрами животного
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * Pet код ответа 200
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | available |
    * Pet код ответа 200
    * тело ответа содержит параметры животного

  Сценарий: поиск животных в статусе "whatever"
    * выполнен GET запрос /pet/findByStatus с параметрами
      | status | whatever |
    * Pet код ответа 400