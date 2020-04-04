# Wallet
# Описание

Требуется разработать сервис «Электронный Кошелек», предоставляющий Пользователям функционал электронных денег.  
Основные сущности: Пользователь, Кошелек. У Пользователя может быть множество кошельков в разной валюте. Справочник валют - текущий ISO. Сервис позволяет оперировать средствами на Кошельках:  
пополнение, списание, перевод между кошельками с конвертацией по курсу ЦБ.  
Пользователи характеризуются ФИО и номером телефона, номер телефона - уникальный идентификатор.     
Дополнительно Пользователи делятся на 2 группы: «Анонимные» и «Доверенные».   
Сервис осуществляет контроль суточной и месячной сумм операций у Пользователя.      
Лимиты для этого контроля задаются отдельно для Анонимных и Доверенных Пользователей.    
Сервис ежесуточно генерирует отчетность о своей работе  

# Предоставляемые бизнес-методы

• создание Пользователя 
POST /users  
```
{
"username": "Ivan Petrov",
"phoneNumber": "894949929292"
}
```

• создание Кошелька у Пользователя  
POST /user/wallet


• удаление Кошелька (доступно только для пустых кошельков)  
DELETE /user/wallet

• пополнение Кошелька на заданную сумму 
  – пополнение своего кошелька (по номеру кошелька)  
  – пополнение чужого кошелька (по номеру телефона Пользователя)   
 PUT /user/wallet/insert
 ```
 {
 "amount": 100
 "walletNumber": some-uuid
 }
 
 {
 "amount": 100
 "phoneNumber": 894949929292
 }
 ```
 
• перевод между Кошельками  
PUT /user/wallet/transfer
```
{
"walletNumberFrom": some-uuid
"walletNumberTo": some-uuid
"amount": 100
}
```

• списание с Кошелька  
PUT /user/wallet/withdraw
```
{
"walletNumber": some-uuid
"amount":100
}
```

• запрос истории операций по Кошельку за период  
GET /user/wallet/history
```
{
"walletNumber": some-uuid
"from": 10-03-2020
"to": 20-03-2020
}
```

• запрос отчетов за период дат  
GET /user/wallet/report
```
{
"walletNumber": some-uuid
"from": 10-03-2020
"to": 20-03-2020
}
```
• расчет пополнения - указываются все аргументы, как для пополнения, самого пополнения не происходит а осуществляется расчет, сколько попадет на Кошелек (в валюте Кошелька) 
GET /user/wallet/try/insert
 ```
 {
 "amount": 100
 "walletNumber": some-uuid
 }
 
 {
 "amount": 100
 "phoneNumber": 894949929292
 }
 ```

• расчет перевода - так же, как расчет пополнения  
GET /user/wallet/try/transfer
```
{
"walletNumberFrom": some-uuid
"walletNumberTo": some-uuid
"amount": 100
}
```

• расчет списания - так же, как расчет пополнения  
PUT /user/wallet/try/withdraw
```
{
"walletNumber": some-uuid
"amount":100
}
```


# Отчеты  
• суммарный баланс на всех кошельках на начало и конец дня  
• сумма входящих операций  
• сумма исходящий операций  

# Доп. требования    
• все суммы с валютой  
• все операции идемпотентны   
• любые операции могут прийти одновременно, сервис должен обеспечивать констистентность данных в этой ситуации    
• если после проведения операции Пользователь выйдет за установленные лимиты - операция отклоняется    
• сервис формирует суточный кеш валют и все расчетные операции производятся по нему   
• операции, приводящие к изменению, проводятся по актуальному курсу, запрашиваемому в ЦБ  
• у Пользователя должен быть Кошелек «по умолчанию», использующийся для операций без явного указания кошелька  
• сервис должен максимально обеспечивать констистентность данных в т.ч. для случая аварийного останова, как минимум гарантировать наличие информации для контроля наличия и исправления ошибок в данных  