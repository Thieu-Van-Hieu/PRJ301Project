const petalsPerInterval = 3;

function createPetal() {
  const petal = document.createElement('div');
  petal.classList.add('petal');
  // Đặt vị trí ngẫu nhiên
  petal.style.left = Math.random() * window.innerWidth + 'px';
  // Thời gian animation từ 3-8 giây
  const duration = 3 + Math.random() * 5;
  petal.style.animationDuration = duration + 's';
  document.getElementById('petalContainer').appendChild(petal);
  // Xóa lá sau khi animation kết thúc
  setTimeout(() => {
    petal.remove();
  }, duration * 1000);
}

// Tạo lá liên tục: mỗi 500ms tạo 3 lá
setInterval(() => {
  for (let i = 0; i < petalsPerInterval; i++) {
    createPetal();
  }
}, 500);
