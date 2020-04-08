function myFunction() {

    var x = document.getElementById("navbarMain");
    if (x.classList.contains("unresponsive")) {
        x.classList.add("responsive");
        x.classList.remove( "unresponsive");
        x.classList.add("d-block");
    } else {
        x.classList.add("unresponsive");
        x.classList.remove("responsive");
        x.classList.remove("d-block");

    }
}