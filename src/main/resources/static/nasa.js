
let imagesUrls = [];
let imagesItems = [];
let imagesLoaded = 0;
let completedItems = []

// отображение анимации загрузки данных 
function waiter() {
    let gifWaiter = new Image();
    gifWaiter.src = "./src/main/resources/static/images/waitLoader.gif";
    document.body.appendChild(gifWaiter);
}

// заполнение массива картинками
function createGif() {
    imagesItems = [];

    for (const item of imagesUrls) {
        let img = new Image(500, 500);
        img.onload = function() {
            imagesLoaded++;
        }

        img.src = item.url;
        imagesItems.push(img);
    }
}

// построение URL для полечения картинки
function buildImageUrl(jsonData) {
    for (let i = 0; i < jsonData.length; i++) {
        jsonData[i].url = 'https://api.nasa.gov/EPIC/archive/natural/' + jsonData[i].date.split(' ')[0].split('-').join('/') + '/png/' + jsonData[i].image + '.png?api_key=XvASfaPmdD3X98CazMKJpgeKBSsdZVpRF8PxIhE9';
    }

    return jsonData;
}

// загрузка данных по картинкам
async function loadResultGif() {
    imagesLoaded = 0;
    imagesUrls = [];
    
    document.querySelector('img')?.remove();
    waiter();
    
    let date = $("#datesSelector").val(); 
    let result = await (await fetch('https://api.nasa.gov/EPIC/api/natural/date/' + date + '?api_key=XvASfaPmdD3X98CazMKJpgeKBSsdZVpRF8PxIhE9')).json();

    result = buildImageUrl(result);
    imagesUrls.push(...result); 

    createGif();
}

// получение данных о доступных датах
async function loadForSelect() {
    let data = await (await fetch('https://epic.gsfc.nasa.gov/api/natural/available')).json();
    data = data.reverse();
    const select = document.querySelector("#datesSelector");
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
let completedIndex = 0;

setInterval(() => {
    if (imagesLoaded == imagesUrls.length && imagesLoaded > 0) {
        document.querySelector('img')?.remove();
        document.body.appendChild(imagesItems[changeIndex]);
        
        changeIndex++;
     
        if(changeIndex === imagesUrls.length) {
            changeIndex = 0;
        }
    }
}, 500)
