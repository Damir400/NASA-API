# NASA API
## Описание
Cервис, который обращается к сервису данных NASA, и отображает снимки Земли за выбранную дату.
Для большей наглядности, изображения за полученную дату объеденены в gif, что позволяет увидеть вращение Земли за определенный день.
Ссылки
• REST API NASA : https://api.nasa.gov/
• Сервис на Spring Boot 2 + Java 
• Запросы приходят на HTTP endpoint, туда передается дата относительно которой происходит получение кадров Земли.
• Для взаимодействия с внешними сервисами используется Spring Cloud Feign.
• Все параметры (адреса внешнего сервиса, ключ api) вынесены в настройки.
• Для преобразования полученных снимков в gif используется javascript. 
• Для сборки используется Maven.
• На сервис написаны тесты  (@mockbean).