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


