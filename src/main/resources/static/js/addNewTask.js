let addCard = document.getElementById('addCard');

addCard.addEventListener('click', function () {
    let windowToAddTask = document.createElement("div");
    windowToAddTask.setAttribute('id', 'windowToAddTask');
    let showWindowAddingCard = document.getElementById('showWindowAddingCard');
    let text = "<p> Utwórz zgłoszenie</p>" +
        "<br>" +
        "<div class='form-group'>" +
        "<label for='selectProject'> Projekt*</label>" +
        "<select id='selectProject'></select> </div>" +

        "<br>" +

        "<div class='form-group'><label for='NotificationType'> Typ zgłoszenia*</label>" +
        "<select id='NotificationType'>" + "<option>Story</option>" + "</select></div> " +

        "<div class='form-group'><label for='inputSummary'> Podsumowanie*</label>" +
        "<input id='inputName' class='form-control' type='text'></div>" +

        "<div class='form-group'><label for='inputDescription'> Opis</label>" +
        "<input id='inputDescription' class='form-control' type='text'></div> " +

        "<div class='form-group'> <label for='selectPriority'> Priorytet</label>" +
        "<select id='selectPriority'><option> Medium</option></select> </div>" +

        "</br>"+
        "<button type='submit' class='btn btn-info'>Utwórz</button>";


    showWindowAddingCard.innerHTML = text;
    console.log(showWindowAddingCard.innerHTML)
});