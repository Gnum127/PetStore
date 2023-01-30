# language: ru

  Функция: вывод списка животных по статусу

    Сценарий: поиск животных в статусе "sold"
      Когда выполнен GET запрос /pet/findByStatus?status= с параметром status = sold
      Тогда код ответа 200
      Тогда тело ответа содержит параметр status = sold

    Сценарий: поиск животных в статусе "pending"
      Когда выполнен GET запрос /pet/findByStatus?status= с параметром status = pending
      Тогда код ответа 200
      Тогда тело ответа содержит параметр status = pending

    Сценарий: поиск животных в статусе "available"
      Когда выполнен GET запрос /pet/findByStatus?status= с параметром status = available
      Тогда код ответа 200
      Тогда тело ответа содержит параметр status = available

    Сценарий: поиск животных в статусе "whatever"
      Когда выполнен GET запрос /pet/findByStatus?status= с параметром status = whatever
      Тогда код ответа 400