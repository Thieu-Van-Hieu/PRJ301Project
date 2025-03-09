function updateGreeting() {
    let now = new Date();
    let hour = now.getHours();
    let greetingText = "";

    if (hour >= 5 && hour < 12) {
        greetingText = "Good Morning";
    } else if (hour >= 12 && hour < 18) {
        greetingText = "Good Afternoon";
    } else {
        greetingText = "Good Night";
    }

    document.getElementById("greeting").innerText = greetingText;
}

updateGreeting();

function updateBackground() {
    let now = new Date();
    let hour = now.getHours();
    let container = document.querySelector(".container");

    if (hour >= 5 && hour < 12) {
        container.style.backgroundImage = "url('../assert/image/loginmorning.jpg')";
    } else if (hour >= 12 && hour < 18 ) {
        container.style.backgroundImage = "url('../assert/image/loginnoon.jpg')";
    } else{
        container.style.backgroundImage = "url('../assert/image/loginnight.jpg')";
    }
    container.style.backgroundSize = "cover";
    container.style.backgroundPosition = "center";
    container.style.backgroundRepeat = "no-repeat";
    container.style.transition = "background 0.5s ease-in-out";
}

updateBackground();
