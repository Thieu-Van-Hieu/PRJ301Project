document.addEventListener("DOMContentLoaded", () => {
  const usernameInput = document.getElementById("username");
  const passwordInput = document.getElementById("matKhau");
  const confirmPasswordInput = document.getElementById("nhapLaiMatKhau");
  const emailInput = document.getElementById("email");
  const registerForm = document.querySelector("form");
  const errorMessage = document.getElementById("error-message");

  const validationRegex = /^(?=.{8,16}$)(?=.*[A-Za-z])[A-Za-z0-9]+$/;
  const emailRegex = /^[a-zA-Z0-9._%+-]+@(fpt\.edu\.vn|gmail\.com)$/;

  function createErrorElement(input) {
    let errorEl = input.parentElement.querySelector(".error-message");
    if (!errorEl) {
      errorEl = document.createElement("p");
      errorEl.className = "error-message";
      errorEl.style.paddingTop = "5px";
      errorEl.style.paddingBottom = "10px";
      errorEl.style.color = "red";
      errorEl.style.fontWeight = "600";
      input.parentElement.appendChild(errorEl);
    }
    return errorEl;
  }

  function allowOnlyAlphanumeric(event) {
    if (event.key.length > 1) return; 
    if (!/[A-Za-z0-9]/.test(event.key)) {
      event.preventDefault();
      const errorEl = createErrorElement(event.target);
      errorEl.textContent = "Không chấp nhận ký tự đặc biệt!";
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

  function validateField(input, regex, errorText) {
    const value = input.value.trim();
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

    if (!regex.test(value)) {
      input.style.border = "2px solid red";
      errorEl.textContent = errorText;
      return false;
    } else {
      input.style.border = "2px solid green";
      errorEl.textContent = "";
      return true;
    }
  }

  function checkPasswordMatch() {
    const value = confirmPasswordInput.value.trim();
    if (value === "") {
      confirmPasswordInput.style.border = "2px solid #1b353b";
      errorMessage.style.display = "none";
      return false;
    }
    if (value !== passwordInput.value.trim()) {
      confirmPasswordInput.style.border = "2px solid red";
      errorMessage.style.display = "block";
      return false;
    } else {
      confirmPasswordInput.style.border = "2px solid green";
      errorMessage.style.display = "none";
      return true;
    }
  }

  usernameInput.addEventListener("keydown", allowOnlyAlphanumeric);
  passwordInput.addEventListener("keydown", allowOnlyAlphanumeric);
  usernameInput.addEventListener("input", filterInput);
  passwordInput.addEventListener("input", filterInput);

  usernameInput.addEventListener("input", () => {
    validateField(usernameInput, validationRegex, "Tài khoản phải có từ 8-16 ký tự, ít nhất 1 chữ, chỉ chữ và số");
  });

  passwordInput.addEventListener("input", () => {
    validateField(passwordInput, validationRegex, "Mật khẩu phải có từ 8-16 ký tự, ít nhất 1 chữ, chỉ chữ và số");
  });

  confirmPasswordInput.addEventListener("input", checkPasswordMatch);
  confirmPasswordInput.addEventListener("blur", checkPasswordMatch);

  emailInput.addEventListener("input", () => {
    validateField(emailInput, emailRegex, "Email chỉ được dùng @fpt.edu.vn hoặc @gmail.com.");
  });

  registerForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const isUsernameValid = validateField(usernameInput, validationRegex, "Tài khoản không hợp lệ.");
    const isPasswordValid = validateField(passwordInput, validationRegex, "Mật khẩu không hợp lệ.");
    const isConfirmPasswordValid = checkPasswordMatch();
    const isEmailValid = validateField(emailInput, emailRegex, "Email không hợp lệ.");

    if (isUsernameValid && isPasswordValid && isConfirmPasswordValid && isEmailValid) {
      console.log("Dữ liệu hợp lệ. Tiến hành đăng ký...");
      registerForm.submit();
    } else {
      console.log("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
    }
  });
});