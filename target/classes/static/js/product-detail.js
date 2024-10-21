$(document).ready(function () {
    $("#buy_btn").click(function (e) {
        e.preventDefault();
       let form = document.getElementById("add_to_cart_form");
       let formData = new FormData(form);
        $.ajax({
            url: '/api/cart/addCart',
            method: "POST",
            contentType: false,
            processData:false,
            data: formData,
            success: function () {
                window.alert("Success!")
            },
            error: function () {
                window.alert("Fail!")
            }
        })
    })
})
