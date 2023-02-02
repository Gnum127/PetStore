# language: ru

Функция: редактирование животных

  Сценарий: внесение изменений в существующее животное
    * выполнен POST запрос /pet с валидными параметрами животного
      | id           |                                                                         |
      | categoryId   | 88                                                                      |
      | categoryName | rat                                                                     |
      | name         | Klara                                                                   |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/f/fd/Fancy_rat_blaze.jpg |
      | tagId        | 99                                                                      |
      | tagName      | little                                                                  |
      | status       | available                                                               |
    * код ответа 200
    * тело ответа содержит отправленные параметры
    * выполнен PUT запрос /pet для внесения изменений в информацию о животном
      | id           |                                                                                          |
      | categoryId   | 44                                                                                       |
      | categoryName | cat                                                                                      |
      | name         | Mary                                                                                     |
      | photoUrls    | https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Kot-026.jpg/1200px-Kot-026.jpg |
      | tagId        | 66                                                                                       |
      | tagName      | large                                                                                    |
      | status       | sold                                                                                     |
    * тело ответа содержит отправленные параметры
