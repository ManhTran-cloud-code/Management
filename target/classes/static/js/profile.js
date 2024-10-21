$(document).ready(function() {
    $("#update_profile_form").validate({
        rules: {
            phone: "required",
            address: "required",
            birthday:"required",
            gender:"required"
        },
        messages: {
            phone:"Vui long nhap so dien thoai cua ban",
            address: "Vui long nhap vao dia chi",
            birthday: "Vui long nhap vao ngay thang nam sinh cua ban",
            gender:"Vui long nhap vao gioi tinh cua ban"
        },
        submitHandler: function(form,e) {
            e.preventDefault();
            let phone = $("#phone").val();
            let address = $("#address").val();
            let birthday = $("#birthday").val();
            let gender = $(".form-check-input").val();
            let userDetail = {"phoneNumber":phone,"address":address,"dateOfBirth":birthday,"gender":gender}
            console.log(userDetail);
            $.ajax({
                url:'/api/user/updateProfile',
                method:"POST",
                contentType:"application/json",
                data: JSON.stringify(userDetail),
                success:function (){
                    window.alert("Update success");
                },
                error:function(){
                    window.alert("Fail!");
                }
            })
        }
    });
});


