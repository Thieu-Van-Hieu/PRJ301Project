function checkPasswordMatch() {
    const matKhau = document.getElementById("matKhau").value;
    const nhapLaiMatKhau = document.getElementById("nhapLaiMatKhau").value;
    
    // Lấy phần tử thông báo lỗi
    const errorMessage = document.getElementById("error-message");

    // Kiểm tra xem mật khẩu và nhập lại mật khẩu có khớp không
    if (matKhau !== nhapLaiMatKhau) {
        // Nếu không khớp, hiển thị thông báo lỗi
        errorMessage.style.display = "block"; // Hiển thị thông báo lỗi
    } else {
        errorMessage.style.display = "none"; // Ẩn thông báo lỗi nếu mật khẩu khớp
    }
}
