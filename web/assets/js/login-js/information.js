/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// File: /assets/js/information.js

// Tải danh sách tỉnh/thành phố khi trang được load
window.onload = loadProvinces;

async function loadProvinces() {
    try {
        const response = await fetch("https://vn-public-apis.fpo.vn/provinces/getAll?limit=-1");
        const data = await response.json();
        const provinceSelect = document.getElementById("province");

        data.data.data.forEach(province => {
            let option = document.createElement("option");
            option.value = province.code;
            option.textContent = province.name;
            provinceSelect.appendChild(option);
        });

    } catch (error) {
        console.error("Lỗi khi tải danh sách tỉnh/thành:", error);
    }
}

async function loadDistricts() {
    let provinceId = document.getElementById("province").value;
    let districtSelect = document.getElementById("district");

    districtSelect.innerHTML = '<option value="">Chọn quận/huyện</option>';

    if (provinceId) {
        try {
            let response = await fetch("https://vn-public-apis.fpo.vn/districts/getAll?limit=-1");
            let data = await response.json();

            console.log("API Response (Districts):", data);

            let districts = data.data.data.filter(d => d.parent_code === provinceId);
            if (districts.length === 0) {
                console.error("❌ Không có dữ liệu quận/huyện cho tỉnh này.");
                return;
            }

            districts.forEach(district => {
                let option = document.createElement("option");
                option.value = district.code;
                option.textContent = district.name;
                districtSelect.appendChild(option);
            });

        } catch (error) {
            console.error("Lỗi khi tải danh sách quận/huyện:", error);
        }
    }
}

async function loadWards() {
    let districtId = document.getElementById("district").value;
    let wardSelect = document.getElementById("ward");

    wardSelect.innerHTML = '<option value="">Chọn phường/xã</option>';

    if (districtId) {
        try {
            let response = await fetch("https://vn-public-apis.fpo.vn/wards/getAll?limit=-1");
            let data = await response.json();

            console.log("API Response (Wards):", data);

            let wards = data.data.data.filter(w => w.parent_code === districtId);
            if (wards.length === 0) {
                console.error("❌ Không có dữ liệu phường/xã cho quận này.");
                return;
            }

            wards.forEach(ward => {
                let option = document.createElement("option");
                option.value = ward.code;
                option.textContent = ward.name;
                wardSelect.appendChild(option);
            });

        } catch (error) {
            console.error("Lỗi khi tải danh sách phường/xã:", error);
        }
    }
}

function updateHiddenInput(selectElement, hiddenInputId) {
    const selectedOption = selectElement.options[selectElement.selectedIndex];
    document.getElementById(hiddenInputId).value = selectedOption.textContent;
}

document.getElementById("province").addEventListener("change", function () {
    updateHiddenInput(this, "provinceText");
    document.getElementById("district").innerHTML = '<option value="">Chọn quận/huyện</option>';
    document.getElementById("ward").innerHTML = '<option value="">Chọn phường/xã</option>';
    document.getElementById("districtText").value = "";
    document.getElementById("wardText").value = "";
});

document.getElementById("district").addEventListener("change", function () {
    updateHiddenInput(this, "districtText");
    document.getElementById("ward").innerHTML = '<option value="">Chọn phường/xã</option>';
    document.getElementById("wardText").value = "";
});

document.getElementById("ward").addEventListener("change", function () {
    updateHiddenInput(this, "wardText");
});

$(document).ready(function () {
    $('#ngaySinh, #thangSinh, #namSinh').select2({
        dropdownAutoWidth: true,
        width: '200px',
        maximumSelectionLength: 5,
        minimumResultsForSearch: -1
    });

    $('.select2-selection__arrow').remove();
});
