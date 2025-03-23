document.addEventListener("DOMContentLoaded", () => {
    const oldPasswordInput = document.getElementById("oldPassword");
    const newPasswordInput = document.getElementById("newPassword");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const submitButton = document.querySelector("button[type='submit']");

    const validationRegex = /^(?=.{8,16}$)(?!\d+$)(?=.*[A-Za-z])[A-Za-z0-9]+$/;

    function createErrorElement(input) {
        let errorEl = input.parentElement.querySelector(".error-message");
        if (!errorEl) {
            errorEl = document.createElement("p");
            errorEl.className = "error-message";
            input.parentElement.appendChild(errorEl);
        }
        return errorEl;
    }

    function allowOnlyAlphanumeric(event) {
        if (event.key.length > 1)
            return;
        if (!/[A-Za-z0-9]/.test(event.key)) {
            event.preventDefault();
            const errorEl = createErrorElement(event.target);
            errorEl.textContent = "Không cho phép ký tự đặt biệt!";
            event.target.style.border = "2px solid red";
        }
    }

    function filterInput(event) {
        const originalValue = event.target.value;
        const filteredValue = originalValue.replace(/[^A-Za-z0-9]/g, '');
        if (originalValue !== filteredValue) {
            event.target.value = filteredValue;
            const errorEl = createErrorElement(event.target);
            errorEl.textContent = "Chỉ cho phép chữ và số!";
            event.target.style.border = "2px solid red";
        } else {
            const errorEl = createErrorElement(event.target);
            errorEl.textContent = "";
        }
    }

    function validateField(input) {
        const value = input.value;
        const errorEl = createErrorElement(input);

        if (value === "") {
            input.style.border = "2px solid #1b353b";
            errorEl.textContent = "";
            return false;
        }

        if (!/[A-Za-z]/.test(value)) {
            input.style.border = "2px solid red";
            errorEl.textContent = "Phải có ít nhất 1 chữ cái.";
            return false;
        }

        if (!validationRegex.test(value)) {
            input.style.border = "2px solid red";
            errorEl.textContent = "Nhập từ 8 đến 16 ký tự, không toàn số, không chứa ký tự đặt biệt.";
            return false;
        } else {
            input.style.border = "2px solid green";
            errorEl.textContent = "";
            return true;
        }
    }

    function validateConfirmPassword() {
        const errorEl = createErrorElement(confirmPasswordInput);
        if (confirmPasswordInput.value !== newPasswordInput.value) {
            confirmPasswordInput.style.border = "2px solid red";
            errorEl.textContent = "Mật khẩu xác nhận không khớp!";
            return false;
        } else {
            confirmPasswordInput.style.border = "2px solid green";
            errorEl.textContent = "";
            return true;
        }
    }

    oldPasswordInput.addEventListener("keydown", allowOnlyAlphanumeric);
    newPasswordInput.addEventListener("keydown", allowOnlyAlphanumeric);
    confirmPasswordInput.addEventListener("keydown", allowOnlyAlphanumeric);

    oldPasswordInput.addEventListener("input", filterInput);
    newPasswordInput.addEventListener("input", filterInput);
    confirmPasswordInput.addEventListener("input", filterInput);

    oldPasswordInput.addEventListener("input", () => validateField(oldPasswordInput));
    newPasswordInput.addEventListener("input", () => validateField(newPasswordInput));
    confirmPasswordInput.addEventListener("input", validateConfirmPassword);

    oldPasswordInput.addEventListener("blur", () => validateField(oldPasswordInput));
    newPasswordInput.addEventListener("blur", () => validateField(newPasswordInput));
    confirmPasswordInput.addEventListener("blur", validateConfirmPassword);

    submitButton.addEventListener("click", (e) => {
        const isOldValid = validateField(oldPasswordInput);
        const isNewValid = validateField(newPasswordInput);
        const isConfirmValid = validateConfirmPassword();

        if (!(isOldValid && isNewValid && isConfirmValid)) {
            console.log("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
            e.preventDefault();
        } else {
            console.log("Dữ liệu hợp lệ. Tiến hành đổi mật khẩu...");
        }
    });
});