/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function () {
    var exampleModal = document.getElementById('exampleModal');
    exampleModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        var id = button.getAttribute('data-bs-id');
        var name = button.getAttribute('data-bs-name');
        var type = button.getAttribute('data-bs-type');
        const idParam = document.querySelector('[name="promotionId"]'); 
        
        var modalTitle = exampleModal.querySelector('.modal-title');
        var modalBody = exampleModal.querySelector('.modal-body span');

        modalTitle.textContent = 'Xóa ' + type;
        modalBody.textContent = 'Bạn có chắc muốn xóa ' + type + " " + name + "?";
        idParam.value = id;
    });
});