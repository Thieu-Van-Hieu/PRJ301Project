const petalsPerInterval = 3;

function createPetal() {
  const petal = document.createElement('div');
  petal.classList.add('petal');
  petal.style.left = Math.random() * window.innerWidth + 'px';
  const duration = 3 + Math.random() * 5;
  petal.style.animationDuration = duration + 's';
  document.getElementById('petalContainer').appendChild(petal);
  setTimeout(() => {
    petal.remove();
  }, duration * 1000);
}

setInterval(() => {
  for (let i = 0; i < petalsPerInterval; i++) {
    createPetal();
  }
}, 500);
