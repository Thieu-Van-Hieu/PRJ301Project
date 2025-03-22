const validationRegex = /^(?=.*[A-Za-z])[A-Za-z0-9]{8,16}$/;
const newPasswordInput = document.getElementById("newPassword");
const confirmPasswordInput = document.getElementById("confirmPassword");
const newPasswordError = document.getElementById("newPasswordError");
const confirmPasswordError = document.getElementById("passwordError");

function checkSpecialCharacters(inputElement, errorElement) {
    const specialCharsRegex = /[^A-Za-z0-9]/g;
    if (specialCharsRegex.test(inputElement.value)) {
        errorElement.textContent = "Không cho phép ký tự đặc biệt";
        errorElement.style.display = "block";
        inputElement.style.border = "2px solid red";
        inputElement.value = inputElement.value.replace(specialCharsRegex, "");
        return true;
    } else {
        errorElement.style.display = "none";
        return false;
    }
}
function validateNewPassword() {
    const value = newPasswordInput.value;
    if (value === "") {
        newPasswordInput.style.border = "1px solid #ddd";
        newPasswordError.style.display = "none";
    } else if (!validationRegex.test(value)) {
        newPasswordInput.style.border = "2px solid red";
        newPasswordError.textContent = "Mật khẩu phải có từ 8-16 ký tự, ít nhất 1 chữ, không được chỉ nhập số";
        newPasswordError.style.display = "block";
    } else {
        newPasswordInput.style.border = "2px solid green";
        newPasswordError.style.display = "none";
    }
}
function validateConfirmPassword() {
    const newPassValue = newPasswordInput.value;
    const confirmValue = confirmPasswordInput.value;
    if (confirmValue === "") {
        confirmPasswordInput.style.border = "1px solid #ddd";
        confirmPasswordError.style.display = "none";
    } else if (confirmValue !== newPassValue) {
        confirmPasswordInput.style.border = "2px solid red";
        confirmPasswordError.style.display = "block";
    } else {
        confirmPasswordInput.style.border = "2px solid green";
        confirmPasswordError.style.display = "none";
    }
}
newPasswordInput.addEventListener("input", function () {
    if (!checkSpecialCharacters(newPasswordInput, newPasswordError)) {
        validateNewPassword();
    }
    validateConfirmPassword();
});

confirmPasswordInput.addEventListener("input", function () {
    if (!checkSpecialCharacters(confirmPasswordInput, confirmPasswordError)) {
        validateConfirmPassword();
    }
});