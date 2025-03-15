
function isLeapYear(year) {
    return (year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0));
}

function isValidDate(day, month, year) {
    const daysInMonth = [31, (isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (month < 1 || month > 12) {
        return false;
    }
    if (day < 1 || day > daysInMonth[month - 1]) {
        return false;
    }

    return true;
}
function validateBirthday() {
    const day = parseInt(document.getElementById('ngaySinh').value, 10);
    const month = parseInt(document.getElementById('thangSinh').value, 10);
    const year = parseInt(document.getElementById('namSinh').value, 10);

    if (!day || !month || !year) {
        alert("Vui lòng chọn đầy đủ ngày, tháng, và năm.");
        return false;
    }
    if (!isValidDate(day, month, year)) {
        alert("Ngày tháng năm không hợp lệ. Vui lòng chọn lại.");
        return false;
    }

    alert("Thông tin sinh nhật hợp lệ!");
    return true;
}

document.querySelector('form').onsubmit = function (event) {
    event.preventDefault();
    if (validateBirthday()) {
        this.submit();
    }
}
