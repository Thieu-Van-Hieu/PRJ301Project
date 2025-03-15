function updateGreeting() {
    let now = new Date();
    let hour = now.getHours();
    let greetingText = "";

    if (hour >= 5 && hour < 12) {
        greetingText = "Chào buổi sáng";
    } else if (hour >= 12 && hour < 18) {
        greetingText = "Chào buổi trưa";
    } else {
        greetingText = "Chào buổi tối";
    }

    document.getElementById("greeting").innerText = greetingText;
}

updateGreeting();

function updateBackground() {
    let now = new Date();
    let hour = now.getHours();
    let container = document.querySelector(".container");

    if (hour >= 5 && hour < 12) {
        container.style.backgroundImage = "url('../assets/img/loginmorning.jpg')";
    } else if (hour >= 12 && hour < 18 ) {
        container.style.backgroundImage = "url('../assets/img/loginnoon.jpg')";
    } else{
        container.style.backgroundImage = "url('../assets/img/loginnight.jpg')";
    }
    container.style.backgroundSize = "cover";
    container.style.backgroundPosition = "center";
    container.style.backgroundRepeat = "no-repeat";
    container.style.transition = "background 0.5s ease-in-out";
}

updateBackground();
