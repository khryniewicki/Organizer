let buttonWithNotifations = document.getElementById("buttonWithNotifations");
let idUser = $('#userId').attr('name');
let value;

window.onload=function() {
    setInterval(checkNumberOfInformation, 5000);
}

function checkNumberOfInformation() {
    let badgeInfo = document.getElementById("badgeInfo");
    $.get("/newInformationCounter/" + idUser,
        function onsuccess(NumberOfInformation) {
            badgeInfo.innerText = NumberOfInformation;
            value=NumberOfInformation;
        })
}


buttonWithNotifations.addEventListener("click", () => {

    let idUser = $('#userId').attr('name');
    for (let i = 0; i <value ; i++) {

    $.get("/receivetaskInformation/" + idUser,
        function onsuccess(MessageEntity) {
          console.log(MessageEntity)
            console.log(MessageEntity[0]);

            let dropdown_item = document.createElement('a');
            dropdown_item.setAttribute('class', 'btn dropdown-item w-100 dropdown-item-Notifications font-weight-bold');
            dropdown_item.innerHTML = MessageEntity;

            let newMessagesList = document.getElementById('newMessages');
            newMessagesList.insertBefore(dropdown_item, newMessagesList.childNodes[0]);
        })
    }
})
