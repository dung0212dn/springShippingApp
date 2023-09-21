

const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';

function generateRandomString(length) {
    let result = '';
    for (let i = 0; i < length; i++)
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    return result;
}

function checkDate() {
    var selectedDate = new Date(document.getElementById("datepicker").value);
    var currentDate = new Date();

    if (selectedDate < currentDate) {
        alert("Ngày đã chọn không hợp lệ. Vui lòng chọn ngày trong tương lai hoặc ngày hiện tại.");
        return;
    }
}

function getCurrentDate() {
    let currentDate = new Date();
    return currentDate.toISOString().split('T')[0];
}


function displayImage(input) {
    var reader = new FileReader();
    reader.onload = function (event) {
        var img = document.getElementById('selected-image');
        img.src = event.target.result;
    };
    reader.readAsDataURL(input.files[0]);
}

function formatNumber(number) {
  return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}


