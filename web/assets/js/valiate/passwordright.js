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
function checkPasswordMatch() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var errorMessage = document.getElementById("error-message");

            if (newPassword !== confirmPassword) {
                errorMessage.style.display = "block";
            } else {
                errorMessage.style.display = "none";
            }
        }
document.addEventListener("DOMContentLoaded", function() {
        var newPassword = document.getElementById("newPassword");
        var confirmPassword = document.getElementById("confirmPassword");
        var passwordError = document.getElementById("passwordError");
        
        function checkPassword() {
            if (confirmPassword.value === "") {
                confirmPassword.style.border = "1px solid #ddd";
                passwordError.style.display = "none";
            } else if (confirmPassword.value !== newPassword.value) {
                confirmPassword.style.border = "2px solid red";
                passwordError.style.display = "block";
            } else {
                confirmPassword.style.border = "2px solid green";
                passwordError.style.display = "none";
            }
        }
        
        newPassword.addEventListener("input", checkPassword);
        confirmPassword.addEventListener("input", checkPassword);
    });
