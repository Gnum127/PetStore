# language: ru

Функция: создание заказа

  Сценарий: удаление заказа с валидными параметрами
    * выполнен POST запрос /store/order с параметрами заказа
      | petId    | 9274                         |
      | quantity | 12                           |
      | shipDate | 2023-02-02T09:48:27.937+0000 |
      | status   | placed                       |
      | complete | true                         |
    * Order код ответа 200
    * Order выполнен DELETE запрос /store/order/id
    * Order код ответа 200
    * Order выполнен GET запрос /store/order/id
    * Order код ответа 404

  Сценарий: удаление несуществующего заказа
    * выполнен POST запрос /store/order с параметрами заказа
      | petId    | 9274                         |
      | quantity | 12                           |
      | shipDate | 2023-02-02T09:48:27.937+0000 |
      | status   | placed                       |
      | complete | true                         |
    * Order код ответа 200
    * Order id изменен на тот, которого нет в базе
    * Order выполнен DELETE запрос /store/order/id
    * Order код ответа 404

  Сценарий: удаление существующего заказа с неверный форматом параметра id
    * Order выполнен DELETE запрос /store/order/ с неверный форматом параметра id
    * Order код ответа 400
