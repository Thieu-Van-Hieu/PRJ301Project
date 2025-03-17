let buttons = document.querySelectorAll("[random-position]");
buttons.forEach((button) => {
    let event = button.getAttribute("random-position");
    event.split(" ").forEach((e) => {
        button.addEventListener(e, () => {
            var x = Math.random() * (window.innerWidth - button.clientWidth);
            var y = Math.random() * (window.innerHeight - button.clientHeight);
            button.style.position = "absolute";
            button.style.left = x + "px";
            button.style.top = y + "px";
            button.style.transition = "all 0.5s";
        });
    });
});
