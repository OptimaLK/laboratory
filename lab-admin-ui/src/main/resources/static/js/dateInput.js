'use strict'
let dateStart = document.querySelector('#birthTime');
let dateEnd = document.querySelector('#lifeTime');

dateStart.addEventListener('change', putFormattedDate);
dateEnd.addEventListener('change', putFormattedDate);
dateStart.addEventListener('focus', changeType);
dateEnd.addEventListener('focus', changeType);

function changeType (event) {
    event.target.setAttribute( 'type' , 'date');
}

function putFormattedDate (event){
    let date = formatDate(event.target.value);
    event.target.setAttribute( 'type' , 'text');
    event.target.value = date;
    event.target.blur();
}

function checkForm (event) {

    if (new Date(dateStart.value) >= new Date(dateEnd.value)) {
        dateEnd.setAttribute( 'type' , 'text');
        spanEnd.value = 'Дата окончания не может быть раньше даты начала';
        event.preventDefault();
    }
}

function formatDate (date) {
    let separator;
    let array;
    if (date.includes( '-') ) {
        array = date.split('-');
        separator = '.';
    } else {
        array = date.split('.');
        separator = '-';
    }
    let result = '';
    for (let i = array.length; i > 0; i--){
        result += array[i - 1];
        if (i > 1) {
            result += separator;
        }
    }
    return result;
}
