const submitButton = document.getElementById('submitButton');
let storedAds = [];
const storedAdsDiv = document.getElementById('currentAdsDiv');
let tokenInput = document.getElementById('tokenInput').value;


const eraseFormerMediaList = () => {

    while (storedAdsDiv.lastChild) {
        storedAdsDiv.removeChild(storedAdsDiv.lastChild);
    }
}


const displayMedia = mediaList => {

    eraseFormerMediaList();

    mediaList.forEach(media => {
        let mediaIDInDatabase = document.createElement('h1');
        mediaIDInDatabase.innerText = media.id;

        let mediaPage = document.createElement('h2');
        mediaPage.innerText = media.pageTitle;

        let mediaUrl = document.createElement('p');
        mediaUrl.innerText = media.adTagUrl;

        storedAdsDiv.appendChild(mediaIDInDatabase);
        storedAdsDiv.appendChild(mediaPage);
        storedAdsDiv.appendChild(mediaUrl);
    })
}

const fetchAllMedia = () => {
    fetch('/fetch-all-media')
        .then(data => data.json())
        .then(data => {
            data.forEach(media => {
                storedAds.push(media)
            });
        }).then(() => {
        displayMedia(storedAds)
    })
        .catch(error => console.log(error));
}

fetchAllMedia();


submitButton.addEventListener('click', () => {
    let pageLocation = document.getElementById('pageLocationInput').value;
    let adTagUrl = document.getElementById('adTagUrl').value;
    let columnPositionA = document.getElementById('columnAInput').value;
    let columnPositionB = document.getElementById('columnBInput').value;
    let columnPositionC = document.getElementById('columnCInput').value;


    let newMedia = {
        adTagUrl: adTagUrl,
        pageTitle: pageLocation,
        columnPositionA: columnPositionA,
        columnPositionB: columnPositionB,
        columnPositionC: columnPositionC
    }

    fetch("/add-media", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": tokenInput,

        },
        body: JSON.stringify(newMedia),
    }).then(response => console.log(response)).then(() => {
        fetchAllMedia()
    });
})


