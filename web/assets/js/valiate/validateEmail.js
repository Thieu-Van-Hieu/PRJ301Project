document.addEventListener("DOMContentLoaded", () => {
    const emailInput = document.getElementById("email");
    const forgotForm = document.getElementById("forgotForm");

    // Regex chỉ cho phép email có đuôi @fpt.edu.vn hoặc @gmail.com
    const emailRegex = /^[a-zA-Z0-9._%+-]+@(fpt\.edu\.vn|gmail\.com)$/;

    // Hàm tạo phần tử thông báo lỗi nếu chưa có, sử dụng lớp "error"
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

    // Hàm validate email: kiểm tra nếu trống, hiển thị lỗi yêu cầu nhập email; nếu không hợp lệ, thông báo lỗi khác; nếu hợp lệ, viền xanh
    function validateEmail() {
        const value = emailInput.value.trim();
        const errorEl = createErrorElement(emailInput);

        if (value === "") {
            emailInput.style.border = "2px solid red";
            errorEl.textContent = "Vui lòng nhập email.";
            return false;
        }

        if (!emailRegex.test(value)) {
            emailInput.style.border = "2px solid red";
            errorEl.textContent = "Email chỉ được dùng @fpt.edu.vn hoặc @gmail.com.";
            return false;
        } else {
            emailInput.style.border = "2px solid green";
            errorEl.textContent = "";
            return true;
        }
    }

    // Kiểm tra ngay khi người dùng nhập (real-time)
    emailInput.addEventListener("input", validateEmail);

    // Xử lý submit form
    forgotForm.addEventListener("submit", (e) => {
        e.preventDefault();
        if (validateEmail()) {
            console.log("Email hợp lệ. Tiến hành xử lý quên mật khẩu...");
            forgotForm.submit();
        } else {
            console.log("Email không hợp lệ. Vui lòng kiểm tra lại.");
        }
    });
});
