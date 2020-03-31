let empty_card_class = document.querySelectorAll('.empty_card');

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);

    [].forEach.call(empty_card_class, function (el) {
        console.log('slice: ' + el.id.slice(6) + ' target.id: ' + ev.target.id);
        if (!(ev.target.id).includes(el.id.slice(6))) {
            el.style.display = 'block';

        }
    });
}

function drop(ev) {
    ev.preventDefault();
    let data = ev.dataTransfer.getData("text");
    let newContainerName = ev.target.id.slice(6);
    let container = document.getElementById('container_' + newContainerName);
    container.append(document.getElementById(data));
    let el_id = data.replace(/(.*)_/, '');
    document.getElementById(data).setAttribute('id', newContainerName + '_' + el_id);
    $.get({
        url: "/task/" + el_id + "/" + newContainerName,
        success: function (data) {
            console.log("update task status: success")
        }
    });
    [].forEach.call(empty_card_class, function (el) {
        el.style.display = 'none'
    });
}

function hideAll() {
    [].forEach.call(empty_card_class, function (el) {
        el.style.display = 'none'
    });
}

////
$('.dropdown-toggle').dropdown();

let addUserToProject = document.getElementById('addUserToProject');
addUserToProject.addEventListener("change", (event)=> {
    let url = document.createElement('a');
    url.setAttribute('href', window.location);
    let param = url.search.substr(4);
    let user =addUserToProject.options[addUserToProject.selectedIndex].value;

    $.get({
        url: "/project" + '/' + param + '/' + user,
        success: function (data) {
        }
    });

});