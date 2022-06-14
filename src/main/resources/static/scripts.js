//url относительно хоста. Можно поменять порт в application.properties и всё будет работать.
const url = './api/';


//Отправляет запрос для получения гифки.

// для получения гифки которая будет обраюатываться rest controller
function loadResultGif() {
    let code = $("#codesSelector").val(); //получает выбранный option из select`а.
    console.log(url + 'epic/' + code);
    $.ajax({
        url: url + 'epic/' + code,
        method: 'GET',
        dataType: "json",
        complete: function (data) {  // обработка ответа
            console.log(data);
            let content = JSON.parse(data.responseText);
            console.log(content);
            let img = document.createElement("img");
            img.src = content.image;
            let out = document.querySelector("#out");
            out.innerHTML = '';
            out.insertAdjacentElement("afterbegin", img);
        }
    })
}

//Заполняет select
function loadForSelect() {
    $.ajax({
        url: url + 'getdate',
        method: 'GET',
        complete: function (data) {
            let codesList = JSON.parse(data.responseText);
            let select = document.querySelector("#codesSelector");
            select.innerHTML = '';
            for (let i = 0; i < codesList.length; i++) {
                let option = document.createElement("option");
                option.value = codesList[i];
                option.text = codesList[i];
                select.insertAdjacentElement("beforeend", option);
            }
        }
    })
}
