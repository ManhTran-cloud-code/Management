$(document).ready(function(){
let id = $("#category_id").val();
$.ajax({
    url:"/api/category/getAll",
    method:"GET",
    success: function (categories){
         $.each(categories,function (i,category){
             let inputSelect = `<option id="${category.id}" value="${category.id}">${category.name}</option>`;
             $(".custom-select").append(inputSelect);
         })
        $("select").val(id);
    }
})
})

$(document).ready(function (){
    $("#update-product-btn").click(function (e){
        e.preventDefault();
        let form = document.getElementById("update-product-form");
        let formData = new FormData(form)
        console.log(formData.get("product_id"));
        console.log(formData.get("category_id"))
        $.ajax({
            url:"/api/product/update",
            method: "PUT",
            data:formData,
            processData:false,
            contentType:false,
            success: function (){
                window.alert("Success!");
            },
            error:function (){
                window.alert("Fail!")
            }
        })
    })
})