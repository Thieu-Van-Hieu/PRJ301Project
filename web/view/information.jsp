<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../assets/css/hoaroi.css" />
    <link rel="stylesheet" href="../assets/css/login-css/information.css" />
    <link rel="stylesheet" href="../assets/css/reponsivecss/reponsiveinfor.css">
    <style>
        .petal {
            position: absolute;
            width: 20px;
            height: 20px;
            background: url('../assets/img/effect-img/hoaanhdao.png') no-repeat center center;
            background-size: contain;
            opacity: 0.9;
            animation: fall linear infinite;
        }
    </style>
</head>



<body>
    <div id="petalContainer"></div>
    <div class="container">
        <div class="form-register">
            <h2>Thông tin cá nhân</h2>
            <form>
                <div class="inner-name">
                    <div class="form-row">
                        <label for="hoTen">Họ và tên đệm:</label>
                        <input type="text" id="hoTen" name="hoTen" required>
                    </div>
                    <div class="form-row">
                        <label for="ten">Tên:</label>
                        <input type="text" id="ten" name="ten" required>
                    </div>
                </div>
                <div class="inner-masv">
                    <div class="form-row">
                        <label for="maSV">Mã sinh viên:</label>
                        <input type="text" id="maSV" name="maSV" required>
                    </div>
                    <div class="form-row">
                        <label for="gioiTinh">Giới tính:</label>
                        <select id="gioiTinh" name="gioiTinh">
                            <option value="nam">Nam</option>
                            <option value="nu">Nữ</option>
                            <option value="khac">Khác</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <label for="sinhNhat">Sinh nhật:</label>
                    <div class="select-wrapper">
                        <select id="ngaySinh" name="ngaySinh" required>
                            <option value="">Ngày</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                            <option value="31">31</option>
                        </select>
                        <select id="thangSinh" name="thangSinh" required>
                            <option value="">Tháng</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <select id="namSinh" name="namSinh" required>
                            <option value="">Năm</option>
                            <option value="1970">1970</option>
                            <option value="1971">1971</option>
                            <option value="1972">1972</option>
                            <option value="1973">1973</option>
                            <option value="1974">1974</option>
                            <option value="1975">1975</option>
                            <option value="1976">1976</option>
                            <option value="1977">1977</option>
                            <option value="1978">1978</option>
                            <option value="1979">1979</option>
                            <option value="1980">1980</option>
                            <option value="1981">1981</option>
                            <option value="1982">1982</option>
                            <option value="1983">1983</option>
                            <option value="1984">1984</option>
                            <option value="1985">1985</option>
                            <option value="1986">1986</option>
                            <option value="1987">1987</option>
                            <option value="1988">1988</option>
                            <option value="1989">1989</option>
                            <option value="1990">1990</option>
                            <option value="1991">1991</option>
                            <option value="1992">1992</option>
                            <option value="1993">1993</option>
                            <option value="1994">1994</option>
                            <option value="1995">1995</option>
                            <option value="1996">1996</option>
                            <option value="1997">1997</option>
                            <option value="1998">1998</option>
                            <option value="1999">1999</option>
                            <option value="2000">2000</option>
                            <option value="2001">2001</option>
                            <option value="2002">2002</option>
                            <option value="2003">2003</option>
                            <option value="2004">2004</option>
                            <option value="2005">2005</option>
                            <option value="2006">2006</option>
                            <option value="2007">2007</option>
                            <option value="2008">2008</option>
                            <option value="2009">2009</option>
                            <option value="2010">2010</option>
                            <option value="2011">2011</option>
                            <option value="2012">2012</option>
                            <option value="2013">2013</option>
                            <option value="2014">2014</option>
                            <option value="2015">2015</option>
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025">2025</option>
                        </select>
                    </div>
                </div>


                <h1>Địa chỉ</h1>

                <div class="inner-address">
                    <div class="form-row">
                        <label>Tỉnh/Thành phố:</label>
                        <select id="province" onchange="loadDistricts()">
                            <option value="">Chọn tỉnh/thành phố</option>
                        </select>
                    </div>
                    <div class="form-row">
                        <label>Quận/Huyện:</label>
                        <select id="district" onchange="loadWards()">
                            <option value="">Chọn quận/huyện</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <label>Phường/Xã:</label>
                    <select id="ward">
                        <option value="">Chọn phường/xã</option>
                    </select>
                </div>

                <button type="submit" style="color: white; font-weight: 700;">Gửi</button>
            </form>
        </div>
    </div>
</body>
<script src="../assets/js/effect-js/roihoa.js"></script>
<script src="../assets/js/valiate/datadate.js"></script>
<script src="../assets/js/valiate/information.js"></script>
<script>
    async function loadProvinces() {
        const response = await fetch("https://vn-public-apis.fpo.vn/provinces/getAll?limit=-1");
        const data = await response.json();
        let provinceSelect = document.getElementById("province");

        data.data.data.forEach(province => {
            let option = document.createElement("option");
            option.value = province.code;
            option.textContent = province.name;
            provinceSelect.appendChild(option);
        });
    }

    async function loadDistricts() {
        let provinceId = document.getElementById("province").value;
        let districtSelect = document.getElementById("district");

        districtSelect.innerHTML = '<option value="">Chọn quận/huyện</option>';

        if (provinceId) {
            try {
                let response = await fetch("https://vn-public-apis.fpo.vn/districts/getAll?limit=-1");
                let data = await response.json();

                console.log("API Response:", data); // Kiểm tra API trả về

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

                console.log("API Response (Wards):", data); // Kiểm tra API trả về

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

    window.onload = loadProvinces;
</script>

</html>