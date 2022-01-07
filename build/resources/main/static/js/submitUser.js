let chosenState = '';
const stateDropDiv = document.getElementById('stateDropDown');

let tokenInput = document.getElementById('tokenInput').value;
console.log("TOKEN1: "+tokenInput);

let testMeta = document.querySelector('meta[name="_csrf"]').content;
let header = document.querySelector('meta[name="_csrf_header"]').content;

console.log("TOKEN2: "+testMeta);

async function getListOfStates() {
    let dataFromBackend = await fetch("/fetch-all-USAStates");
    return await dataFromBackend.json();
}

getListOfStates().then(result => {

    let statesList = result;

    stateDropDiv.addEventListener('click', ()=> {
        statesList.forEach(state => {
            let stateToChoose = document.createElement('p');
            stateToChoose.innerText = state;

            stateToChoose.addEventListener('click', ()=> {
                chosenState = stateToChoose.innerText;
                console.log(chosenState);
            })

            stateDropDiv.appendChild(stateToChoose);
        })
    })

})

let submitButton = document.getElementById('submitToDatabase');
submitButton.addEventListener('click', ()=> {

    let newUserData = {
        email: document.getElementById('email').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value
        // usaState: chosenState,
        // dateCreated: new Date()
    }
    console.log(newUserData);

    fetch("/add-user", {
        method: "POST",
        headers: {"Content-Type": "application/json",
            "X-CSRF-TOKEN": tokenInput,

        },
        body: JSON.stringify(newUserData),
    }).then(response => console.log(response));
});

let getUsersButton = document.getElementById('getUsersButton');
getUsersButton.addEventListener('click', ()=> {
    fetch('/get-all-users').then(data => data.json()).then(data => console.log(data));
})



