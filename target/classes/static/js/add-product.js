$(document).ready(function (){
    $.ajax({
        url: "/api/category/getAll",
        method:"GET",
        success: function (categories){
            $.each(categories,function (i,category) {
               let option=` <option value="${category.id}">${category.name}</option>`
                $(".custom-select").append(option);
            })
        }
    })
})

function readUrl(input){
    if(input.files&&input.files[0]){
        let reader = new FileReader();
        reader.onload = function (e){
            $("#image-receive").attr('src',e.target.result);
        }
        reader.readAsDataURL(input.files[0])
    }
}
$("#product_image").change(function (){
    readUrl(this);
})

$(document).ready(function (){
$("#add-product-btn").click(function (e){
    e.preventDefault();
    let form = document.getElementById('add-product-form');
    let formData = new FormData(form);
    $.ajax({
        url:"/api/product/add",
        method:"POST",
        data:formData,
        contentType:false,
        processData:false,
        success: function (response){
            window.alert("Add Product Success!");
        },
        error: function(){
            window.alert("Add Product Fail!")
        }
    })
})
})