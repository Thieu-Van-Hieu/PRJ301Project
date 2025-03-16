document.addEventListener("DOMContentLoaded", () => {
  const usernameInput = document.getElementById("loginEmail");
  const passwordInput = document.getElementById("loginPassword");
  const loginButton = document.getElementById("login");

  const validationRegex = /^(?=.{8,16}$)(?!\d+$)(?=.*[A-Za-z])[A-Za-z0-9]+$/;

  function createErrorElement(input) {
    let errorEl = input.parentElement.querySelector(".error-message");
    if (!errorEl) {
      errorEl = document.createElement("p");
      errorEl.className = "error-message";
      errorEl.style.paddingTop = "15px";
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
      errorEl.textContent = "Không cho phép ký tự đặc biệt!";
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
      errorEl.textContent = "Vui lòng nhập theo mẫu: từ 8 đến 16 ký tự, không toàn số, không chứa ký tự đặt biệt";
      return false;
    } else {
      input.style.border = "2px solid green";
      errorEl.textContent = "";
      return true;
    }
  }

  usernameInput.addEventListener("keydown", allowOnlyAlphanumeric);
  passwordInput.addEventListener("keydown", allowOnlyAlphanumeric);

  usernameInput.addEventListener("input", filterInput);
  passwordInput.addEventListener("input", filterInput);

  usernameInput.addEventListener("input", () => validateField(usernameInput));
  passwordInput.addEventListener("input", () => validateField(passwordInput));
  usernameInput.addEventListener("blur", () => validateField(usernameInput));
  passwordInput.addEventListener("blur", () => validateField(passwordInput));

  loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const isUsernameValid = validateField(usernameInput);
    const isPasswordValid = validateField(passwordInput);
    if (isUsernameValid && isPasswordValid) {
      console.log("Dữ liệu hợp lệ. Tiến hành xử lý đăng nhập...");
    } else {
      console.log("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
    }
  });
});
