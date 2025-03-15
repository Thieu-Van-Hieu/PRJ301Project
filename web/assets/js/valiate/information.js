document.addEventListener("DOMContentLoaded", () => {
    const hoTenInput = document.getElementById("hoTen");
    const tenInput = document.getElementById("ten");
    const maSVInput = document.getElementById("maSV");
  
    const hoTenRegex = /^[A-Za-zÀ-Ỹà-ỹ\s]{8,30}$/;
  
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
  
    function validateField(input, regex, errorText) {
      const rawValue = input.value;
      const value = rawValue.trim();
      const errorEl = createErrorElement(input);
  
      if (input.id === "hoTen" && /\s{2,}/.test(rawValue)) {
        input.style.border = "2px solid red";
        errorEl.textContent = "Không được spam khoảng trắng.";
        return false;
      }
  
      if (rawValue !== "" && value === "") {
        input.style.border = "2px solid red";
        errorEl.textContent = "Không được spam khoảng trắng.";
        return false;
      }
  
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
  
    hoTenInput.addEventListener("input", () => {
      validateField(hoTenInput, hoTenRegex, "Họ và tên phải từ 8-30 ký tự, chỉ chứa chữ và khoảng trắng.");
    });
    hoTenInput.addEventListener("blur", () => {
      const hoTen = hoTenInput.value.trim();
      if (hoTen.length > 0) {
        const words = hoTen.split(/\s+/);
        tenInput.value = words.length > 1 ? words[words.length - 1] : hoTen;
      }
    });
  
    function validateMaSV() {
      const value = maSVInput.value.trim();
      const errorEl = createErrorElement(maSVInput);
      if (value === "") {
        maSVInput.style.border = "2px solid #1b353b";
        errorEl.textContent = "";
        return false;
      }
      if (value.length < 2) {
        maSVInput.style.border = "2px solid red";
        errorEl.textContent = "Phải bắt đầu bằng HE, HS, HA hoặc GV.";
        return false;
      }
      const prefix = value.substring(0, 2).toUpperCase();
      if (!(prefix === "HE" || prefix === "HS" || prefix === "HA" || prefix === "GV")) {
        maSVInput.style.border = "2px solid red";
        errorEl.textContent = "Mã sinh viên phải bắt đầu bằng HE, HS, HA hoặc GV.";
        return false;
      }
      if (prefix === "HE" || prefix === "HS" || prefix === "HA") {
        if (value.length < 8) {
          if (value.length >= 4) {
            const digits = value.substring(2);
            const firstTwo = digits.substring(0, 2);
            if (/^\d{2}$/.test(firstTwo)) {
              const num = parseInt(firstTwo, 10);
              if (num < 15 || num > 21) {
                maSVInput.style.border = "2px solid red";
                errorEl.textContent = "2 số đầu phải từ 15 đến 21.";
                return false;
              }
            }
          }
          maSVInput.style.border = "2px solid orange";
          errorEl.textContent = "";
          return false;
        } else {
          const pattern = /^(HE|HS|HA)\d{6}$/i;
          if (!pattern.test(value)) {
            maSVInput.style.border = "2px solid red";
            errorEl.textContent = "Mã sinh viên phải có 2 chữ và 6 số.";
            return false;
          }
          const digits = value.substring(2);
          const firstTwo = digits.substring(0, 2);
          const num = parseInt(firstTwo, 10);
          if (num < 15 || num > 21) {
            maSVInput.style.border = "2px solid red";
            errorEl.textContent = "2 số đầu của phần số phải từ 15 đến 21.";
            return false;
          }
          maSVInput.style.border = "2px solid green";
          errorEl.textContent = "";
          return true;
        }
      } else if (prefix === "GV") {
        if (value.length < 8) {
          maSVInput.style.border = "2px solid orange";
          errorEl.textContent = "";
          return false;
        } else {
          const pattern = /^GV\d{6}$/i;
          if (!pattern.test(value)) {
            maSVInput.style.border = "2px solid red";
            errorEl.textContent = "Mã sinh viên phải có dạng GV và 6 số.";
            return false;
          }
          maSVInput.style.border = "2px solid green";
          errorEl.textContent = "";
          return true;
        }
      }
    }
    
    maSVInput.addEventListener("input", validateMaSV);
  });
  