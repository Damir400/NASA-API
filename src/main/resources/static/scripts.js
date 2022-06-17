//Отправляет запрос для получения гифки.
let imagesUrls = [];
let imagesItems = [];
let imagesLoaded = 0;



function waiter(){
  let gifWaiter = new Image();
  gifWaiter.src = "/images/waitLoader.gif";
  document.body.appendChild(gifWaiter);

}




function createGif() {
 for (const item of imagesUrls) {
  let img = new Image(500,500);
  img.onload = function() {
   imagesLoaded++;
}
  img.src = item.url;
  imagesItems.push(img);
 }


}

// для получения гифки которая будет обраюатываться rest controller
async function loadResultGif() {
imagesLoaded = 0;
imagesUrls = [];
  imagesItems = [];
  document.querySelector('img')?.remove();
 waiter();
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




 let changeIndex = 0;

 setInterval(() => {
  if (imagesLoaded == imagesUrls.length && imagesLoaded > 0) {
   document.querySelector('img')?.remove();
   document.body.appendChild(imagesItems[changeIndex]);
   changeIndex++;
   if(changeIndex === imagesUrls.length) {
    changeIndex = 0;
   }
  }
 }, 100)