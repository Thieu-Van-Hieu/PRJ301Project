function updateBackground() {
    let now = new Date();
    let hour = now.getHours();
    let container = document.querySelector(".container");

    if (hour >= 5 && hour < 12) {
        container.style.backgroundImage = "url('assets/img/loginmorning.jpg')";
    } else if (hour >= 12 && hour < 18) {
        container.style.backgroundImage = "url('assets/img/loginnoon.jpg')";
    } else {
        container.style.backgroundImage = "url('assets/img/loginnight.jpg')";
    }
}

document.addEventListener("DOMContentLoaded", updateBackground);