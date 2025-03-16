function checkPasswordMatch() {
    const matKhau = document.getElementById("matKhau").value;
    const nhapLaiMatKhau = document.getElementById("nhapLaiMatKhau").value;
    const errorMessage = document.getElementById("error-message");

    if (matKhau !== nhapLaiMatKhau) {
        errorMessage.style.display = "block";
    } else {
        errorMessage.style.display = "none";
    }
}
