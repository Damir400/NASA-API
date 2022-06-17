//Отправляет запрос для получения гифки.
const imagesUrls = [];
const imagesItems = [];
let imagesLoaded = 0;

function createGif() {
 for (const item of imagesUrls) {
  let img = new Image(500,500);
  img.onload = function() {
   imagesLoaded++;
}
  img.src = item.url;
  imagesItems.push(img);
 }

 let i = 1;

 setInterval(() => {
  if (imagesLoaded == imagesUrls.length) {
   document.querySelector('img')?.remove();
   document.body.appendChild(imagesItems[i]);
   i++;
   if(i === imagesUrls.length) {
    i = 0;
   }
  }
 }, 125)
}

// для получения гифки которая будет обраюатываться rest controller
async function loadResultGif() {
    let code = $("#codesSelector").val(); //получает выбранный option из select`а.
 const result = await (await fetch('./api/epic/' + code)).json();
 imagesUrls.push(...result);

 createGif();
}

//Заполняет select
async function loadForSelect() {
 const data = (await (await fetch('./api/getdate')).json()).reverse();
 const select = document.querySelector("#codesSelector");
 select.innerHTML = '';
 for (const item of data) {
  let option = document.createElement("option");
  option.value = item;
  option.text = item;
  select.insertAdjacentElement("beforeend", option);
 }
}

window.onload = loadForSelect;