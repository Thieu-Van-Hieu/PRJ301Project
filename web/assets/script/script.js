function move(element) {
    var x = Math.random() * window.innerWidth;
    var y = Math.random() * window.innerHeight;
    element.style.position = "absolute";
    element.style.left = x + "px";
    element.style.top = y + "px";
    console.log("Moved to " + x + " " + y);
}
