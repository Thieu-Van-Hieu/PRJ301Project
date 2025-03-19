/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", () => {
    const emailInput = document.getElementById("email");
    const forgotForm = document.querySelector("form");

    // Regex chỉ cho phép email có đuôi @fpt.edu.vn hoặc @gmail.com
    const emailRegex = /^[a-zA-Z0-9._%+-]+@(fpt\.edu\.vn|gmail\.com)$/;

    // Hàm tạo phần tử thông báo lỗi nếu chưa có
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

    // Hàm validate email
    function validateEmail() {
        const value = emailInput.value.trim();
        const errorEl = createErrorElement(emailInput);

        if (value === "") {
            emailInput.style.border = "2px solid #1b353b";
            errorEl.textContent = "";
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

    // Gán sự kiện input để kiểm tra ngay khi nhập liệu
    emailInput.addEventListener("input", validateEmail);

    // Xử lý khi submit form
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
