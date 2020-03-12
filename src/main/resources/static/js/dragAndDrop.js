let empty_card_class = document.querySelectorAll('.empty_card');

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
    [].forEach.call(empty_card_class, function (el) {
        if (el.id.slice(6) !== ev.target.id) {
            el.style.visibility = 'visible';
        }
    });}

function drop(ev) {
    ev.preventDefault();
    let data = ev.dataTransfer.getData("text");
    let container = document.getElementById('container' + (ev.target.id.slice(5)));
    container.appendChild(document.getElementById(data));
    [].forEach.call(empty_card_class, function (el) {
        el.style.visibility = 'hidden'
    });
}

function hideAll() {
        [].forEach.call(empty_card_class, function (el) {
            el.style.visibility = 'hidden'
        });}
