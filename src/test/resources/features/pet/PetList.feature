# language: ru

Функция: вывод списка животных по статусу

  Сценарий: поиск животных в статусе "sold"
    * выполнен GET запрос /pet/findByStatus?status= с параметром status = sold
    * код ответа 200
    * тело ответа содержит параметр status = sold

  Сценарий: поиск животных в статусе "pending"
    * выполнен GET запрос /pet/findByStatus?status= с параметром status = pending
    * код ответа 200
    * тело ответа содержит параметр status = pending

  Сценарий: поиск животных в статусе "available"
    * выполнен GET запрос /pet/findByStatus?status= с параметром status = available
    * код ответа 200
    * тело ответа содержит параметр status = available

  Сценарий: поиск животных в статусе "whatever"
    * выполнен GET запрос /pet/findByStatus?status= с параметром status = whatever
    * код ответа 400