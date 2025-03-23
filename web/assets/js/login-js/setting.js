/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function openModal() {
    document.getElementById('modal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

document.getElementById("customFileUpload").addEventListener("click", function () {
    document.getElementById("fileInput").click();
});

const newContent = document.querySelector('.js-open-modal')
const modal = document.querySelector('.js-modal')
const modalContainer = document.querySelector('.js-modal-container')
const modalClose = document.querySelector('.js-modal-close')
const imgPreview = document.getElementById('imgPreview');
const fileInput = document.getElementById('fileInput');

function showModal() {
    modal.classList.add('open')
}

function hideModal() {
    modal.classList.remove('open')
}

newContent.addEventListener('click', showModal)

modalClose.addEventListener('click', hideModal)

modalClose.addEventListener('click', function () {
    setTimeout(() => {
        document.getElementById('imgPreview').innerHTML = "";
        document.getElementById('fileInput').value = "";
    }, 300);
});
modal.addEventListener('click', hideModal)
modal.addEventListener('click', function () {
    setTimeout(() => {
        document.getElementById('imgPreview').innerHTML = "";
        document.getElementById('fileInput').value = "";
    }, 300);
});

modalContainer.addEventListener('click', function (event) {
    event.stopPropagation();
})


document.getElementById('fileInput').addEventListener('change', function (event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const imgElement = document.createElement('img');
            imgElement.src = e.target.result;
            imgElement.style.maxWidth = "100%";
            imgElement.style.borderRadius = "8px";
            imgElement.style.marginTop = "10px";

            const previewDiv = document.getElementById('imgPreview');
            previewDiv.innerHTML = "";
            previewDiv.appendChild(imgElement);
        };
        reader.readAsDataURL(file);
    }
});