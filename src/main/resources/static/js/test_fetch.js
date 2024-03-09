const people = "https://randomuser.me/api/?results=100";

fetch(people)
    .then(function(response) {
        return response.json();
    })
    .then(function(data) {
        appendData(data);
    })
    .catch(function(err) {
        console.log("error : " + err)
    });

    function appendData(data) {
        var mainContainer = document.getElementById("myData");

        for (var i = 0; i < data.results.length; i++) {
            var table = document.createElement("table");
            table.innerHTML = `
            <tr>
                <td>
                    First Name : ${data.results[i].name.first}
                    <br>
                    Last Name : ${data.results[i].name.last}
                </td>
            </tr>
            `;
            mainContainer.appendChild(table)
        }
    }












// const axios = require('axios');

// let apiUrl = "http://localhost:1150/vocabulary/";
// let input = document.querySelector('.eng-input');
// let eng = document.querySelector('.eng-eng');
// let thai = document.querySelector('.eng-thai');
// let type = document.querySelector('.eng-type');
// let pronunciation = document.querySelector('.eng-pronunciation');

// function getEngData() {
//     axios.get(apiUrl + input.value)
//     .then(function(response) {
//         eng.innerHTML = response.data.eng;
//         thai.innerHTML = response.thai;
//         type.innerHTML = response.data.type;
//         pronunciation.innerHTML = response.data.pronunciation;
//     })
//     .catch((error) => {
//         console.error(error.message);
//     });
// }

// let button = document.querySelector("eng-button");
// button.addEventListener("click", getEngData);