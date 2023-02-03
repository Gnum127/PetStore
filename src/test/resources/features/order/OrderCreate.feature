# language: ru

Функция: создание заказа

  Сценарий: создание заказа с валидными параметрами
    * выполнен POST запрос /store/order с валидными параметрами заказа
      | id       |                              |
      | petId    | 9274                         |
      | quantity | 12                           |
      | shipDate | 2023-02-02T09:48:27.937+0000 |
      | status   | placed                       |
      | complete | true                         |
    * код ответа 200
    * тело ответа содержит отправленные параметры

  Сценарий: создание заказа с невалидным параметром complete
    * выполнен POST запрос /store/order с невалидным значением параметра complete
      | id       |                              |
      | petId    | 9274                         |
      | quantity | 12                           |
      | shipDate | 2023-02-02T09:48:27.937+0000 |
      | status   | placed                       |
      | complete | some                         |
    * код ответа 500

  Сценарий: создание заказа с невалидным параметром shipData
    * выполнен POST запрос /store/order с невалидным значением параметра ship data
      | id       |                             |
      | petId    | 9274                        |
      | quantity | 12                          |
      | shipDate | 2023-2-02T09:48:27.937+0000 |
      | status   | placed                      |
      | complete | true                        |
    * код ответа 500